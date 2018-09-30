package com.nse.repository;

import org.springframework.data.repository.CrudRepository;

import com.nse.model.MarketData;

public interface MarketDataRepository  extends CrudRepository<MarketData, Integer>{

}
