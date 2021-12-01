package com.carpco.footballstats.adapter.gui.dto;

import java.util.Objects;

public record PersonNameDto(String firstName, String lastName) {
  
  public PersonNameDto {
    Objects.requireNonNull(firstName);
    Objects.requireNonNull(lastName);
  }
  
  public String getFullName() {
    return firstName + " " + lastName;
  }
}
