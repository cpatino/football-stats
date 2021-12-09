package com.carpco.footballstats.adapter.persistence.gateway;

import com.carpco.footballstats.adapter.persistence.entity.PersonEntity;
import com.carpco.footballstats.adapter.persistence.mapper.Mapper;
import com.carpco.footballstats.adapter.persistence.repository.PersonRepository;
import com.carpco.footballstats.domain.gateway.PlayerPersistenceGateway;
import com.carpco.footballstats.domain.model.Player;
import com.carpco.footballstats.utils.TestDomainUtils;
import com.carpco.footballstats.utils.TestEntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {PersonPersistenceService.class})
class PersonPersistenceServiceTest {
  
  @MockBean
  private Mapper<PersonEntity, Player> mapper;
  @MockBean
  private PersonRepository repository;
  @Autowired
  private PlayerPersistenceGateway persistenceGateway;
  
  @BeforeEach
  void setup() {
    when(mapper.toEntity(TestDomainUtils.PLAYER_ALVARO_MORATA)).thenReturn(TestEntityUtils.PERSON_ALVARO_MORATA);
    when(mapper.toDomain(TestEntityUtils.PERSON_ALVARO_MORATA)).thenReturn(TestDomainUtils.PLAYER_ALVARO_MORATA);
    
    when(repository.save(TestEntityUtils.PERSON_ALVARO_MORATA)).thenReturn(TestEntityUtils.PERSON_ALVARO_MORATA);
  }
  
  @Test
  void givenNullParameter_whenSave_thenIllegalArgumentExceptionIsThrown() {
    assertThatThrownBy(() -> persistenceGateway.save(null)).isInstanceOf(IllegalArgumentException.class);
  }
  
  @Test
  void givenCountry_whenSave_thenCompleteSaveOperation() {
    assertThat(persistenceGateway.save(TestDomainUtils.PLAYER_ALVARO_MORATA)).isEqualTo(TestDomainUtils.PLAYER_ALVARO_MORATA);
    verify(repository).save(TestEntityUtils.PERSON_ALVARO_MORATA);
  }
  
  @Test
  void givenCountry_whenSaveAndRepositoryFails_thenRuntimeExceptionIsThrown() {
    when(repository.save(TestEntityUtils.PERSON_ALVARO_MORATA)).thenThrow(new RuntimeException());
    assertThatThrownBy(() -> persistenceGateway.save(TestDomainUtils.PLAYER_ALVARO_MORATA)).isInstanceOf(RuntimeException.class);
  }
}