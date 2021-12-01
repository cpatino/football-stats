package com.carpco.footballstats.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@EqualsAndHashCode
public class Player {
  
  private Long id;
  private String firstName;
  private String lastName;
  private LocalDate bornDate;
}
