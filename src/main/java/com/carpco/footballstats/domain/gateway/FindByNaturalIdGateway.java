package com.carpco.footballstats.domain.gateway;

public interface FindByNaturalIdGateway<T> {
  
  T findBy(String naturalId);
}
