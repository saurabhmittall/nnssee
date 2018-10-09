package com.nse.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nse.model.MarketData;
import com.nse.model.SecurityMaster;

public interface MarketDataRepository  extends CrudRepository<MarketData, Integer>{
	List<MarketData> findBySecurityMasterOrderByCreateDateDesc(SecurityMaster companyId);
	List<MarketData> findCreateDateGreaterThanOrderByCreateDate(Date createDate);
	@Query(value  = "SELECT max(createDate) FROM MarketData", nativeQuery = true)
	Date findMaxCreateDate();
	
	

}
