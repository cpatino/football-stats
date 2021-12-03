package com.carpco.footballstats.adapter.persistence.mapper;

import com.carpco.footballstats.adapter.persistence.entity.CountryEntity;
import com.carpco.footballstats.adapter.persistence.entity.PersonEntity;
import com.carpco.footballstats.domain.model.Country;
import com.carpco.footballstats.domain.model.Name;
import com.carpco.footballstats.domain.model.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class PlayerMapper implements Mapper<PersonEntity, Player> {
  
  private final Mapper<CountryEntity, Country> countryMapper;
  
  @Override
  public PersonEntity toEntity(Player player) {
    return PersonEntity.builder()
      .id(player.getId())
      .firstName(player.getName().first())
      .lastName(player.getName().last())
      .bornDate(player.getBornDate())
      .country(countryMapper.toEntity(player.getCountry()))
      .job(PersonEntity.Job.PLAYER)
      .build();
  }
  
  @Override
  public Player toDomain(PersonEntity player) {
    return Player.builder()
      .id(player.getId())
      .name(new Name(player.getFirstName(), player.getLastName()))
      .bornDate(player.getBornDate())
      .country(countryMapper.toDomain(player.getCountry()))
      .build();
  }
}
