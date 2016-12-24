package a501.itis.kpfu.ru.chucknorrisapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TaskListener {
    private TextView tv;
    private Button btn;
    private ProgressBar progressBar;

    private static final String KEY_CURRENT_PROGRESS_VISIBILITY = "current_progress";
    private static final String KEY_CURRENT_TEXT = "current_joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.text);
        btn = (Button) findViewById(R.id.button);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        if (savedInstanceState == null) {
            progressBar.setVisibility(View.GONE);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAsyncContainer().startAsyncTask();
            }
        });

        if (savedInstanceState != null) {
            switch (savedInstanceState.getInt(KEY_CURRENT_PROGRESS_VISIBILITY)) {
                case View.VISIBLE :
                    progressBar.setVisibility(View.VISIBLE);
                    break;
                case View.GONE :
                    progressBar.setVisibility(View.GONE);
                    break;
                default:
                    progressBar.setVisibility(View.INVISIBLE);
            }
            tv.setText(savedInstanceState.getString(KEY_CURRENT_TEXT));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CURRENT_PROGRESS_VISIBILITY, progressBar.getVisibility());
        outState.putString(KEY_CURRENT_TEXT, tv.getText().toString());
    }

    private AsyncContainerFragment getAsyncContainer() {
        AsyncContainerFragment fragment = (AsyncContainerFragment) getFragmentManager()
                .findFragmentByTag(AsyncContainerFragment.class.getName());
        if (fragment == null) {
            fragment = new AsyncContainerFragment();
            fragment.onAttach(this);
            getFragmentManager().beginTransaction()
                    .add(new AsyncContainerFragment(), AsyncContainerFragment.class.getName())
                    .commit();
        }
        return fragment;
    }

    @Override
    public void onTaskFinish(String joke) {
        TextView newTextView = (TextView) findViewById(R.id.text);
        ProgressBar newProgressBar = (ProgressBar) findViewById(R.id.progress);
        newProgressBar.setVisibility(View.GONE);
        newTextView.setText(joke);
    }

    @Override
    public void onTaskStarted() {
        progressBar = (ProgressBar) findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
    }
}
