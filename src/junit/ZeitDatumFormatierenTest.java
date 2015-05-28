package junit;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import jdbc.Zeit;
import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ZeitDatumFormatierenTest {
	
	private static Zeit zeit;
	private long timestamp;
	private String format;
	private boolean bool;
	
	public ZeitDatumFormatierenTest(long timestamp, String format, boolean bool) {
		this.format = format;
		this.timestamp = timestamp;
		this.bool = bool;
	}
	
	@BeforeClass
	public static void setUp() throws Exception {
		ZeitDatumFormatierenTest.zeit = new Zeit();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		ZeitDatumFormatierenTest.zeit = null;
	}
	
	@Parameters
	public static Collection<Object[]> values() {
		return Arrays.asList(new Object[][] {
				{0, "1970-01-01", true}, {0, "2015-01-01", false}
		});
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDatumformatieren() {
		try {
			Assert.assertEquals(zeit.datumformatieren(new Timestamp(timestamp)).equals(format), bool);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
