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

	/**
	 * Rechnet das Total der Arbeitszeiten aus und formatiert die Liste fuer die
	 * Tabelle
	 * 
	 * @param arrayList
	 * @return Zweidimensionale Arraylist
	 * @throws SQLException
	 * @throws ParseException
	 */

	public ArrayList<ArrayList<String>> totalberechnen(ArrayList<ArrayList<Timestamp>> arrayList) throws SQLException, ParseException {
		int tagzaehler = 0;
		int zeitzaehler = 0;
		for (int i = 0; i < arrayList.size(); i++) {
			if (arrayList.get(tagzaehler).size() == 4) {
				zeitzaehler = 0;

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

			} else {
				finaltag.add(datumformatieren(arrayList.get(tagzaehler).get(0)));
				finaltag.add("Zeiten nicht korrekt eingetragen ");

				finalalleTage.add(finaltag);
				finaltag = new ArrayList<String>();

			}
			tagzaehler++;

		}
		return finalalleTage;
	}

	/**
	 * Fuegt die Timestamps des gleichen Tages in eine Arraylist
	 * 
	 * @param list
	 * @return Zweidimensionale Arraylist
	 * @throws SQLException
	 * @throws ParseException
	 */
	public ArrayList<ArrayList<Timestamp>> zeitenorganisieren(List<Timestamp> list) throws SQLException, ParseException {
		int i = 0;
		int position = 0;
		Timestamp ersterTag = list.get(0);
		int arraygroesse = list.size();
		while (i < arraygroesse) {
			if (DateUtils.isSameDay(ersterTag, list.get(0))) {
				tag.add(list.get(position));
				list.remove(position);

			} else {
				alleTage.add(tag);
				ersterTag = list.get(0);
				tag = new ArrayList<Timestamp>();
			}
			i++;
		}
		if (list.size() >= 1) {
			tag.add(list.get(0));
		}
		alleTage.add(tag);
		return alleTage;
	}

	/**
	 * Rechnet die Arbeitszeit aus
	 * 
	 * @param date1
	 * @param date2
	 * @param date3
	 * @param date4
	 * @return
	 */
	public long rechnen(Timestamp date1, Timestamp date2, Timestamp date3, Timestamp date4) {
		long diffInMillies = date2.getTime() - date1.getTime() + date4.getTime() - date3.getTime();
		return diffInMillies;
	}

	/**
	 * Formatiert das Datum nach dem yyyy-MM-dd Format
	 * 
	 * @param timestamp
	 * @return
	 * @throws ParseException
	 */
	public String datumformatieren(Date timestamp) throws ParseException {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		String timestamp_string = formatter.format(timestamp);
		return timestamp_string;
	}

	/**
	 * Formatiert die Zeiten nach dem HH:mm Format
	 * 
	 * @param timestamp
	 * @return
	 * @throws ParseException
	 */
	public String zeitformatieren(Date timestamp) throws ParseException {
		Format formatter = new SimpleDateFormat("HH:mm");
		String timestamp_string = formatter.format(timestamp);
		return timestamp_string;
	}
	/**
	 * Formatiert eine Float Zahl sodass sie lesbar ist
	 * @param diff
	 * @return
	 */
	public String leserlichmachen(long diff) {
		long second = (diff / 1000) % 60;
		long minute = (diff / (1000 * 60)) % 60;
		long hour = (diff / (1000 * 60 * 60)) % 24;
		diff = diff % 1000;
		String time = String.format("%02d:%02d", hour, minute, second, diff);
		return time;
	}
}
