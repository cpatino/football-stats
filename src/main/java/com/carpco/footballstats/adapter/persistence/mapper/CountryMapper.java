package com.carpco.footballstats.adapter.persistence.mapper;

import com.carpco.footballstats.adapter.persistence.entity.CountryEntity;
import com.carpco.footballstats.domain.model.Country;
import org.springframework.stereotype.Component;

@Component
class CountryMapper implements Mapper<CountryEntity, Country> {
  
  @Override
  public CountryEntity toEntity(Country country) {
    return CountryEntity.builder()
      .id(country.getId())
      .name(country.getName())
      .build();
  }
  
  @Override
  public Country toDomain(CountryEntity country) {
    return Country.builder()
      .id(country.getId())
      .name(country.getName())
      .build();
  }
}
