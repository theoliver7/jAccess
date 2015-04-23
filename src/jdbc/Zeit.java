package jdbc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DurationFormatUtils;

public class Zeit {
	public String idZeit;
	public String beginnMorgen;
	public String endeMorgen;
	public String beginnNachmittag;
	public String endeMittag;
	public String datum;
	public String total;
	public String arbeiterID;

	public static double totalberechnen(String time1, String time2, String time3, String time4) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date date1 = format.parse(time1);
		Date date2 = format.parse(time2);
		Date date3 = format.parse(time3);
		Date date4 = format.parse(time4);
		long difference = (date2.getTime() - date1.getTime()) + (date4.getTime() - date3.getTime());
		;
		System.out.println("Duration: " + DurationFormatUtils.formatDuration(difference, "HH:mm"));
		return difference;
	}

	public String getIdZeit() {
		return idZeit;
	}

	public void setIdZeit(String idZeit) {
		this.idZeit = idZeit;
	}

	public String getBeginnMorgen() {
		return beginnMorgen;
	}

	public void setBeginnMorgen(String beginnMorgen) {
		this.beginnMorgen = beginnMorgen;
	}

	public String getEndeMorgen() {
		return endeMorgen;
	}

	public void setEndeMorgen(String endeMorgen) {
		this.endeMorgen = endeMorgen;
	}

	public String getBeginnNachmittag() {
		return beginnNachmittag;
	}

	public void setBeginnNachmittag(String beginnNachmittag) {
		this.beginnNachmittag = beginnNachmittag;
	}

	public String getEndeMittag() {
		return endeMittag;
	}

	public void setEndeMittag(String endeMittag) {
		this.endeMittag = endeMittag;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getArbeiterID() {
		return arbeiterID;
	}

	public void setArbeiterID(String arbeiterID) {
		this.arbeiterID = arbeiterID;
	}

}
