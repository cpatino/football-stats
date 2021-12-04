package com.carpco.footballstats.adapter.persistence.gateway;

import com.carpco.footballstats.adapter.persistence.entity.CountryEntity;
import com.carpco.footballstats.adapter.persistence.mapper.Mapper;
import com.carpco.footballstats.adapter.persistence.repository.CountryRepository;
import com.carpco.footballstats.domain.gateway.CountryPersistenceGateway;
import com.carpco.footballstats.domain.model.Country;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.stream.Stream;

@Service
@Slf4j
class CountryPersistenceService extends AbstractGateway<CountryEntity, Country> implements CountryPersistenceGateway {
  
  public CountryPersistenceService(Mapper<CountryEntity, Country> mapper, JpaRepository<CountryEntity, Long> repository) {
    super(mapper, repository);
  }
  
  @Override
  public Country findByNaturalId(String countryName) {
    log.info("Checking if the given country {} already exists into the database", countryName);
    return getRepository().findByName(countryName)
      .map(mapper::toDomain)
      .orElseThrow(() -> new EntityNotFoundException("The country was not found using the name " + countryName));
  }
  
  @Override
  public Stream<Country> findAllNotDeleted() {
    return getRepository().findByDeletedFalse()
      .stream()
      .parallel()
      .map(mapper::toDomain)
      .sorted(Comparator.comparing(Country::getName))
      .toList()
      .stream();
  }
  
  public CountryRepository getRepository() {
    return (CountryRepository) repository;
  }
}
