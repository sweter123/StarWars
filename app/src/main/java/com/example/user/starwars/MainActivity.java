package com.example.user.starwars;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    public static final String HTTP_SWAPI_CO_API = "http://swapi.co/api/";
    @BindView(R.id.peopleRecycleView)
    RecyclerView peopleRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ArrayList<Person> people = new ArrayList<>();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HTTP_SWAPI_CO_API)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        StarWarsService service = retrofit.create(StarWarsService.class);
        Call<ResultSet> peopl = service.listPeople();
        peopleRecycleView.setLayoutManager(new LinearLayoutManager(this));
        final PeopleAdapter adapter = new PeopleAdapter(people, getLayoutInflater());
        peopleRecycleView.setAdapter(adapter);
        peopl.enqueue(new Callback<ResultSet>() {
            @Override
            public void onResponse(Call<ResultSet> call, Response<ResultSet> response) {
                if(response.isSuccessful()){
                    Timber.i(response.body().getCount());
                    ArrayList<Person> people = new ArrayList<>(response.body().getResults());
                    Timber.i(people.size()+"");
                    adapter.add(people);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<ResultSet> call, Throwable t) {
                Timber.e(t,t.getMessage());
            }
        });
    }
}
