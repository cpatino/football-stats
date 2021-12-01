package com.carpco.footballstats.adapter.persistence.entity;

import com.carpco.footballstats.domain.model.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "country")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @NaturalId
  private String name;
  
  public Country toDomain() {
    return Country.builder()
      .id(id)
      .name(name)
      .build();
  }
  
  public static CountryEntity buildFrom(Country country) {
    return CountryEntity.builder()
      .id(country.getId())
      .name(country.getName())
      .build();
  }
}
