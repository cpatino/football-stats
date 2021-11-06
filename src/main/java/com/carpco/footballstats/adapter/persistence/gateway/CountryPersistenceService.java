package com.carpco.footballstats.adapter.persistence.gateway;

import com.carpco.footballstats.adapter.persistence.entity.CountryEntity;
import com.carpco.footballstats.adapter.persistence.repository.CountryRepository;
import com.carpco.footballstats.domain.gateway.CountryPersistenceGateway;
import com.carpco.footballstats.domain.model.Country;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
class CountryPersistenceService implements CountryPersistenceGateway {
  
  private final CountryRepository repository;
  
  @Override
  public Country findBy(String countryName) {
    log.info("Checking if the given country {} already exists into the database", countryName);
    return repository.findByName(countryName)
      .map(CountryEntity::toDomain)
      .orElseThrow(() -> new EntityNotFoundException("The country was not found using the name " + countryName));
  }
  
  @Override
  public Country save(Country country) {
    log.info("Saving the given data into the database");
    return Optional.ofNullable(country)
      .map(CountryEntity::buildFrom)
      .map(repository::save)
      .map(CountryEntity::toDomain)
      .orElseThrow(() -> new RuntimeException("Save operation has failed."));
  }
}
