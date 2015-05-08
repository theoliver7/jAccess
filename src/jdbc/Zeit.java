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

	public List<String> totalberechnen(List<String> daten) throws SQLException, ParseException {
		int i = 0;
		List<String> totalarbeitszeiten = new ArrayList<String>();

		if (daten.size() % 4 == 0) {
			while (i < daten.size()) {

				Date beginnMorgen = umwandeln(daten.get(i));
				i++;
				Date endeMorgen = umwandeln(daten.get(i));
				i++;
				Date beginnNachmittag = umwandeln(daten.get(i));
				i++;
				Date endeNachmittag = umwandeln(daten.get(i));
				i++;
				long diff = (endeMorgen.getTime() - beginnMorgen.getTime() + endeNachmittag.getTime() - beginnNachmittag.getTime());
				long second = (diff / 1000) % 60;
				long minute = (diff / (1000 * 60)) % 60;
				long hour = (diff / (1000 * 60 * 60)) % 24;
				diff = diff % 1000;

				String time = String.format("%02d:%02d:%02d:%d", hour, minute, second, diff);
				totalarbeitszeiten.add(time);
			}
		} else {
			System.out.println("Zu wenig eingetragene Arbeitszeiten");
		}
		// System.out.println(totalarbeitszeiten);
		return totalarbeitszeiten;
	}

	public List<String> zeitenorganisieren(List<String> daten) throws SQLException, ParseException {
		int i = 1;
		int position = 0;
		List<String> tag = new ArrayList<String>();
		long beginn = umwandeln(daten.get(0)).getTime();
		long diff = (umwandeln(daten.get(3)).getTime() - umwandeln(daten.get(0)).getTime());
		System.out.println(daten.get(0));

		while (i <= 10) {
			System.out.println("Resultat:"+umwandeln(daten.get(position)).getTime());
			long peter=umwandeln(daten.get(position)).getTime()-beginn;
			System.out.println("Peter: "+peter);
			if ( umwandeln(daten.get(position)).getTime()-beginn < 86400000) {
				System.out.println("Time:"+ daten.get(0));
				tag.add(daten.get(position));
				daten.remove(position);
				System.out.println("Offiziel"+tag);
				System.out.println(daten);
			} else {
				System.out.println("niet");
//				beginn = umwandeln(daten.get(0)).getTime();
			}
			i++;
			System.out.println();
			System.out.println("______________________________________________________________________________________");
			System.out.println();
		}
		return tag;
	}

	public String getIdZeit() {
		return idZeit;
	}

	public void setIdZeit(String idZeit) {
		this.idZeit = idZeit;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp2) {
		this.timestamp = timestamp2;
	}

	public Date umwandeln(String timestamp_string) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date umgewandeltertimestamp = dateFormat.parse(timestamp_string);
		return umgewandeltertimestamp;

	}

}
