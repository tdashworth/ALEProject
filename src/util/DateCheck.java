package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class DateCheck {

	public static boolean validate(String date) {
    	SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yy");
        try {
            fmt.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }	
	}
}
