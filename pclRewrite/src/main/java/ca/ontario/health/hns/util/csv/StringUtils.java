package ca.ontario.health.hns.util.csv;



import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StringUtils {

	private static DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
	private static SimpleDateFormat FR_FORMAT = new SimpleDateFormat("dd MMMMM yyyy", new Locale("fr", "FR"));
	private static SimpleDateFormat EN_FORMAT = new SimpleDateFormat("MMMMM dd, yyyy");
	private static int SIN_MAX_LENGTH = 9;

	public static Date getDateFromString(String dateStr) {
		return getDateFromString(dateStr, dateFormat);
	}

	public static String formatDate(Date date) {
		return formatDate(date, dateFormat);
	}

	public static String formatDate(Date date, String lang) {
		SimpleDateFormat dateFormat = "FR".equals(lang) ? FR_FORMAT : EN_FORMAT;
		return formatDate(date, dateFormat);
	}

	public static String formatDate(Date date, DateFormat dateFormat) {
		return date == null ? "" : dateFormat.format(date);
	}

	/*public static String formatDate(CustomTimestamp cts, DateFormat dateFormat) {
		return cts == null ? "" : dateFormat.format(new Date(cts.getMilliseconds()));
	}*/

	public static Date getDateFromString(String dateStr, DateFormat dateFormat) {
		try {
			if (dateStr != null)
				return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	// The int format is like 20130101.
	public static int getIntFromDate(Date date) {
		int dateInt = 0;
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			dateInt = Integer.valueOf(df.format(date));	
		}
		
		return dateInt;
	}

	public static String formatAppNumber(String appNumber) {
		if (appNumber == null || appNumber.length() < 10)
			return "";
		return appNumber.substring(0, 2) + "-" + appNumber.substring(2, 5) + "-" + appNumber.substring(5, 9) + "-"
				+ appNumber.substring(9, 11);
	}
	
	
	public static String fillOdbNumerString(Long o){
		
		if(String.valueOf(o).length()==10)
			return String.valueOf(o);
		else {
			StringBuffer buf=new StringBuffer();
			int prefixCount=10-String.valueOf(o).length();
			for(int i=0;i<prefixCount;i++){
				buf.append("0");
			}
			buf.append(String.valueOf(o));
			return buf.toString();
		}
	}

	public static String formatOdbNumber2(Long odbNumber) {
		String number = fillOdbNumerString(odbNumber);
		if (number == null || number.length() < 10)
			return "";
		return number.substring(0, 4) + " " + number.substring(4, 7) + " " + number.substring(7, 10);
	}
	
	public static String formatOdbNumber(Long odbNumber) {
		String number = "" + odbNumber;
		if (number == null || number.length() < 9)
			return "";
		if (number.length() == 9)//fix for qc8766
			number="0"+number;
		return number.substring(0, 4) + " " + number.substring(4, 7) + " " + number.substring(7, 10);
	}

	public static String formatPhoneNumber(String phoneNumber) {
		if (phoneNumber == null || phoneNumber.trim().length() < 9)
			return "";
		// QC9722 change phone format to (###) ###-####
		return "("+phoneNumber.substring(0, 3) + ") " + phoneNumber.substring(3, 6) + "-" + phoneNumber.substring(6, 10);
	}

	public static String formatPostalCode(String pc) {
		if (pc != null && pc.trim().length() == 6)
			return pc.substring(0, 3) + " " + pc.substring(3, 6);
		else
			return pc;
	}
	
	/*
	 * Format numbers according to language EN or FR
	 * such as "9999" to "9,999" or
	 * "9999.8" to "9,999.80"
	 */
	public static String formatNumber(String numStr, String lang) {
		double amount = Double.parseDouble(numStr);
		DecimalFormat formatter = new DecimalFormat("#,###");
		if (numStr.indexOf(".") > 0) {
			formatter = new DecimalFormat("#,###.00");
		}
		String result = formatter.format(amount);
		
		if ("FR".equalsIgnoreCase(lang)) {
			result = result.replace(",", " ");
			result = result.replace(".", ",");
		}
		
		return result;
	}
	
	/*
	 * Format SIN number either Integer or Long into 9-digit-long string
	 * with leading zeros if the length of SIN is less then 9.
	 */
	public static String formatSIN(Number sin) {
		String result = "";
		if (sin != null && sin.longValue() > 0) {
			String str = String.valueOf(sin);
			int len = str.length();
			int zeroLen = SIN_MAX_LENGTH - len;
			if (zeroLen > 0) {
				StringBuffer zeroStr = new StringBuffer();
				for (int i = 0; i < zeroLen; i++) {
					zeroStr.append("0");
				}
				result = zeroStr.toString() + str;
			} else {
				result = str;
			}
		}

		return result;
	}

	/*
	 * Trim string.
	 * Return null if the string is like null, "" or " ".
	 * Return "A" if the string is like " A",  "A " or " A ".
	 */
	public static String trimString(Object obj) {
		String theStr = (String) obj;

		String result = null;
		if (theStr != null) {
			String str = theStr.trim();
			if (str.length() > 0) {
				result = str;
			}
		}

		return result;
	}

	/*
	 * Trim string.
	 * Return "" if the string is like null, "" or " ".
	 * Return "A" if the string is like " A",  "A " or " A ".
	 */
	public static String getNotNullStr(Object obj) {
		String result = "";
		if (obj != null) {
			String str = obj.toString().trim();
			if (str.length() > 0) {
				result = str;
			}
		}

		return result;
	}

	/*
	 * Trim string.
	 * Return null if the string is like null, "" or " ".
	 * Return "A" if the string is like " A",  "A " or " A ".
	 */
	public static String getNullStr(Object obj) {
		String result = null;
		if (obj != null) {
			String str = obj.toString().trim();
			if (str.length() > 0) {
				result = str;
			}
		}

		return result;
	}
	
	/*
	 * Check if a string equals another.
	 */
	public static boolean equalString(Object arg0, Object arg1) {
		boolean flag = false;
		String str0 = trimString(arg0);
		String str1 = trimString(arg1);			
		if (str0 == null) {
			if (str1 == null) {
				flag = true;
			}
		} else {
			if (str0.equals(str1)) {
				flag = true;
			}
		}

		return flag;
	}
	
	/*
	 * Return the time stamp
	 * representing the current time.
	 */
/*	public static CustomTimestamp getCurrentTime() {
		// The current time expressed as milliseconds.
		return new CustomTimestamp(System.currentTimeMillis());
	}*/
	
	/*
	 * Calculate the current benefit year
	 * according to system date, 
	 * for example, if today is September 24, 2015, 
	 * the current benefit year should be 2015
	 */	
	public static int calculateCurrentBenefitYear() {
		int today = StringUtils.getIntFromDate(new Date());
		int sysYear = Integer.parseInt(String.valueOf(today).substring(0, 4));
		int sysMonthDay = Integer.parseInt(String.valueOf(today).substring(4, 8));
		
		// One year has been divided into two periods: 
		// January 1 - July 31, August 1 - December 31.
		int curYear = 0;
		if (sysMonthDay >= 101 && sysMonthDay <= 731) {
			curYear = sysYear - 1;
		} else {
			curYear = sysYear;
		}
		
		return curYear;
	}
}
