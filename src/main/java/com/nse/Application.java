package com.nse;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nse.constant.Constant;
import com.nse.model.Analytics;
import com.nse.model.CorporateAction;
import com.nse.model.CorporateBonds;
import com.nse.model.ETLMaster;
import com.nse.model.ETLTableMaster;
import com.nse.model.Etf;
import com.nse.model.EtlColMap;
import com.nse.model.MarketData;
import com.nse.model.SecurityMaster;
import com.nse.repository.AnalyticsRepository;
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
import com.nse.thread.MyMonitorThread;
import com.nse.thread.RejectedExecutionHandlerImpl;
import com.nse.thread.WorkerThread;

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
	@Autowired
	AnalyticsRepository analyticsRepository;

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

		System.out.println("list Zip Called..");
		Utility util = new Utility();

		String[] list = null;
		try {
			list = util.getLocalZipFileListSortOnName(etlMaster.get("localPath").getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				String fileName = list[i];
				callfileDB(i, fileName);

			}
		}
	}

	public void callfileDB(int i, String fileName) {
		CsvReader csvReader = new CsvReader();
		FileOperation file = new FileOperation();
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
				System.out.println("\n>>dataCsv=" + dataCsv.size());

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
						try {
							String series = dataCsvRow[0];
							String symbol = dataCsvRow[1];
							String name = dataCsvRow[2];
							SecurityMaster sec = null;
							List<SecurityMaster> secList = securityMasterRepository.findByNameAndSeriesAndSymbol(name,
									series, symbol);
							if (secList != null && secList.size() > 0)
								sec = secList.get(0);
							else
								sec = new SecurityMaster(createDate, series, name, symbol);

							CorporateAction ann = new CorporateAction(sec, createDate, Utility.subArray(dataCsvRow, 3));

							corporateActionRepository.save(ann);
						} catch (Exception e) {
							System.out.println("186:" + e.getMessage());
						}
					}

					else

					if (table.getDbTable().equals("CorporateBonds")) {
						try {
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
							CorporateBonds ann = new CorporateBonds(sec, createDate, Utility.subArray(dataCsvRow, 4));

							corporateBondsRepository.save(ann);
							;
						} catch (Exception e) {
							System.out.println("192:" + e.getMessage());
						}
					} else if (table.getDbTable().equals("ETF")) {
						try {
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

							etfRepository.save(ann);
							;
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (table.getDbTable().equals("MarketData")) {
						try {
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

	public void download1File() {
		if (etlMaster == null)
			loadETLData();
		int p = 0;
		// RejectedExecutionHandler implementation
		RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
		// Get the ThreadFactory implementation to use
		ThreadFactory threadFactory = Executors.defaultThreadFactory();
		// creating the ThreadPoolExecutor
		/* initial pool size as 2, maximum pool size to 4 and work queue size as 2 */
		ThreadPoolExecutor executorPool = new ThreadPoolExecutor(1, 2, 10, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(2), threadFactory, rejectionHandler);
		// start the monitoring thread
		MyMonitorThread monitor = new MyMonitorThread(executorPool, 10);
		Thread monitorThread = new Thread(monitor);
		monitorThread.start();
		List<String> uniList = new ArrayList<String>();
		Iterable<SecurityMaster> secqurityMasterList = securityMasterRepository.findBySeriesAndBseIdNotNull("EQ");
		for (SecurityMaster table : secqurityMasterList) {
			p = p + 1;
			System.out.println("i=i=i=" + p);
			// if(p<199)
			// continue;
			if (uniList.contains(table.getBseId()))
				continue;
			uniList.add(table.getBseId());
			ExecutorService executor = Executors.newFixedThreadPool(5);
			if (table.getBseId() == null)
				continue;
			String url = etlMaster.get("SPattern").getValue();
			for (int i = 95; i < 99; i++) {
				String url1 = url.replace("SCRIPCODE", table.getBseId()).replace("QTR", "" + i);
				// url=url+i;
				System.out.println("table:" + table.getName() + ",url:" + url1);
				String localPath = etlMaster.get("SPatternBaseDir").getValue() + table.getBseId() + "qtr" + i + ".html";
				// System.out.println("localPath=" + localPath);
				Runnable worker = new WorkerThread(url1, localPath);
				executor.execute(worker);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		// shut down the pool
		executorPool.shutdown();
		// shut down the monitor thread
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		monitor.shutdown();
	}

	public void download1FinResults() {
		if (etlMaster == null)
			loadETLData();
		int p = 0;
		// RejectedExecutionHandler implementation
		RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
		// Get the ThreadFactory implementation to use
		ThreadFactory threadFactory = Executors.defaultThreadFactory();
		// creating the ThreadPoolExecutor
		/* initial pool size as 2, maximum pool size to 4 and work queue size as 2 */
		ThreadPoolExecutor executorPool = new ThreadPoolExecutor(1, 2, 10, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(2), threadFactory, rejectionHandler);
		// start the monitoring thread
		MyMonitorThread monitor = new MyMonitorThread(executorPool, 10);
		Thread monitorThread = new Thread(monitor);
		monitorThread.start();
		List<String> uniList = new ArrayList<String>();
		Iterable<SecurityMaster> secqurityMasterList = securityMasterRepository.findBySeriesAndBseIdNotNull("EQ");
		for (SecurityMaster table : secqurityMasterList) {
			p = p + 1;
			System.out.println("i=i=i=" + p);
			// if(p<199)
			// continue;
			if (uniList.contains(table.getBseId()))
				continue;
			uniList.add(table.getBseId());
			ExecutorService executor = Executors.newFixedThreadPool(5);
			if (table.getBseId() == null)
				continue;
			String url = etlMaster.get("SPattern").getValue();
			for (int i = 90; i < 91; i++) {
				String url1 = url.replace("SCRIPCODE", table.getBseId()).replace("QTR", "" + i);
				// url=url+i;
				System.out.println("table:" + table.getName() + ",url:" + url1);
				String localPath = etlMaster.get("SPatternBaseDir").getValue() + table.getBseId() + "qtr" + i + ".html";
				// System.out.println("localPath=" + localPath);
				Runnable worker = new WorkerThread(url1, localPath);
				executor.execute(worker);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		// shut down the pool
		executorPool.shutdown();
		// shut down the monitor thread
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		monitor.shutdown();
	}

	public void calculate() {
		Iterable<SecurityMaster> secList = securityMasterRepository.findAll();
		Iterator<SecurityMaster> it = secList.iterator();
		SecurityMaster secqertyMaster = null;
		List<MarketData> marketList = null;
		List<Analytics> list = null;
		Analytics analytics = null;
		MarketData mr = null;
		while (it.hasNext()) {
			// analyticsRepository
			secqertyMaster = it.next();
			if (secqertyMaster.getId() < 2269)
				continue;
			System.out.println(secqertyMaster.getId() + ",name=" + secqertyMaster.getName());
			marketList = marketDataRepository.findBySecurityMasterOrderByCreateDateDesc(secqertyMaster);
			System.out.println("marketList=" + marketList.size() + " date=" + (new Date()));
			list = new ArrayList<Analytics>();
			for (int i = 0; i < marketList.size(); i++) {

				mr = marketList.get(i);
				analytics = new Analytics(mr.getCreateDate(), mr.getSecurityMaster());
				// if (i > 1)
				analytics.setAv3(marketList, i);
				// if (i > 4)
				analytics.setAv5(marketList, i);
				// if (i > 14)
				analytics.setAv15(marketList, i);
				// if (i > 29)
				analytics.setAv30(marketList, i);
				// if (i > 89)
				analytics.setAv90(marketList, i);
				// if (i > 179)
				analytics.setAv180(marketList, i);
				// System.out.print(","+i);
				list.add(analytics);
			}
			analyticsRepository.save(list);
		}
		System.out.println("done");
	}

	public void calculateDaily() {

		List<MarketData> marketList = null;
		List<Analytics> list = null;
		Analytics analytics = null;
		MarketData mr = null;
		System.out.println("aaaaaaaaaaa");
		Date max = analyticsRepository.findMaxCreateDate();
		
		System.out.println("aa=" + max);
		marketList = marketDataRepository.findCreateDateGreaterThanOrderByCreateDate(max);
		System.out.println("marketList=" + marketList.size() + " date=" + (new Date()));
		list = new ArrayList<Analytics>();
		for (int i = 0; i < marketList.size(); i++) {
			mr = marketList.get(i);
			analytics = new Analytics(mr.getCreateDate(), mr.getSecurityMaster());
			analytics.setAv3(marketList, i);
			analytics.setAv5(marketList, i);
			analytics.setAv30(marketList, i);
			analytics.setAv90(marketList, i);
			analytics.setAv180(marketList, i);
			list.add(analytics);
		}
		analyticsRepository.save(list);

		System.out.println("done");
	}

}