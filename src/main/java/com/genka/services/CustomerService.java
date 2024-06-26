package com.genka.services;

import com.genka.domain.customer.Customer;
import com.genka.domain.enums.UserRole;
import com.genka.dtos.CustomerNewDTO;
import com.genka.dtos.CustomerUpdateDTO;
import com.genka.repositories.CustomerRepository;
import com.genka.resources.exceptions.AuthorizationException;
import com.genka.resources.exceptions.DataIntegrityException;
import com.genka.resources.exceptions.EntityNotFoundException;
import com.genka.security.UserAuthDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder bcrypt;
    private final S3Service s3Service;
    private final ImageService imageService;

    @Value("${img.prefix.client.profile}")
    private String prefix;

    public CustomerService(CustomerRepository customerRepository, BCryptPasswordEncoder bcrypt, S3Service s3Service, ImageService imageService) {
        this.customerRepository = customerRepository;
        this.bcrypt = bcrypt;
        this.s3Service = s3Service;
        this.imageService = imageService;
    }

    public Optional<Customer> findCustomerById(Integer id) {
        return customerRepository.findById(id);
    }

    public Customer getCustomerById(Integer customerId) {
        UserAuthDetails authenticatedUser = UserService.getAuthenticatedUser();
        if (authenticatedUser == null || !authenticatedUser.hasRole(UserRole.ADMIN) && !customerId.equals(authenticatedUser.getId())) {
            throw new AuthorizationException("Acesso negado");
        }
        return customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer with id " + customerId + " not found"));
    }

    public Customer getCustomerByEmail(String email) {
        UserAuthDetails authenticatedUser = UserService.getAuthenticatedUser();
        if (authenticatedUser == null || !authenticatedUser.hasRole(UserRole.ADMIN) && !email.equals(authenticatedUser.getUsername())) {
            throw new AuthorizationException("Acesso negado");
        }
        return customerRepository.findCustomerByEmail(email).orElseThrow(() -> new EntityNotFoundException("Customer with email " + email + " not found"));
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Integer customerId, CustomerUpdateDTO customerUpdate) {
        Customer customerToUpdate = getCustomerById(customerId);
        mapCustomerUpdates(customerToUpdate, customerUpdate);
        return customerRepository.save(customerToUpdate);
    }

    private void mapCustomerUpdates(Customer customerToUpdate, CustomerUpdateDTO update) {
        customerToUpdate.setName(update.getName());
        customerToUpdate.setEmail(update.getEmail());
    }

    public void deleteCustomer(Integer customerId) {
        Customer customerToDelete = getCustomerById(customerId);
        try {
            customerRepository.delete(customerToDelete);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityException("Customer cannot be deleted if there are orders linked to it");
        }
    }

    public Customer mapFromDTO(CustomerNewDTO customerNewDTO) {
        return new Customer(
                customerNewDTO.getEmail(),
                customerNewDTO.getName(),
                customerNewDTO.getIdentification(),
                customerNewDTO.getType(),
                bcrypt.encode(customerNewDTO.getPassword()),
                customerNewDTO.getPhones()
        );
    }

    public URI uploadProfilePicture(MultipartFile file) {
        UserAuthDetails authenticatedUser = UserService.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new AuthorizationException("Acesso negado");
        }
        Customer customer = getCustomerByEmail(authenticatedUser.getUsername());
        BufferedImage jpgImage = imageService.getJpgImageFromFile(file);
        jpgImage = imageService.cropSquare(jpgImage);
        String fileName = prefix + customer.getId() + ".jpg";
        URI pfpURI = s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
        customer.setPfpURL(pfpURI.toString());
        customerRepository.save(customer);
        return pfpURI;
    }
}