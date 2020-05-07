import clockFiles.BallClock;
import clockFiles.Utils;
import jdk.nashorn.internal.runtime.regexp.joni.constants.Arguments;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Scanner;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;

public class BallClockTest {
  private BallClock ballClock = new BallClock();
  private Utils utils = new Utils();
  private final int EXITCODE_OK = 0;
  private final int EXITCODE_EXCEPTION = 1;

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

  private static String AFTER_28_MINUTE_28B =
      "{\"Minutes\":[26,27,28],\"FiveMin\":[5,10,15,20,25],\"Hours\":[],\"Main\":[1,2,3,4,6,7,8,9,11,12,13,14,16,17,18,19,21,22,23,24]}";
  private static String AFTER_1_MINUTE_39B =
      "{\"Minutes\":[1],\"FiveMin\":[],\"Hours\":[],\"Main\":[2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39]}";
  private static String AFTER_65_MINUTE_127B =
      "{\"Minutes\":[],\"FiveMin\":[65],\"Hours\":[60],\"Main\":[66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,1,2,3,4,6,7,8,9,11,12,13,14,16,17,18,19,21,22,23,24,26,27,28,29,31,32,33,34,36,37,38,39,41,42,43,44,46,47,48,49,51,52,53,54,56,57,58,59,5,10,15,20,25,30,35,40,45,50,55,61,62,63,64]}";

  @BeforeTest
  public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
  }

  // clock for min
  @Test
  public void test_rail_after_28m_with_28b_happyPath() {
    String input = "m\n28 28";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ballClock = new BallClock();
    int resultCode = ballClock.start();
    String resultRail = ballClock.getStringResult();

    Assert.assertEquals(EXITCODE_OK, resultCode);
    Assert.assertEquals(AFTER_28_MINUTE_28B, resultRail);
  }

  @Test
  public void test_rail_after_1m_with_39b_happyPath() {
    String input = "m\n1 39";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ballClock = new BallClock();
    int resultCode = ballClock.start();
    String resultRail = ballClock.getStringResult();

    Assert.assertEquals(EXITCODE_OK, resultCode);
    Assert.assertEquals(AFTER_1_MINUTE_39B, resultRail);
  }

  @Test
  public void test_rail_after_65m_with_127b_happyPath() {
    String input = "m\n65 127";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ballClock = new BallClock();
    int resultCode = ballClock.start();
    String resultRail = ballClock.getStringResult();

    Assert.assertEquals(EXITCODE_OK, resultCode);
    Assert.assertEquals(AFTER_65_MINUTE_127B, resultRail);
  }
  // clock for days
  @Test
  public void input_65Balls_expected_70Days_happyPath() {
    String input = "d\n65";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ballClock = new BallClock();

    int resultCode = ballClock.start();
    double days = ballClock.getDays();
    String daysInString = String.format("%.0f", days);

    Assert.assertEquals(EXITCODE_OK, resultCode);
    Assert.assertEquals("70", daysInString);
  }
  @Test
  public void input_30Balls_expected_13Days_happyPath() {
    String input = "d\n30";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ballClock = new BallClock();

    int resultCode = ballClock.start();
    double days = ballClock.getDays();
    String daysInString = String.format("%.0f", days);

    Assert.assertEquals(EXITCODE_OK, resultCode);
    Assert.assertEquals("13", daysInString);
  }
}
