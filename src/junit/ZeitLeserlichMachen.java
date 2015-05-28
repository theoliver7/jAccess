package junit;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

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
public class ZeitLeserlichMachen {
	
	private static Zeit zeit;
	private long timestamp;
	private boolean ergebnis;
	private String format;
	
	public ZeitLeserlichMachen(long timestamp, String format, boolean ergebnis) {
		this.timestamp = timestamp;
		this.format = format;
		this.ergebnis = ergebnis;
	}

	@BeforeClass
	public static void setUp() throws Exception {
		ZeitLeserlichMachen.zeit = new Zeit();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		ZeitLeserlichMachen.zeit = null;
	}

	@Parameters
	public static Collection<Object[]> valuesLeserlichmachen() {
		return Arrays.asList(new Object[][] {
				{1234, "01:00:01", false}
		});
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testLeserlichmachen() {
		Assert.assertEquals(zeit.leserlichmachen(timestamp).equals(format), ergebnis);
	}
}
