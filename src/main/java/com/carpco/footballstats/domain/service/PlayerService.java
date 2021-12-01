package com.carpco.footballstats.domain.service;

import com.carpco.footballstats.adapter.gui.dto.PersonDto;
import com.carpco.footballstats.domain.gateway.PlayerPersistenceGateway;
import com.carpco.footballstats.domain.model.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerService {
  
  //private final PlayerPersistenceGateway gateway;
  
  public Player create(PersonDto playerDto) {
    return null;
  }
}
