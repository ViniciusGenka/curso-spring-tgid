package com.genka.services;

import com.genka.domain.Address;
import com.genka.domain.City;
import com.genka.domain.Customer;
import com.genka.dtos.AddressNewDTO;
import com.genka.repositories.AddressRepository;
import com.genka.resources.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    private final CustomerService customerService;
    private final CityService cityService;

    public AddressService(AddressRepository addressRepository, CustomerService customerService, CityService cityService) {
        this.addressRepository = addressRepository;
        this.customerService = customerService;
        this.cityService = cityService;
    }

    public Optional<Address> findAddressById(Integer id) {
        return addressRepository.findById(id);
    }

    public Address getAddressById(Integer addressId) {
        return addressRepository.findById(addressId).orElseThrow(() -> new EntityNotFoundException("Address with id " + addressId + " not found"));
    }

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address mapFromDTO(AddressNewDTO addressNewDTO) {
        City city = cityService.getCityById(addressNewDTO.getCityId());
        Customer customer = customerService.getCustomerById(addressNewDTO.getUserId());
        return new Address(
                null,
                addressNewDTO.getStreet(),
                addressNewDTO.getNumber(),
                addressNewDTO.getComplement(),
                addressNewDTO.getNeighborhood(),
                addressNewDTO.getZipCode(),
                city,
                customer
        );
    }
}
