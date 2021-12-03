package com.carpco.footballstats.domain.service;

public interface Creator<T> {
  
  T create(T newDomain);
}
