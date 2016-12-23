package a501.itis.kpfu.ru.chucknorrisapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by Амир on 23.12.2016.
 */

public class AsyncContainerFragment extends Fragment {

    TaskListener mTaskListener;
    MyAsyncTask asyncTask;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mTaskListener = (TaskListener) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mTaskListener = (TaskListener) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void startAsyncTask() {
        asyncTask = new MyAsyncTask(mTaskListener);
        asyncTask.execute();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mTaskListener = null;
    }
}
