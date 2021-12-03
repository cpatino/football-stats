package com.carpco.footballstats.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Country extends Domain {
  
  private final String name;
  
  @Builder
  public Country(Long id, String name) {
    super(id);
    this.name = name;
  }
}
