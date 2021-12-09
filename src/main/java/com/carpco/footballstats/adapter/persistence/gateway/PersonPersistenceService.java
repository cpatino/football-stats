package com.carpco.footballstats.adapter.persistence.gateway;

import com.carpco.footballstats.adapter.persistence.entity.PersonEntity;
import com.carpco.footballstats.adapter.persistence.mapper.Mapper;
import com.carpco.footballstats.domain.gateway.PlayerPersistenceGateway;
import com.carpco.footballstats.domain.model.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
class PersonPersistenceService extends GatewayPattern<PersonEntity, Player> implements PlayerPersistenceGateway {
  
  public PersonPersistenceService(Mapper<PersonEntity, Player> mapper, JpaRepository<PersonEntity, Long> repository) {
    super(mapper, repository);
  }
}
