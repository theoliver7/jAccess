package jdbc;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Zeit {
	public String idZeit;
	public Timestamp timestamp;
	private ArrayList<String> tag = new ArrayList<String>();
	private ArrayList<ArrayList<String>> alleTage = new ArrayList<ArrayList<String>>();

	public ArrayList<ArrayList<String>> totalberechnen(ArrayList<ArrayList<String>> arrayList) throws SQLException, ParseException {
		Date beginnMorgen = umwandeln(arrayList.get(0).get(0));
		
		Date endeMorgen = umwandeln(arrayList.get(0).get(1));
		
		Date beginnNachmittag = umwandeln(arrayList.get(0).get(2));
		Date endeNachmittag = umwandeln(arrayList.get(0).get(3));
		long diff = (endeMorgen.getTime() - beginnMorgen.getTime() + endeNachmittag.getTime() - beginnNachmittag.getTime());
		long second = (diff / 1000) % 60;
		long minute = (diff / (1000 * 60)) % 60;
		long hour = (diff / (1000 * 60 * 60)) % 24;
		diff = diff % 1000;

		String time = String.format("%02d:%02d:%02d:%d", hour, minute, second, diff);
	
		arrayList.get(0).add(time);
		System.out.println(arrayList);
		return arrayList;
	}

	public ArrayList<ArrayList<String>> zeitenorganisieren(List<String> daten) throws SQLException, ParseException {
		int i = 0;
		int position = 0;
		long ersterTag = umwandeln(daten.get(0)).getTime();
		int arraygrösse = daten.size();
		while (i <= arraygrösse) {
			if (umwandeln(daten.get(position)).getTime() - ersterTag < 72000000) {
				tag.add(daten.get(position));
				daten.remove(position);
				System.out.println("Tag Array:" + tag);
				System.out.println(daten);
			} else {
				alleTage.add(tag);
				System.out.println("Nächster Tag");
				System.out.println("Alle Tage: " + alleTage);
				ersterTag = umwandeln(daten.get(0)).getTime();
				tag = new ArrayList<String>();
			}
			i++;
			System.out.println();
			System.out.println("______________________________________________________________________________________");
			System.out.println();
		}
		return alleTage;
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

	public String leserlichmachen(long diff) {
		long second = (diff / 1000) % 60;
		long minute = (diff / (1000 * 60)) % 60;
		long hour = (diff / (1000 * 60 * 60)) % 24;
		diff = diff % 1000;

		String time = String.format("%02d:%02d:%02d:%d", hour, minute, second, diff);
		return time;

	}

}
