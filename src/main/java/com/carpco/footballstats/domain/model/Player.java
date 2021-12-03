package com.carpco.footballstats.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Player extends Domain {
  
  private final Name name;
  private final LocalDate bornDate;
  private final Country country;
  
  @Builder
  public Player(Long id, Name name, LocalDate bornDate, Country country) {
    super(id);
    this.name = name;
    this.bornDate = bornDate;
    this.country = country;
  }
}
