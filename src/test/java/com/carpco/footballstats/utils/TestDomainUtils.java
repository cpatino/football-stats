package com.carpco.footballstats.utils;

import com.carpco.footballstats.domain.model.Country;
import com.carpco.footballstats.domain.model.Name;
import com.carpco.footballstats.domain.model.Player;

import java.time.LocalDate;

public abstract class TestDomainUtils {
  
  public static Country COUNTRY_SPAIN = Country.builder()
    .id(1L)
    .name("Spain")
    .build();
  
  public static Player PLAYER_ALVARO_MORATA = Player.builder()
    .id(1L)
    .name(new Name("Alvaro", "Morata"))
    .country(COUNTRY_SPAIN)
    .bornDate(LocalDate.of(1992, 10, 23))
    .build();
  
  private TestDomainUtils() {
  }
}
