package com.carpco.footballstats.domain.service;

import com.carpco.footballstats.domain.gateway.PlayerPersistenceGateway;
import com.carpco.footballstats.domain.model.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = PlayerService.class)
class PlayerServiceTest {
  
  @MockBean
  private PlayerPersistenceGateway gateway;
  @Autowired
  private PlayerService service;
  
  @Test
  void givenAPlayer_whenCreate_thenVerifySave() {
    service.create(Player.builder().build());
    verify(gateway).save(any(Player.class));
  }
}
