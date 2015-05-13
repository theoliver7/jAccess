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
	private ArrayList<String> tag = new ArrayList<String>();
	private ArrayList<ArrayList<String>> alleTage = new ArrayList<ArrayList<String>>();

	public ArrayList<ArrayList<String>> totalberechnen(ArrayList<ArrayList<String>> arrayList) throws SQLException, ParseException {
		int tagzähler = 0;
		int zeitzähler = 0;
		System.out.println(arrayList);
		for (int i = 0; i <= arrayList.size(); i++) {
			zeitzähler = 0;
			System.out.println(tagzähler + "    " + zeitzähler);
			try {
				Date beginnMorgen = umwandeln(arrayList.get(tagzähler).get(zeitzähler));
				zeitzähler++;
				Date endeMorgen = umwandeln(arrayList.get(tagzähler).get(zeitzähler));
				zeitzähler++;
				Date beginnNachmittag = umwandeln(arrayList.get(tagzähler).get(zeitzähler));
				zeitzähler++;
				Date endeNachmittag = umwandeln(arrayList.get(tagzähler).get(zeitzähler));
				zeitzähler++;
				long diff = (endeMorgen.getTime() - beginnMorgen.getTime() + endeNachmittag.getTime() - beginnNachmittag.getTime());
			
			// System.out.println(tagzähler);
			System.out.println(leserlichmachen(diff));
			arrayList.get(tagzähler).add(leserlichmachen(diff));
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String datum = dateFormat.format(beginnMorgen);
			arrayList.get(tagzähler).add(0,datum);
			System.out.println(arrayList);
			tagzähler++;
			} catch (Exception e) {
				System.out.println("Helo");
			}
		}

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
			} else {
				alleTage.add(tag);
				ersterTag = umwandeln(daten.get(0)).getTime();
				tag = new ArrayList<String>();
			}
			i++;

		}
		return alleTage;
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
