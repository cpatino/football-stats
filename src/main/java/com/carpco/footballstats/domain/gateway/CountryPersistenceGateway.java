package com.carpco.footballstats.domain.gateway;

import com.carpco.footballstats.domain.model.Country;

public interface CountryPersistenceGateway {
  
  Country findBy(String countryName);
  
  Country save(Country country);
}
