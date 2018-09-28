package com.nse.repository;

import org.springframework.data.repository.CrudRepository;

import com.nse.model.CorporateBonds;
import com.nse.model.SecurityMaster;

public interface SecurityMasterRepository  extends CrudRepository<SecurityMaster, Integer>{

}
