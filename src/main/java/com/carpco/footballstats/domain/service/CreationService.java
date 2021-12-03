package com.carpco.footballstats.domain.service;

public interface CreationService<T> {
  
  T create(T newDomain);
}
