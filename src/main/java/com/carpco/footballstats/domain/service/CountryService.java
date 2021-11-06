package com.carpco.footballstats.domain.service;

import com.carpco.footballstats.domain.gateway.CountryPersistenceGateway;
import com.carpco.footballstats.domain.model.Country;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
@RequiredArgsConstructor
public class CountryService {
  
  private final CountryPersistenceGateway persistenceGateway;
  
  public Country create(Country country) {
    checkIfCountryAlreadyExists(country);
    return persistenceGateway.save(country);
  }
  
  private void checkIfCountryAlreadyExists(Country country) {
    try {
      persistenceGateway.findBy(country.getName());
      log.warn("The country with name {} was already created, the operation cannot be completed!", country.getName());
      throw new IllegalStateException("This country is already in the database, try again with a new country!");
    } catch (EntityNotFoundException ex) {
      log.info("The country was not found in the table, the new record can be created");
    }
  }
}
