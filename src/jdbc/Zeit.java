package jdbc;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

public class Zeit {
	private ArrayList<String> finaltag = new ArrayList<String>();
	private ArrayList<ArrayList<String>> finalalleTage = new ArrayList<ArrayList<String>>();
	private ArrayList<Timestamp> tag = new ArrayList<Timestamp>();
	private ArrayList<ArrayList<Timestamp>> alleTage = new ArrayList<ArrayList<Timestamp>>();

	public ArrayList<ArrayList<String>> totalberechnen(ArrayList<ArrayList<Timestamp>> arrayList) throws SQLException, ParseException {
		int tagzaehler = 0;
		int zeitzaehler = 0;
		System.out.println(arrayList.size());
		for (int i = 0; i < arrayList.size(); i++) {
			zeitzaehler = 0;
			System.out.println(tagzaehler + "    " + zeitzaehler);

			Timestamp beginnMorgen = arrayList.get(tagzaehler).get(zeitzaehler);
			zeitzaehler++;
			Timestamp endeMorgen = arrayList.get(tagzaehler).get(zeitzaehler);
			zeitzaehler++;
			Timestamp beginnNachmittag = arrayList.get(tagzaehler).get(zeitzaehler);
			zeitzaehler++;
			Timestamp endeNachmittag = arrayList.get(tagzaehler).get(zeitzaehler);
			zeitzaehler++;
			String datum = datumformatieren(beginnMorgen);
			String total = leserlichmachen(rechnen(beginnMorgen, endeMorgen, beginnNachmittag, endeNachmittag));
			finaltag.add(0, datum);
			finaltag.add(1, zeitformatieren(beginnMorgen));
			finaltag.add(2, zeitformatieren(endeMorgen));
			finaltag.add(3, zeitformatieren(beginnNachmittag));
			finaltag.add(4, zeitformatieren(endeNachmittag));
			finaltag.add(5, total);
			finalalleTage.add(finaltag);
			finaltag = new ArrayList<String>();
			System.out.println(total);
			System.out.println(finalalleTage);
			tagzaehler++;
		}

		return finalalleTage;
	}

	public ArrayList<ArrayList<Timestamp>> zeitenorganisieren(List<Timestamp> list) throws SQLException, ParseException {
		int i = 0;
		int position = 0;
		Timestamp ersterTag = list.get(0);
		int arraygroesse = list.size();
		if (arraygroesse % 2 != 0) {
			while (i <= arraygroesse) {
				if (DateUtils.isSameDay(ersterTag, list.get(0))) {
					tag.add(list.get(position));
					list.remove(position);
				} else {
					if (tag.size() == 4) {
						alleTage.add(tag);
						ersterTag = list.get(0);
						tag = new ArrayList<Timestamp>();
					}
				}
				i++;
			}
		}
		return alleTage;
	}

	public long rechnen(Timestamp date1, Timestamp date2, Timestamp date3, Timestamp date4) {
		long diffInMillies = date2.getTime() - date1.getTime() + date4.getTime() - date3.getTime();
		return diffInMillies;
	}

	public String datumformatieren(Date timestamp) throws ParseException {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		String timestamp_string = formatter.format(timestamp);
		return timestamp_string;
	}

	public String zeitformatieren(Date timestamp) throws ParseException {
		Format formatter = new SimpleDateFormat("HH:mm");
		String timestamp_string = formatter.format(timestamp);
		return timestamp_string;
	}

	// public Date umwandeln(String timestamp_string) throws ParseException {
	// SimpleDateFormat dateFormat = new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	// Date umgewandeltertimestamp = dateFormat.parse(timestamp_string);
	// return umgewandeltertimestamp;
	// }

	public String leserlichmachen(long diff) {
		long second = (diff / 1000) % 60;
		long minute = (diff / (1000 * 60)) % 60;
		long hour = (diff / (1000 * 60 * 60)) % 24;
		diff = diff % 1000;
		String time = String.format("%02d:%02d:%02d:%d", hour, minute, second, diff);
		return time;
	}
}
