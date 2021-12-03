package com.carpco.footballstats.domain.gateway;

import com.carpco.footballstats.domain.model.Country;

public interface CountryPersistenceGateway extends Saver<Country>, Finder<Country> {
}
