package com.carpco.footballstats.adapter.persistence.mapper;

public interface Mapper<E, T> {
  
  E toEntity(T domain);
  
  T toDomain(E entity);
}
