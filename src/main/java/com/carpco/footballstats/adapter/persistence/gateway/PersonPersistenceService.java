package com.carpco.footballstats.adapter.persistence.gateway;

import com.carpco.footballstats.adapter.persistence.repository.PersonRepository;
import com.carpco.footballstats.domain.gateway.PlayerPersistenceGateway;
import com.carpco.footballstats.domain.model.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
class PersonPersistenceService implements PlayerPersistenceGateway {
  
  private final PersonRepository repository;
  
  @Override
  public Player save(Player player) {
    log.info("Saving the given data into the database");
    return null;
  }
}
