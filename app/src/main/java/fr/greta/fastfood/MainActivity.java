package fr.greta.fastfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;


import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

import fr.greta.fastfood.adapters.RestaurantListAdapter;
import fr.greta.fastfood.model.Restaurant;

public class MainActivity extends AppCompatActivity implements  RestaurantListAdapter.RestaurantListClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // ActionBar actionBar = getSupportActionBar();
       // actionBar.setTitle("Restautrants list");
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

    private void initRecyclerView(List<Restaurant> restaurantList){
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RestaurantListAdapter adapter = new RestaurantListAdapter(restaurantList,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Restaurant restaurant){
        Intent intent = new Intent(MainActivity.this , RestaurantListAdapter.class);
        intent.putExtra("restaurant", (Parcelable) restaurant);
        startActivity(intent);

    }






    }