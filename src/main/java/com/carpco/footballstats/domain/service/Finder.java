package com.carpco.footballstats.domain.service;

import java.util.stream.Stream;

public interface Finder<T> {
  
  Stream<T> findAllEnabled();
}
