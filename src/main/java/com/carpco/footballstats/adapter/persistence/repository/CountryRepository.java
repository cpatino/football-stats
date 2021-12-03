package com.carpco.footballstats.adapter.persistence.repository;

import com.carpco.footballstats.adapter.persistence.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
  
  Optional<CountryEntity> findByName(String name);
  
  List<CountryEntity> findByDeletedFalse();
}
