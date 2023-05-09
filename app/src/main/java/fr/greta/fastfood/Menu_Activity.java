package fr.greta.fastfood;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.greta.fastfood.adapters.MenuListAdapter;
import fr.greta.fastfood.model.Menu;
import fr.greta.fastfood.model.Restaurant;

public class Menu_Activity extends AppCompatActivity  implements  MenuListAdapter.MenuListClickListener {
    private List<Menu> menuList = null;
    private MenuListAdapter adapter1;
    private List<Menu> itemsInCardsList;
    private int totalItemsInCart = 0 ;
    private TextView buttonCheckout ;


    //afficher les informations dans le action bar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        //recover the object of the previous activity
        Intent intent =getIntent();
        Restaurant restaurant = intent.getParcelableExtra("restaurant");

       //creation of the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(restaurant.getName());
        actionBar.setSubtitle(restaurant.getAddress());

        //injection first view
         menuList = restaurant.getMenus();
        initRecyclerView();



        buttonCheckout = findViewById(R.id.buttonCheckout);
        buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemsInCardsList != null && itemsInCardsList.size() <= 0){
                    Toast.makeText(Menu_Activity.this, "please add some items in cart. ",Toast.LENGTH_SHORT).show();
                    return;
                }
                restaurant.setMenus(itemsInCardsList);
                Intent i = new Intent(Menu_Activity.this, PlaceYourOrderActivity.class);
                i.putExtra("RestaurantModel",restaurant);
                startActivityForResult(i, 1000);
            }
        });

    }




    private void initRecyclerView(){
        RecyclerView recyclerView1=findViewById(R.id.menuView);
        recyclerView1.setLayoutManager(new GridLayoutManager(this, 2));
        adapter1 = new MenuListAdapter(menuList,this);
        recyclerView1.setAdapter(adapter1);
    }


    @Override
    public void onAddToCartClick(Menu menu) {
      if (itemsInCardsList == null){
          itemsInCardsList = new ArrayList<>();
      }
      itemsInCardsList.add(menu);
      totalItemsInCart =0;
      for(Menu m :itemsInCardsList){
          totalItemsInCart = totalItemsInCart + m.getTotalInCards();
      }

      buttonCheckout.setText(("Chekout ("+totalItemsInCart+ ")items"));
    }



    @Override
    public void onUpdateCartClick(Menu menu) {

        if(itemsInCardsList.contains(menu)){
            int index = itemsInCardsList.indexOf(menu);
            itemsInCardsList.remove(index);
            itemsInCardsList.add(index,menu);
             totalItemsInCart = 0;

             for(Menu m : itemsInCardsList){
                 totalItemsInCart = totalItemsInCart + m.getTotalInCards();
             }
             buttonCheckout.setText("Chekout ( "+ totalItemsInCart + " ) items ");
        }
    }
    @Override
    public void onRemoveFormCartClick(Menu menu) {
        if(itemsInCardsList.contains(menu)) {
            itemsInCardsList.remove(menu);
            totalItemsInCart =0;
            for(Menu m :itemsInCardsList){
                totalItemsInCart = totalItemsInCart +m.getTotalInCards();
            }
            buttonCheckout.setText("Checkout (" + totalItemsInCart + ") items");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch ((item.getItemId())){
            case android.R.id.home:
                finish();
            default:
                //do nothing
        }
        return  super.onOptionsItemSelected(item);

    }


    @Override
    protected void onActivityResult(int requestCode,int resultCode ,@NonNull Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 1000 && requestCode == Activity.RESULT_OK){
            //
            finish();
        }
    }

    }
