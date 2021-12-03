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

@Service
@Slf4j
class CountryPersistenceService extends AbstractSaveGateway<CountryEntity, Country> implements CountryPersistenceGateway {
  
  public CountryPersistenceService(Mapper<CountryEntity, Country> mapper, JpaRepository<CountryEntity, Long> repository) {
    super(mapper, repository);
  }
  
  @Override
  public Country findBy(String countryName) {
    log.info("Checking if the given country {} already exists into the database", countryName);
    return getRepository().findByName(countryName)
      .map(CountryEntity::toDomain)
      .orElseThrow(() -> new EntityNotFoundException("The country was not found using the name " + countryName));
  }
  
  @Override
  public CountryRepository getRepository() {
    return (CountryRepository) super.getRepository();
  }
}
