package junit;

import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import jdbc.Zeit;
import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ZeitRechnenTest {
	
	private static Zeit zeit;
	private boolean result;
	private String format;
	private long timestamp;

	@BeforeClass
	public static void setUp() throws Exception {
		ZeitRechnenTest.zeit = new Zeit();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		ZeitRechnenTest.zeit = null;
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testRechnen() {
		Long rechnenResult = new Long(14400);
		Assert.assertEquals(zeit.rechnen(new Timestamp(1432807200), new Timestamp(1432814400), new Timestamp(1432821600), new Timestamp(1432828800)), rechnenResult.longValue());
		rechnenResult = null;
	}
}
