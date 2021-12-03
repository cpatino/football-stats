package com.carpco.footballstats.domain.gateway;

import java.util.stream.Stream;

public interface Finder<T> {
  
  T findByNaturalId(String naturalId);
  
  Stream<T> findAllNotDeleted();
}
