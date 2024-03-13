package lt.jusatas.readtimeestimation;

public class ManagerFactory {
    public static TimerManager createTimerManager() {
        return TimerManager.getInstance();
    }
    public static ParagraphManager createParagraphManger(String fileName) {
        return ParagraphManager.getInstance(fileName);
    }
}
