package com.carpco.footballstats.adapter.persistence.mapper;

import com.carpco.footballstats.adapter.persistence.entity.CountryEntity;
import com.carpco.footballstats.domain.model.Country;
import com.carpco.footballstats.utils.TestDomainUtils;
import com.carpco.footballstats.utils.TestEntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(classes = CountryMapper.class)
class CountryMapperTest {
  
  @Autowired
  private Mapper<CountryEntity, Country> mapper;
  
  @Test
  void givenNullParameter_whenMapToEntity_thenNullPointerExceptionIsThrown() {
    assertThatThrownBy(() -> mapper.toEntity(null)).isInstanceOf(NullPointerException.class);
  }
  
  @Test
  void givenSpainCountry_whenMapToEntity_thenEntityShouldBeEqual() {
    assertThat(mapper.toEntity(TestDomainUtils.COUNTRY_SPAIN)).isEqualTo(TestEntityUtils.COUNTRY_SPAIN);
  }
  
  @Test
  void givenNullParameter_whenMapToDomain_thenNullPointerExceptionIsThrown() {
    assertThatThrownBy(() -> mapper.toDomain(null)).isInstanceOf(NullPointerException.class);
  }
  
  @Test
  void givenSpainCountry_whenMapToDomain_thenDomainShouldBeEqual() {
    assertThat(mapper.toDomain(TestEntityUtils.COUNTRY_SPAIN)).isEqualTo(TestDomainUtils.COUNTRY_SPAIN);
  }
}