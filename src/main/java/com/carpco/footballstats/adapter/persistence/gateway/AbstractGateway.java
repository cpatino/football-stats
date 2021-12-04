package com.carpco.footballstats.adapter.persistence.gateway;

import com.carpco.footballstats.adapter.persistence.mapper.Mapper;
import com.carpco.footballstats.domain.gateway.Saver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractGateway<E, T> implements Saver<T> {
  
  protected final Mapper<E, T> mapper;
  protected final JpaRepository<E, Long> repository;
  
  public T save(T domain) {
    log.info("Saving the given data into the database");
    return Optional.ofNullable(domain)
      .map(mapper::toEntity)
      .map(repository::save)
      .map(mapper::toDomain)
      .orElseThrow(() -> new RuntimeException("Save operation has failed."));
  }
}
