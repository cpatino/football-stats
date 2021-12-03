package com.carpco.footballstats.domain.model;

import java.util.Objects;

public record Name(String first, String last) {
  
  public Name {
    Objects.requireNonNull(first);
    Objects.requireNonNull(last);
  }
  
  public String full() {
    return first + " " + last;
  }
}
