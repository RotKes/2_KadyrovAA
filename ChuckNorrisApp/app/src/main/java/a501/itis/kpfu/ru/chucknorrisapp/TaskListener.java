package a501.itis.kpfu.ru.chucknorrisapp;

/**
 * Created by Амир on 23.12.2016.
 */

public interface TaskListener {
    void onTaskFinish(String joke);
    void onTaskStarted();
}
