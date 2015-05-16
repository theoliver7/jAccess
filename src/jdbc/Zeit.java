package jdbc;

import java.sql.SQLException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DateUtils;

public class Zeit {
	private ArrayList<Date> tag = new ArrayList<Date>();
	private ArrayList<ArrayList<Date>> alleTage = new ArrayList<ArrayList<Date>>();
	private ArrayList<String> finaltag = new ArrayList<String>();
	private ArrayList<ArrayList<String>> finalalleTage = new ArrayList<ArrayList<String>>();

	public ArrayList<ArrayList<String>> totalberechnen(ArrayList<ArrayList<Date>> arrayList) throws SQLException,
			ParseException {
		int tagzaehler = 0;
		int zeitzaehler = 0;
		System.out.println(arrayList.size());
		for (int i = 0; i < arrayList.size(); i++) {
			zeitzaehler = 0;
			System.out.println(tagzaehler + "    " + zeitzaehler);

			Date beginnMorgen = arrayList.get(tagzaehler).get(zeitzaehler);
			zeitzaehler++;
			Date endeMorgen = arrayList.get(tagzaehler).get(zeitzaehler);
			zeitzaehler++;
			Date beginnNachmittag = arrayList.get(tagzaehler).get(zeitzaehler);
			zeitzaehler++;
			Date endeNachmittag = arrayList.get(tagzaehler).get(zeitzaehler);
			zeitzaehler++;
			String datum = datumformatieren(beginnMorgen);
			String total = leserlichmachen(rechnen(beginnMorgen, endeMorgen,
					beginnNachmittag, endeNachmittag));
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

	public ArrayList<ArrayList<Date>> zeitenorganisieren(List<Date> list)
			throws SQLException, ParseException {
		int i = 0;
		int position = 0;
		Date ersterTag = list.get(0);
		int arraygroesse = list.size();
		while (i <= arraygroesse) {
			if (DateUtils.isSameDay(ersterTag, list.get(0))) {
				tag.add(list.get(position));
				list.remove(position);
			} else {
				alleTage.add(tag);
				ersterTag = list.get(0);
				tag = new ArrayList<Date>();
			}
			i++;

		}
		return alleTage;
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

	public long rechnen(Date date1, Date date2, Date date3, Date date4) {
		long diffInMillies = date2.getTime() - date1.getTime()
				+ date4.getTime() - date3.getTime();
		return diffInMillies;
	}

	public String leserlichmachen(long diff) {
		long second = (diff / 1000) % 60;
		long minute = (diff / (1000 * 60)) % 60;
		long hour = (diff / (1000 * 60 * 60)) % 24;
		diff = diff % 1000;
		String time = String.format("%02d:%02d:%02d:%d", hour, minute, second,
				diff);
		return time;

	}

}
