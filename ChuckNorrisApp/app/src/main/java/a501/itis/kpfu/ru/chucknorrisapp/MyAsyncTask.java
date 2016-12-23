package a501.itis.kpfu.ru.chucknorrisapp;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import a501.itis.kpfu.ru.chucknorrisapp.joke.Joke;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Амир on 23.12.2016.
 */

public class MyAsyncTask extends AsyncTask<Void, Void, String> {
    TaskListener mTaskListener;
    String temp = null;

    public MyAsyncTask(TaskListener taskListener) {
        this.mTaskListener = taskListener;
    }

    @Override
    protected String doInBackground(Void... voids) {

        try {
            Thread.sleep(2400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final String URL = "http://api.icndb.com";
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(URL)
                .build();
        LinkAPI interfaceAPI = retrofit.create(LinkAPI.class);

        Call<Joke> call = interfaceAPI.getJoke();
        try {
            Response<Joke> response = call.execute();
            if (response.isSuccessful()) {
                Joke joke = response.body();
                temp = joke.getValue().getJoke();
            }else {
                temp = "Error: " + response.code();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    @Override
    protected void onPreExecute() {
        mTaskListener.onTaskStarted();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String joke) {
        super.onPostExecute(joke);
        mTaskListener.onTaskFinish(joke);
    }
}
