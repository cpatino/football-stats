package com.carpco.footballstats.utils;

import com.carpco.footballstats.adapter.persistence.entity.CountryEntity;
import com.carpco.footballstats.adapter.persistence.entity.PersonEntity;

import java.time.LocalDate;

public abstract class TestEntityUtils {
  
  public static CountryEntity COUNTRY_SPAIN = CountryEntity.builder()
    .id(1L)
    .name("Spain")
    .build();
  
  public static PersonEntity PERSON_ALVARO_MORATA = PersonEntity.builder()
    .id(1L)
    .firstName("Alvaro")
    .lastName("Morata")
    .country(COUNTRY_SPAIN)
    .bornDate(LocalDate.of(1992, 10, 23))
    .job(PersonEntity.Job.PLAYER)
    .build();
  
  private TestEntityUtils() {
  }
}
