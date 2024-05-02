package com.genka.resources;

import com.genka.domain.address.Address;
import com.genka.dtos.AddressNewDTO;
import com.genka.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/addresses")
public class AddressResource {
    private final AddressService addressService;

    public AddressResource(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Integer id) {
        Address address = addressService.getAddressById(id);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    @PostMapping()
    public ResponseEntity<Address> addAddress(@Valid @RequestBody AddressNewDTO addressNewDTO) {
        Address savedAddress = addressService.saveAddress(addressService.mapFromDTO(addressNewDTO));
        return ResponseEntity.status(HttpStatus.OK).body(savedAddress);
    }
}
