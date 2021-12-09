package com.carpco.footballstats.adapter.persistence.mapper;

import com.carpco.footballstats.adapter.persistence.entity.PersonEntity;
import com.carpco.footballstats.domain.model.Player;
import com.carpco.footballstats.utils.TestDomainUtils;
import com.carpco.footballstats.utils.TestEntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(classes = {PlayerMapper.class, CountryMapper.class})
class PlayerMapperTest {
  
  @Autowired
  private Mapper<PersonEntity, Player> mapper;
  
  @Test
  void givenNullParameter_whenMapToEntity_thenNullPointerExceptionIsThrown() {
    assertThatThrownBy(() -> mapper.toEntity(null)).isInstanceOf(NullPointerException.class);
  }
  
  @Test
  void givenPlayer_whenMapToEntity_thenEntityShouldBeEqual() {
    assertThat(mapper.toEntity(TestDomainUtils.PLAYER_ALVARO_MORATA)).isEqualTo(TestEntityUtils.PERSON_ALVARO_MORATA);
  }
  
  @Test
  void givenPersonEntity_whenMapToDomain_thenDomainShouldBeEqual() {
    assertThat(mapper.toDomain(TestEntityUtils.PERSON_ALVARO_MORATA)).isEqualTo(TestDomainUtils.PLAYER_ALVARO_MORATA);
  }
  
  @Test
  void givenNullParameter_whenMapToDomain_thenNullPointerExceptionIsThrown() {
    assertThatThrownBy(() -> mapper.toDomain(null)).isInstanceOf(NullPointerException.class);
  }
}