package jdbc;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Zeit {
	public String idZeit;
	public Timestamp timestamp;
	public String arbeiterID = "45-459-5415";

	public List<String> totalberechnen(List<String> daten) throws SQLException, ParseException {
		int i = 0;
		List<String> totalarbeitszeiten = new ArrayList<String>();
		if (daten.size() % 4 == 0) {
			while (i < daten.size()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				Date beginnMorgen = dateFormat.parse(daten.get(i));
				i++;
				Date endeMorgen = dateFormat.parse(daten.get(i));
				i++;
				Date beginnNachmittag = dateFormat.parse(daten.get(i));
				i++;
				Date endeNachmittag = dateFormat.parse(daten.get(i));
				i++;
				System.out.println(beginnMorgen);
				System.out.println(endeMorgen);
				System.out.println(beginnNachmittag);
				System.out.println(endeNachmittag);
				long diff = (endeMorgen.getTime() - beginnMorgen.getTime() + endeNachmittag.getTime() - beginnNachmittag.getTime());

				long second = (diff / 1000) % 60;
				long minute = (diff / (1000 * 60)) % 60;
				long hour = (diff / (1000 * 60 * 60)) % 24;
				diff = diff % 1000;

				String time = String.format("%02d:%02d:%02d:%d", hour, minute, second, diff);
				System.out.println(time);
				totalarbeitszeiten.add(time);
			}
		} else {
			System.out.println("Zu wenig eingetragene Arbeitszeiten");
		}
		System.out.println(totalarbeitszeiten);
		return totalarbeitszeiten;
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
