package fr.greta.fastfood;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;

import fr.greta.fastfood.model.Restaurant;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Restautrants list");
        //get the restaurant list from the json
        List<Restaurant> restaurantList = getRestaurantData();
        //injevtion first view
        initRecyclerView(restaurantList);

    }

    private List<Restaurant> getRestaurantData() {
    }
}