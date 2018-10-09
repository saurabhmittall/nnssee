package com.nse.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nse.model.Analytics;



public interface AnalyticsRepository extends CrudRepository<Analytics, Long> {
	@Query(value  = "SELECT max(CreateDate) FROM Analytics ", nativeQuery = true)
	public Date findMaxCreateDate();
}
