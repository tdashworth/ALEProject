package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatter {

	public static boolean validateDate(String date) {
    	SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yy");
        try {
            fmt.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }	
	}
	
	public static String dateFormat(Date d) {
		DateFormat df = new SimpleDateFormat("dd-MMM-yy");
		return df.format(d);
	}
	
	public static String moneyFormat(Double price) {
		String strPrice = price.toString();
		int decimalPoints = strPrice.length() - strPrice.indexOf(".")-1;
		if(decimalPoints < 2) {
			strPrice += "0";
		} else if (decimalPoints > 2) {
			System.out.println(strPrice);
		}
		
		return strPrice;
	}
}
