package com.carpco.footballstats.domain.gateway;

public interface SaveGateway<T> {
  
  T save(T domain);
}
