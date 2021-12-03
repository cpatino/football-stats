package com.carpco.footballstats.domain.service;

import com.carpco.footballstats.domain.model.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
class PlayerService implements CreationService<Player> {
  
  //private final PlayerPersistenceGateway gateway;
  
  @Override
  public Player create(Player newDomain) {
    return null;
  }
}
