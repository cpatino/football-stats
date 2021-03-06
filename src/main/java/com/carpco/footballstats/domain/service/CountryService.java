package com.carpco.footballstats.domain.service;

import com.carpco.footballstats.domain.gateway.CountryPersistenceGateway;
import com.carpco.footballstats.domain.model.Country;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
class CountryService implements Creator<Country>, Finder<Country> {
  
  private final CountryPersistenceGateway gateway;
  
  @Override
  public Country create(Country country) {
    checkIfCountryAlreadyExists(country.getName());
    return gateway.save(country);
  }
  
  @Override
  public Stream<Country> findAllEnabled() {
    return gateway.findAllNotDeleted();
  }
  
  private void checkIfCountryAlreadyExists(String countryName) {
    try {
      var country = gateway.findByNaturalId(countryName);
      log.warn("The country with name {} was already created, the operation cannot be completed!", country.getName());
      throw new IllegalStateException("This country is already in the database, try again with a new country!");
    } catch (EntityNotFoundException ex) {
      log.info("The country was not found in the table, the new record can be created");
    }
  }
}
