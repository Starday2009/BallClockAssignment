package clockFiles;

import com.google.common.base.Stopwatch;
import static java.lang.System.out;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class BallClock {

  private Scanner scanner = new Scanner(System.in);
  private Utils utils = new Utils();
  private String willCount;
  private int minutesCount;
  private int ballsCount;
  private double days;
  private int cycles = 1;
  int[] vars = new int[2];

  public int start() {
    out.println("Enter m to count minutes or enter d to count days");
    willCount = scanner.nextLine();

    switch (willCount) {
      case "m":
        handleMinutes();
        return 0;
      case "d":
        handleDays();
        return 0;
      default:
        out.println("You enter wrong value");
        return 1;
    }
  }

  public int handleMinutes() {
    willCount = "minutes";
    out.println("We will count: " + willCount);
    out.println("Please, enter minutes count and balls(minutes value between 27 and 127)");
    for (int i = 0; i < vars.length; i++) {
      vars[i] = scanner.nextInt();
    }
    minutesCount = vars[0];
    ballsCount = vars[1];
    if (ballsCount < 27 || ballsCount > 127) {
      out.println("You enter wrong value, balls should be between 27 and 127");
      return 1;
    }
    out.println(
        "Ok, we will count value for " + minutesCount + " minutes and " + ballsCount + " balls..");
    calculateMinutes();
    return 0;
  }

  public int calculateMinutes() {
    utils.fillMain(ballsCount);
    Stopwatch stopwatch = Stopwatch.createStarted();
    while (cycles < minutesCount + 1) {
      utils.fillRail();
      cycles++;
    }
    utils.printJsonMinutes();
    printStopwatch(
        "Completed in %s milliseconds (%.3f seconds)",
        stopwatch.elapsed(TimeUnit.MILLISECONDS), stopwatch.elapsed(TimeUnit.MILLISECONDS) / 1000d);
    return minutesCount;
  }

  private void printStopwatch(String s, long elapsed, double v) {
    out.println(String.format(s, elapsed, v));
  }

  public String getStringResult(){
    return utils.getJson();
  }

  public int handleDays() {
    willCount = "days";
    out.println("We will count : " + willCount);
    out.println("Please, enter balls count(value between 27 and 127)");
    ballsCount = Integer.parseInt(scanner.nextLine());

    if (ballsCount < 27 || ballsCount > 127) {
      out.println("You enter wrong value, balls should be between 27 and 127");
      return 1;
    }

    out.println("Ok, we will count days for " + ballsCount + " balls..");
    calculateDays();
    return 0;
  }

  public double getDays() {
    return days;
  }
  public double calculateDays() {
    utils.fillMain(ballsCount);
    Stopwatch stopwatch = Stopwatch.createStarted();
     days = utils.runDays(ballsCount);
    stopwatch.stop();
    out.println(String.format("%d balls cycle after %.0f days.", ballsCount, days));
    printStopwatch(
        "Completed in %s milliseconds (%.3f seconds)",
        stopwatch.elapsed(TimeUnit.MILLISECONDS), stopwatch.elapsed(TimeUnit.MILLISECONDS) / 1000d);
    return days;
  }
}
