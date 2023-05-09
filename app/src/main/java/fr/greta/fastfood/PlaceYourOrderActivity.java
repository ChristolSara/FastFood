package fr.greta.fastfood;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.greta.fastfood.adapters.MenuListAdapter;
import fr.greta.fastfood.adapters.orderListAdapter;
import fr.greta.fastfood.model.Menu;
import fr.greta.fastfood.model.Restaurant;

public class PlaceYourOrderActivity extends AppCompatActivity implements  orderListAdapter.orderListClickListener {

    private List<Menu> menus;
    private orderListAdapter orderListAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_your_order_view);

        //recover the object of the previous activity
        Intent intent = getIntent();
        Restaurant restaurant = intent.getParcelableExtra("RestaurantModel");

        //creation of the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(restaurant.getName());
        actionBar.setSubtitle(restaurant.getAddress());

        //injection first view
        List<Menu> menus = restaurant.getMenus();
        initRecyclerView(menus);

    }
        private void initRecyclerView(List<Menu> menus){
            @SuppressLint("WrongViewCast") RecyclerView recyclerView2=findViewById(R.id.listOrderView);
            recyclerView2.setLayoutManager(new LinearLayoutManager(this));
            orderListAdapter adapter2 = new orderListAdapter(menus, this);
            recyclerView2.setAdapter(adapter2);
        }



}