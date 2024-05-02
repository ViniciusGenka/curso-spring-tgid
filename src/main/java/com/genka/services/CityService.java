package com.genka.services;

import com.genka.domain.City;
import com.genka.repositories.CityRepository;
import com.genka.resources.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Optional<City> findCityById(Integer id) {
        return cityRepository.findById(id);
    }

    public City getCityById(Integer cityId) {
        return cityRepository.findById(cityId).orElseThrow(() -> new EntityNotFoundException("City with id " + cityId + " not found"));
    }

    public City saveCity(City city) {
        return cityRepository.save(city);
    }
}
