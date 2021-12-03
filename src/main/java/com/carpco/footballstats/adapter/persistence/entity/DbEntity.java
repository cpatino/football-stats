package com.carpco.footballstats.adapter.persistence.entity;

import com.carpco.footballstats.domain.model.Domain;

public abstract class DbEntity {
  
  public abstract DbEntity toDomain();
  
  public abstract DbEntity buildFrom(Domain domain);
}
