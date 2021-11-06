package com.carpco.footballstats.adapter.gui.dto;

import com.carpco.footballstats.domain.model.Country;

import java.util.Objects;

public record CountryDto(Long dbId, String name) {
  
  public CountryDto {
    Objects.requireNonNull(name);
  }
  
  public static CountryDto from(Country country) {
    return new CountryDto(country.getBdId(), country.getName());
  }
}
