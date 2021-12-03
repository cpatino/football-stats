package com.carpco.footballstats.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public abstract class Domain {
  
  private final Long id;
  
  protected Domain(Long id) {
    this.id = id;
  }
}
