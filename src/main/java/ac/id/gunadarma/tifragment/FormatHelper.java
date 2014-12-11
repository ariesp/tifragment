package ac.id.gunadarma.tifragment;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.joda.time.Days;
import org.joda.time.LocalDate;

public class FormatHelper {

	public static String formatDate(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
		try {
			return dateFormat.format(dateFormat.parse(date));
		} catch (ParseException e) {			
			throw new RuntimeException(e);
		}		
	}
	
	public static String formatDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
		return dateFormat.format(date);
	}
	
	public static Date parseDate(String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
		return dateFormat.parse(date);
	}
	
	public static String formatNumber(String number) {
		DecimalFormat decimalFormat = new DecimalFormat("#.###");
		return decimalFormat.format(Double.valueOf(number));
	}
	
	public static int daysBetween(String startDate, String endDate) throws ParseException {
		return Days.daysBetween(new LocalDate(parseDate(startDate)), new LocalDate(parseDate(endDate))).getDays();
	}
}
