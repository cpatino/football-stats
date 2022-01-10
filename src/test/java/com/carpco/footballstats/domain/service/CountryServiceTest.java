package com.carpco.footballstats.domain.service;

import com.carpco.footballstats.domain.gateway.CountryPersistenceGateway;
import com.carpco.footballstats.domain.model.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityNotFoundException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CountryService.class)
class CountryServiceTest {
  
  @MockBean
  private CountryPersistenceGateway gateway;
  @Autowired
  private CountryService countryService;
  
  @Test
  void givenACountryThatAlreadyExists_whenCreate_thenThrowException() {
    var country = Country.builder().name("Spain").build();
    when(gateway.findByNaturalId("Spain")).thenReturn(country);
    assertThrows(IllegalStateException.class, () -> countryService.create(country));
  }
  
  @Test
  void givenACountryThatNotExists_whenCreate_thenVerifySave() {
    when(gateway.findByNaturalId("Spain")).thenThrow(new EntityNotFoundException());
    countryService.create(Country.builder().name("Spain").build());
    verify(gateway).save(any(Country.class));
  }
  
  @Test
  void whenFindAllEnabled_thenReturnStream() {
    when(gateway.findAllNotDeleted()).thenReturn(Stream.of(Country.builder().id(1L).name("Spain").build()));
    assertThat(countryService.findAllEnabled()).isNotNull().hasSize(1);
  }
}
