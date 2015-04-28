package jdbc;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang3.time.DurationFormatUtils;

public class Zeit {
	public String idZeit;
	public Timestamp timestamp;
	public String arbeiterID="45-459-5415";
	public static double totalberechnen(String time1, String time2, String time3, String time4) throws ParseException {
		
	
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date date1 = format.parse(time1);
		Date date2 = format.parse(time2);
		Date date3 = format.parse(time3);
		Date date4 = format.parse(time4);
		long difference = (date2.getTime() - date1.getTime()) + (date4.getTime() - date3.getTime());
		System.out.println("Duration: " + DurationFormatUtils.formatDuration(difference, "HH:mm"));
		return difference;
	}

	public String getIdZeit() {
		return idZeit;
	}

	public void setIdZeit(String idZeit) {
		this.idZeit = idZeit;
	}

	public String getArbeiterID() {
		return arbeiterID;
	}

	public void setArbeiterID(String arbeiterID) {
		this.arbeiterID = arbeiterID;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp2) {
		this.timestamp = timestamp2;
	}

}
