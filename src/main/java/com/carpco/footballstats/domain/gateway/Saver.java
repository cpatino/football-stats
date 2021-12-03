package com.carpco.footballstats.domain.gateway;

public interface Saver<T> {
  
  T save(T domain);
}
