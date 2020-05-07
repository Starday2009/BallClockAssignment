package clockFiles;

import com.google.gson.Gson;

import java.util.*;

public class Utils {
  private Deque<Integer> minRailQueue = new ArrayDeque<>();
  private Deque<Integer> fiveMinRailQueue = new ArrayDeque<>();
  private Deque<Integer> hourRailQueue = new ArrayDeque<>();
  private Deque<Integer> baggageQueue = new ArrayDeque<>();
  private Object[] firstStateRail;

  private static final int ONE_MIN_RAIL_CAP = 5;
  private static final int FIVE_MIN_RAIL_CAP = 12;
  private static final int HOUR_RAIL_CAP = 12;

  private Gson gson = new Gson();

  void printJsonMinutes() {
    System.out.println(getJson());
  }

  public String getJson() {
    Map<String, Object[]> map = new LinkedHashMap<>();
    map.put("Minutes", minRailQueue.toArray());
    map.put("FiveMin", fiveMinRailQueue.toArray());
    map.put("Hours", hourRailQueue.toArray());
    map.put("Main", baggageQueue.toArray());
    return gson.toJson(map);
  }

  void fillMain(int ballsCount) {
    for (int i = 1; i <= ballsCount; i++) {
      baggageQueue.add(i);
    }
    firstStateRail = baggageQueue.toArray();
  }

  public double runDays(int ballsCount) {
    int cycles = 1;
    do {
      fillRail();
      if (baggageQueue.size() == ballsCount
          && Arrays.equals(firstStateRail, baggageQueue.toArray())) {
        break;
      }
    } while (cycles++ > 0);

    return cycles / (60 * 24);
  }

  public void fillRail() {
    minRailQueue.offer(baggageQueue.poll());
    if (minRailQueue.size() >= ONE_MIN_RAIL_CAP) {
      fillFiveRail();
    }
  }

  private void fillFiveRail() {
    fiveMinRailQueue.offer(minRailQueue.pollLast());
    do {
      baggageQueue.offer(minRailQueue.poll());
    } while (minRailQueue.size() > 0);

    if (fiveMinRailQueue.size() >= FIVE_MIN_RAIL_CAP) {
      fillHourRail();
    }
  }

  private void fillHourRail() {
    hourRailQueue.offer(fiveMinRailQueue.pollLast());
    do {
      baggageQueue.offer(fiveMinRailQueue.poll());
    } while (fiveMinRailQueue.size() > 0);

    if (hourRailQueue.size() >= HOUR_RAIL_CAP) {
      do {
        baggageQueue.offer(hourRailQueue.poll());
      } while (hourRailQueue.size() > 0);
    }
  }
}
