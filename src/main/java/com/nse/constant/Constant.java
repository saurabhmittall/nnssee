package com.nse.constant;

import java.text.SimpleDateFormat;

public class Constant {
	public static class DateFormat{
		public static SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
		public static SimpleDateFormat ddMMYY = new SimpleDateFormat("ddMMyy");
		
		public static SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd hh:00:00");
		public static SimpleDateFormat sf1 = new SimpleDateFormat("dd MMM, yyyy");
		public static SimpleDateFormat sf3 = new SimpleDateFormat("dd-MM-yyyy");
		public static SimpleDateFormat sf4 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	}
}
