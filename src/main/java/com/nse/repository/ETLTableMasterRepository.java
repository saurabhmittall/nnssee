package com.nse.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nse.model.ETLTableMaster;
import com.nse.model.SecurityMaster;

public interface ETLTableMasterRepository  extends CrudRepository<ETLTableMaster, Integer>{
	List<ETLTableMaster> findByStatus(int status);
}
