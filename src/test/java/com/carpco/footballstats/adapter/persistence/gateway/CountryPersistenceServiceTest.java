package com.carpco.footballstats.adapter.persistence.gateway;

import com.carpco.footballstats.adapter.persistence.entity.CountryEntity;
import com.carpco.footballstats.adapter.persistence.mapper.Mapper;
import com.carpco.footballstats.adapter.persistence.repository.CountryRepository;
import com.carpco.footballstats.domain.gateway.CountryPersistenceGateway;
import com.carpco.footballstats.domain.model.Country;
import com.carpco.footballstats.utils.TestDomainUtils;
import com.carpco.footballstats.utils.TestEntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CountryPersistenceService.class})
class CountryPersistenceServiceTest {
  
  @MockBean
  private Mapper<CountryEntity, Country> mapper;
  @MockBean
  private CountryRepository repository;
  @Autowired
  private CountryPersistenceGateway persistenceGateway;
  
  @BeforeEach
  void setup() {
    when(mapper.toEntity(TestDomainUtils.COUNTRY_SPAIN)).thenReturn(TestEntityUtils.COUNTRY_SPAIN);
    when(mapper.toDomain(TestEntityUtils.COUNTRY_SPAIN)).thenReturn(TestDomainUtils.COUNTRY_SPAIN);
    
    when(repository.save(TestEntityUtils.COUNTRY_SPAIN)).thenReturn(TestEntityUtils.COUNTRY_SPAIN);
    when(repository.findByName(TestEntityUtils.COUNTRY_SPAIN.getName())).thenReturn(Optional.of(TestEntityUtils.COUNTRY_SPAIN));
    when(repository.findByDeletedFalse()).thenReturn(Collections.singletonList(TestEntityUtils.COUNTRY_SPAIN));
  }
  
  @Test
  void givenNullParameter_whenSave_thenIllegalArgumentExceptionIsThrown() {
    assertThatThrownBy(() -> persistenceGateway.save(null)).isInstanceOf(IllegalArgumentException.class);
  }
  
  @Test
  void givenCountry_whenSave_thenCompleteSaveOperation() {
    assertThat(persistenceGateway.save(TestDomainUtils.COUNTRY_SPAIN)).isEqualTo(TestDomainUtils.COUNTRY_SPAIN);
    verify(repository).save(TestEntityUtils.COUNTRY_SPAIN);
  }
  
  @Test
  void givenCountry_whenSaveAndRepositoryFails_thenRuntimeExceptionIsThrown() {
    when(repository.save(TestEntityUtils.COUNTRY_SPAIN)).thenThrow(new RuntimeException());
    assertThatThrownBy(() -> persistenceGateway.save(TestDomainUtils.COUNTRY_SPAIN)).isInstanceOf(RuntimeException.class);
  }
  
  @Test
  void givenNullCountryName_whenFindByNaturalId_thenIllegalArgumentExceptionIsThrown() {
    assertThatThrownBy(() -> persistenceGateway.findByNaturalId(null)).isInstanceOf(IllegalArgumentException.class);
  }
  
  @Test
  void givenEmptyCountryName_whenFindByNaturalId_thenIllegalArgumentExceptionIsThrown() {
    assertThatThrownBy(() -> persistenceGateway.findByNaturalId("")).isInstanceOf(IllegalArgumentException.class);
  }
  
  @Test
  void givenBlankCountryName_whenFindByNaturalId_thenIllegalArgumentExceptionIsThrown() {
    assertThatThrownBy(() -> persistenceGateway.findByNaturalId("  ")).isInstanceOf(IllegalArgumentException.class);
  }
  
  @Test
  void givenACountryName_whenFindByNaturalId_thenCompleteTheOperation() {
    assertThat(persistenceGateway.findByNaturalId(TestEntityUtils.COUNTRY_SPAIN.getName())).isEqualTo(TestDomainUtils.COUNTRY_SPAIN);
  }
  
  @Test
  void whenFindAllNotDeleted_thenCheckTheResult() {
    assertThat(persistenceGateway.findAllNotDeleted()).isNotNull()
      .hasSize(1)
      .hasOnlyElementsOfType(Country.class)
      .isEqualTo(Collections.singletonList(TestDomainUtils.COUNTRY_SPAIN));
  }
  
  @Test
  void whenFindAllNotDeletedButRepositoryIsEmpty_thenCheckTheResult() {
    when(repository.findByDeletedFalse()).thenReturn(Collections.emptyList());
    assertThat(persistenceGateway.findAllNotDeleted()).isNotNull().isEmpty();
  }
}