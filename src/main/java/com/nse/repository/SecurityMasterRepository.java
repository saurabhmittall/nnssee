package com.nse.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nse.model.SecurityMaster;

public interface SecurityMasterRepository  extends CrudRepository<SecurityMaster, Integer>{
List<SecurityMaster>findByNameAndSeriesAndSymbol(String name,String series,String symbol);

List<SecurityMaster> findByMarketAndNameAndSeriesAndSymbol(String market, String name, String series, String symbol);
}
