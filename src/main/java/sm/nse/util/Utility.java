package sm.nse.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;

public class Utility {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//OPTIDXNIFTY24-JUN-2021CE11200 OPTIDXFTSE10021-SEP-2018PE8000
		String str = "OPTIDXFTSE10021-SEP-2018PE8000";
		List<String> res = splitName(str);
		for(String rr:res)
			System.out.println("aa="+rr);

	}

	public static List<String> splitName(String str) {
		List<String> res = new ArrayList<String>();
		String str1 = null;
		String callType;
		String price = "";
		int index = 0;
		List<String> list = null;
		try {
			if (str.contains("OPTIDX"))
				str = str.replaceAll("OPTIDX", "");
			if (str.contains("OPTSTK"))
				str = str.replaceAll("OPTSTK", "");
			int ceIndex = str.lastIndexOf("CE");
			int peIndex = str.lastIndexOf("PE");
			if (ceIndex > 0 && ceIndex > peIndex) {
				callType = "CE";
				index = ceIndex;
			} else {
				callType = "PE";
				index = peIndex;
			}
			System.out.println("str="+str+",,index="+index);
			if(index==-1)return res;
			String expiry=str.substring(index-11, index);
			price=str.substring(index+2);
			String symbol=str.substring(0,index-11);
			res.add(symbol);
			res.add(callType);
			res.add(expiry);
			res.add(price);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(str + ">>str=" + str1 + "," + list.get(0));
		}
		return res;
	}

	public static String datetoString(String pattern, int currentMinus) {
		
		pattern=pattern.trim();
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, currentMinus);
		
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		String strDate = dateFormat.format(cal.getTime());

		return strDate;

	}

	public static Date stringToDate(String pattern, String datestr) {

		Date date1 = null;
		try {
			date1 = new SimpleDateFormat(pattern).parse(datestr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(datestr + "\t" + date1);
		return date1;
	}

	public static void waitIn() {
		try {
			System.out.print("press any key...");
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public static String removeHtmlTags(String desc) {
		org.jsoup.nodes.Document doc = Jsoup.parse(desc);
		if (doc != null) {
			return doc.text();
		}
		return "";
	}
	public static String removeJunkChar(String desc) {
		desc = desc.replace("â", "").replace("&amp;", "").replace("#039;", "");
		return desc;
	}
	public static Date parseDate(String DateStr, String rssDateFormatStr) throws ParseException {

		Date date = null;
		try {
			if (DateStr == null || DateStr.equals(""))
				return null;
			DateFormat originalFormat = new SimpleDateFormat(rssDateFormatStr);
			date = originalFormat.parse(DateStr.trim());
		} catch (ParseException ee) {
			System.err.println("rssDateFormatStr=" + rssDateFormatStr + "DateStr=" + DateStr);
			ee.printStackTrace();
			throw ee;
		}
		return date;
	}
	public String[] getLocalZipFileListSortOnName(String localPath) {
		File f = null;
		String[] paths = null;
		try {

			f = new File(localPath);

			paths = f.list(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.toLowerCase().endsWith(".zip");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(paths!=null)
		Arrays.sort(paths);

		return paths;
	}
}
