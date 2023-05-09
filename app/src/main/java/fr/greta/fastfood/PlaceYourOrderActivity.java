package fr.greta.fastfood;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
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
    private orderListAdapter adapter2;
    private EditText inputName, inputAddress, inputCity, inputState, inputZip,inputCardNumber, inputCardExpiry, inputCardPin ;

    private RecyclerView cartItemsRecyclerView;
    private TextView tvSubtotalAmount, tvDeliveryChargeAmount, tvDeliveryCharge, tvTotalAmount, buttonPlaceYourOrder;
    private SwitchCompat switchDelivery;
    private boolean isDeliveryOn;
    private orderListAdapter placeYourOrderAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_your_order_view);

        //recover the object of the previous activity
        Intent intent2 = getIntent();
        Restaurant restaurant = intent2.getParcelableExtra("RestaurantModel");

        //creation of the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(restaurant.getName());
        actionBar.setSubtitle(restaurant.getAddress());
        actionBar.setDisplayHomeAsUpEnabled(true);
        //recuperer les input
        inputName = findViewById(R.id.inputName);
        inputAddress = findViewById(R.id.inputAddress);
        inputCity = findViewById(R.id.inputCity);
        inputState = findViewById(R.id.inputState);
        inputZip = findViewById(R.id.inputZip);
        inputCardNumber = findViewById(R.id.inputCardNumber);
        inputCardExpiry = findViewById(R.id.inputCardExpiry);
        inputCardPin = findViewById(R.id.inputCardPin);
        tvSubtotalAmount = findViewById(R.id.tvSubtotalAmount);
        tvDeliveryChargeAmount = findViewById(R.id.tvDeliveryChargeAmount);
        tvDeliveryCharge = findViewById(R.id.tvDeliveryCharge);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        buttonPlaceYourOrder = findViewById(R.id.buttonPlaceYourOrder);
        switchDelivery = findViewById(R.id.switchDelivery);

        cartItemsRecyclerView = findViewById(R.id.cartItemsRecyclerView);


        //injection first view
        menus = restaurant.getMenus();
        initRecyclerView();

    }
        private void initRecyclerView(){
            @SuppressLint("WrongViewCast") RecyclerView recyclerView2=findViewById(R.id.cartItemsRecyclerView);
            recyclerView2.setLayoutManager(new LinearLayoutManager(this));
             adapter2 = new orderListAdapter(menus, this);
            recyclerView2.setAdapter(adapter2);
        }

      //  TextView tvSubtotal = findViewById(R.id.tvSubtotal);
    //    tvSubtotal.setText("ghh");



}