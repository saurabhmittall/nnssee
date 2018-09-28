package com.nse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.batch.core.scope.context.SynchronizationManagerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nse.model.ETLMaster;
import com.nse.model.ETLTableMaster;
import com.nse.model.EtlColMap;
import com.nse.repository.AnnouncementsRepository;
import com.nse.repository.BoardMeetingsRepository;
import com.nse.repository.CorporateActionRepository;
import com.nse.repository.CorporateBondsRepository;
import com.nse.repository.ETLMasterRepository;
import com.nse.repository.ETLTableMasterRepository;
import com.nse.repository.EtlColMapRepository;
import com.nse.repository.SecurityMasterRepository;

import sm.nse.util.FileOperation;
import sm.nse.util.Utility;

@Component
public class Application {
	@Autowired
	AnnouncementsRepository announcementsRepository;
	@Autowired
	BoardMeetingsRepository BoardMeetingsRepository;
	@Autowired
	CorporateActionRepository corporateActionRepository;
	@Autowired
	CorporateBondsRepository corporateBondsRepository;
	@Autowired
	EtlColMapRepository etlColMapRepository;
	@Autowired
	ETLTableMasterRepository etlTableMasterRepository;
	@Autowired
	ETLMasterRepository etlMasterRepository;
	@Autowired
	SecurityMasterRepository securityMasterRepository;

	static Map<String, ETLMaster> etlMaster=null;
static	Map<String, EtlColMap> etlColMap=null;
	static Map<String, ETLTableMaster> etlTableMaster=null;

	 int loadETLData() {
		Iterable<ETLMaster> mas = etlMasterRepository.findAll();
		Iterator<ETLMaster> it = mas.iterator();
		etlMaster = new HashMap<String, ETLMaster>();
		while (it.hasNext()) {
			ETLMaster mas1 = it.next();
			etlMaster.put(mas1.getName(), mas1);

		}
	//	System.out.println("etlMaster="+etlMaster);
		etlTableMaster=new HashMap<String,ETLTableMaster>();
		Iterable<ETLTableMaster> tab = etlTableMasterRepository.findAll();
		Iterator<ETLTableMaster> it3 = tab.iterator();
		
		while (it3.hasNext()) {
			ETLTableMaster mas1 = it3.next();
			etlTableMaster.put(mas1.getDbTable(), mas1);

		}
		//System.out.println("etlTableMaster="+etlTableMaster);

		etlColMap = new HashMap<String, EtlColMap>();
		Iterable<EtlColMap> colMap = etlColMapRepository.findAll();
		Iterator<EtlColMap> it2 = colMap.iterator();
		etlColMap = new HashMap<String, EtlColMap>();
		while (it2.hasNext()) {
			EtlColMap mas1 = it2.next();
			etlColMap.put(mas1.getFileColName(), mas1);

		}
		//System.out.println("etlColMap="+etlColMap);
		return 0;
	}

	public void downloadFile(int day) {
		if(etlMaster==null)
			loadETLData();
		FileOperation file = new FileOperation();
		
		file.downloadFile(day, etlMaster.get("url").getValue(), etlMaster.get("localPath").getValue(),
				etlMaster.get("dateFormat").getValue());
	}

	public void UnZip() {
		if(etlMaster==null)
			loadETLData();
		System.out.println("list Zip Called..");
		Utility util = new Utility();
		String[] list = null;
		try {
			list = util.getLocalZipFileListSortOnName(etlMaster.get("localPath").getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list != null) {
			for (String fileName : list) {
				System.out.println("fileName:" + fileName);
			}
		}

	}
}
