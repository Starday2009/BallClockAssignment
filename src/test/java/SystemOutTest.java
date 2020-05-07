import clockFiles.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class SystemOutTest {
  private BallClock ballClock = new BallClock();
  private final int EXITCODE_OK = 0;
  private final int EXITCODE_EXCEPTION = 1;

  // start()
  @Test()
  public void enter_28min_and_28balls_for_min_happyPath() {
    String input = "m\n28 28";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ballClock = new BallClock();

    int resultCode = ballClock.start();

    Assert.assertEquals(EXITCODE_OK, resultCode);
  }

  @Test
  public void enter_28balls_for_days_happyPath() {
    String input = "d\n28";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ballClock = new BallClock();

    int resultCode = ballClock.start();

    Assert.assertEquals(EXITCODE_OK, resultCode);
  }
  // handleDays()
  @Test
  public void test_enter_28balls_for_dayCounter_happyPath() {
    String input = "28";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ballClock = new BallClock();

    int resultCode = ballClock.handleDays();

    Assert.assertEquals(EXITCODE_OK, resultCode);
  }

  @Test
  public void test_enter_wrong_128balls_for_dayCounter_sadPath() {
    String input = "128";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ballClock = new BallClock();

    int resultCode = ballClock.handleDays();

    Assert.assertEquals(EXITCODE_EXCEPTION, resultCode);
  }

  @Test
  public void test_enter_wrong_26balls_for_dayCounter_sadPath() {
    String input = "26";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ballClock = new BallClock();

    int resultCode = ballClock.handleDays();

    Assert.assertEquals(EXITCODE_EXCEPTION, resultCode);
  }

  @Test
  public void test_enter_wrong_value_in_select_month_or_days_sadPath() {
    String input = "f";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ballClock = new BallClock();

    int resultCode = ballClock.start();

    Assert.assertEquals(EXITCODE_EXCEPTION, resultCode);
  }

  //  handleMinutes()
  @Test
  public void test_minutes_method_28min_28balls_happyPath() {
    String input = "28 28";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ballClock = new BallClock();

    int resultCode = ballClock.handleMinutes();

    Assert.assertEquals(EXITCODE_OK, resultCode);
  }

  @Test
  public void test_minutes_method_21min_wrong_27balls_sadPath() {
    String input = "21 26";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ballClock = new BallClock();

    int resultCode = ballClock.handleMinutes();

    Assert.assertEquals(EXITCODE_EXCEPTION, resultCode);
  }

  @Test
  public void test_minutes_method_21min_wrong_128balls_sadPath() {
    String input = "21 128";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ballClock = new BallClock();

    int resultCode = ballClock.handleMinutes();

    Assert.assertEquals(EXITCODE_EXCEPTION, resultCode);
  }
}
