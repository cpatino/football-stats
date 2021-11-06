package com.carpco.footballstats.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Country {
  
  private Long bdId;
  private String name;
}
