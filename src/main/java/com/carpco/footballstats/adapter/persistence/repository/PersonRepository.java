package com.carpco.footballstats.adapter.persistence.repository;

import com.carpco.footballstats.adapter.persistence.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
