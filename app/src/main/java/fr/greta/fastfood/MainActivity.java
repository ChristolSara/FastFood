package fr.greta.fastfood;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

import fr.greta.fastfood.model.Restaurant;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//afficher limage pour 2000 s

        ActionBar actionBar1 = getSupportActionBar();
        actionBar1.hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, SplashActivity.class));
                finish();
            }
        }, 2000);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Restautrants list");
        //get the restaurant list from the json
        List<Restaurant> restaurantList = getRestaurantData();
        //injevtion first view
        initRecyclerView(restaurantList);

    }

    private List<Restaurant> getRestaurantData() {
        //lis le json
        InputStream is = getResources().openRawResource(R.raw.restaurant);

        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            // injecte dans le tableau
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception e) {

        }

        String jsonStr = writer.toString();
        Gson gson = new Gson();
        Restaurant[] restaurants = gson.fromJson(jsonStr, Restaurant[].class);
        List<Restaurant> restList = Arrays.asList(restaurants);

        return restList;
    }

    }