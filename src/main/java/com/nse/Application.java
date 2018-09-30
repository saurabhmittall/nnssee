package com.nse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.scope.context.SynchronizationManagerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nse.constant.Constant;
import com.nse.model.Announcements;
import com.nse.model.BoardMeetings;
import com.nse.model.CorporateAction;
import com.nse.model.CorporateBonds;
import com.nse.model.ETLMaster;
import com.nse.model.ETLTableMaster;
import com.nse.model.Etf;
import com.nse.model.EtlColMap;
import com.nse.model.MarketData;
import com.nse.model.SecurityMaster;
import com.nse.repository.AnnouncementsRepository;
import com.nse.repository.BoardMeetingsRepository;
import com.nse.repository.CorporateActionRepository;
import com.nse.repository.CorporateBondsRepository;
import com.nse.repository.ETLMasterRepository;
import com.nse.repository.ETLTableMasterRepository;
import com.nse.repository.EtfRepository;
import com.nse.repository.EtlColMapRepository;
import com.nse.repository.MarketDataRepository;
import com.nse.repository.SecurityMasterRepository;

import sm.nse.util.CsvReader;
import sm.nse.util.FileOperation;
import sm.nse.util.Utility;

@Component
public class Application {
	@Autowired
	AnnouncementsRepository announcementsRepository;
	@Autowired
	BoardMeetingsRepository boardMeetingsRepository;
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
	@Autowired
	EtfRepository etfRepository;
	@Autowired
	MarketDataRepository marketDataRepository;

	static Map<String, ETLMaster> etlMaster = null;
	static Map<String, EtlColMap> etlColMap = null;
	static Map<String, ETLTableMaster> etlTableMasterMap = null;
	static List<ETLTableMaster> etlTableMasterList = null;

	int loadETLData() {
		Iterable<ETLMaster> mas = etlMasterRepository.findAll();
		Iterator<ETLMaster> it = mas.iterator();
		etlMaster = new HashMap<String, ETLMaster>();
		while (it.hasNext()) {
			ETLMaster mas1 = it.next();
			etlMaster.put(mas1.getName(), mas1);

		}
		// System.out.println("etlMaster="+etlMaster);
		etlTableMasterList = new ArrayList<ETLTableMaster>();
		etlTableMasterMap = new HashMap<String, ETLTableMaster>();
		Iterable<ETLTableMaster> tab = etlTableMasterRepository.findByStatus(1);
		Iterator<ETLTableMaster> it3 = tab.iterator();

		while (it3.hasNext()) {
			ETLTableMaster mas1 = it3.next();
			etlTableMasterMap.put(mas1.getDbTable(), mas1);
			etlTableMasterList.add(mas1);

		}
		// System.out.println("etlTableMaster="+etlTableMaster);

		etlColMap = new HashMap<String, EtlColMap>();
		Iterable<EtlColMap> colMap = etlColMapRepository.findAll();
		Iterator<EtlColMap> it2 = colMap.iterator();
		etlColMap = new HashMap<String, EtlColMap>();
		while (it2.hasNext()) {
			EtlColMap mas1 = it2.next();
			etlColMap.put(mas1.getFileColName(), mas1);

		}
		// System.out.println("etlColMap="+etlColMap);
		return 0;
	}

	public void downloadFile(int day) {
		if (etlMaster == null)
			loadETLData();
		FileOperation file = new FileOperation();

		file.downloadFile(day, etlMaster.get("url").getValue(), etlMaster.get("localPath").getValue(),
				etlMaster.get("dateFormat").getValue());
	}

	public void UnZip() {
		if (etlMaster == null)
			loadETLData();
		CsvReader csvReader = new CsvReader();
		FileOperation file = new FileOperation();
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
				String dir = fileName.replace(".zip", "");
				String dateStr = dir.substring(2);

				Date createDate = Utility.parseDate(dateStr, Constant.DateFormat.ddMMYY);
				System.out.println("dateStr=" + dateStr + "==" + createDate);

				List<String> unZipFileInfolder = file.unZipIt(etlMaster.get("localPath").getValue(), fileName,
						etlMaster.get("archiveLocalBaseDir").getValue());
				for (ETLTableMaster table : etlTableMasterList) {
					if (table.getFileExtension().equals("CSV")) {
						String csvFile = table.getNseNamePattern().replace("DDMMYY", dateStr);

						List<String[]> dataCsv = csvReader
								.makeFileList(etlMaster.get("localPath").getValue() + dir + "/" + csvFile);
						int row = 0;
						System.out.println("dataCsv=" + dataCsv.size());
						for (String[] dataCsvRow : dataCsv) {
							System.out.print("," + row);
							// for (String s : dataCsvRow)
							// System.out.print(">>" + s);
							// System.out.println("");
							row = row + 1;
							if (row == 1)
								continue;
							if (row == dataCsv.size())
								break;
							// System.out.println("dataCsvRow=" + dataCsvRow.length);

							if (table.getDbTable().equals("CorporateAction")) {
								String series = dataCsvRow[0];
								String symbol = dataCsvRow[1];
								String name = dataCsvRow[2];
								SecurityMaster sec = null;
								List<SecurityMaster> secList = securityMasterRepository
										.findByNameAndSeriesAndSymbol(name, series, symbol);
								if (secList != null && secList.size() > 0)
									sec = secList.get(0);
								else
									sec = new SecurityMaster(createDate, series, name, symbol);

								CorporateAction ann = new CorporateAction(sec, createDate,
										Utility.subArray(dataCsvRow, 3));
								try {
									corporateActionRepository.save(ann);
								} catch (Exception e) {
									System.out.println("222:" + e.getMessage());
								}
							}

							else

							if (table.getDbTable().equals("CorporateBonds")) {
								String market = dataCsvRow[0];
								String series = dataCsvRow[1];
								String symbol = dataCsvRow[2];
								String name = dataCsvRow[3];
								SecurityMaster sec = null;
								List<SecurityMaster> secList = securityMasterRepository
										.findByMarketAndNameAndSeriesAndSymbol(market, name, series, symbol);
								if (secList != null && secList.size() > 0)
									sec = secList.get(0);
								else
									sec = new SecurityMaster(createDate, series, name, symbol, market);
								CorporateBonds ann = new CorporateBonds(sec, createDate,
										Utility.subArray(dataCsvRow, 4));
								try {
									corporateBondsRepository.save(ann);
									;
								} catch (Exception e) {
									System.out.println("192:" + e.getMessage());
								}
							} else if (table.getDbTable().equals("ETF")) {
								String market = dataCsvRow[0];
								String series = dataCsvRow[1];
								String symbol = dataCsvRow[2];
								String name = dataCsvRow[3];
								SecurityMaster sec = null;
								List<SecurityMaster> secList = securityMasterRepository
										.findByMarketAndNameAndSeriesAndSymbol(market, name, series, symbol);
								if (secList != null && secList.size() > 0)
									sec = secList.get(0);
								else
									sec = new SecurityMaster(createDate, series, name, symbol, market);
								Etf ann = new Etf(sec, createDate, Utility.subArray(dataCsvRow, 4));
								try {
									etfRepository.save(ann);
									;
								} catch (Exception e) {
									e.printStackTrace();
								}
							} else if (table.getDbTable().equals("MarketData")) {
								String market = dataCsvRow[0];
								String series = dataCsvRow[1];
								String symbol = dataCsvRow[2];
								String name = dataCsvRow[3];
								SecurityMaster sec = null;
								List<SecurityMaster> secList = securityMasterRepository
										.findByMarketAndNameAndSeriesAndSymbol(market, name, series, symbol);
								if (secList != null && secList.size() > 0)
									sec = secList.get(0);
								else
									sec = new SecurityMaster(createDate, series, name, symbol, market);
								MarketData ann = new MarketData(sec, createDate, Utility.subArray(dataCsvRow, 4));
								// System.out.println("an="+ann);
								try {
									marketDataRepository.save(ann);
								} catch (Exception e) {
									System.out.println("222:" + e.getMessage());
								}
							}

						}

					}
					if (table.getFileExtension().equals("TXT")) {
						if (table.getDbTable().equals("announcements")) {
							/*
							 * SecurityMaster sec=new SecurityMaster(dataCsvRow); Announcements ann=new
							 * Announcements(createDate,dataCsvRow); announcementsRepository.save(ann);
							 */} else if (table.getDbTable().equals("BoardMeetings")) {
							/*
							 * BoardMeetings ann=new BoardMeetings(dataCsvRow);
							 * boardMeetingsRepository.save(ann);
							 */}
					}

				}
				file.coypFileArchive(etlMaster.get("localPath").getValue(), fileName,
						etlMaster.get("archiveLocalBaseDir").getValue());
				System.out.println("end");
				System.out.println(etlMaster.get("localPath").getValue() + "/" + fileName + "to :"
						+ etlMaster.get("archiveLocalBaseDir").getValue());
				
			}
		}

	}
}
