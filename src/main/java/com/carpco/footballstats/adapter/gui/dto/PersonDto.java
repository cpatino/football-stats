package com.carpco.footballstats.adapter.gui.dto;

import java.time.LocalDate;
import java.util.Objects;

public record PersonDto(PersonNameDto personName, CountryDto nationality, LocalDate bornDate) {
  
  public PersonDto {
    Objects.requireNonNull(personName);
    Objects.requireNonNull(nationality);
    Objects.requireNonNull(bornDate);
  }
}
