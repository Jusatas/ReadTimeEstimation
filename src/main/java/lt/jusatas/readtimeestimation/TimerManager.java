package lt.jusatas.readtimeestimation;

public class TimerManager {
    private static TimerManager instance;
    private long startTime;
    private long elapsedTime;
    private boolean running;

    private TimerManager() {};

    public static TimerManager getInstance() {
        if (instance == null) {
            instance = new TimerManager();
        }
        return instance;
    }

    public void startTimer() {
        if (!running) {
            running = true;
            startTime = System.currentTimeMillis();
        }
    }

    public void stopTimer() {
        if (running) {
            running = false;
            elapsedTime = System.currentTimeMillis() - startTime;
        }
    }

    public long getElapsedTimeMS() {
        return elapsedTime;
    }
}
