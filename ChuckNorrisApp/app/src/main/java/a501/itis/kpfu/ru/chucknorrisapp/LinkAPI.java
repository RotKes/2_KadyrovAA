package a501.itis.kpfu.ru.chucknorrisapp;

import a501.itis.kpfu.ru.chucknorrisapp.joke.Joke;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Амир on 23.12.2016.
 */

public interface LinkAPI {
    @GET("/jokes/random")
    Call<Joke> getJoke();
}
