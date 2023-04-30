package fr.greta.fastfood;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.greta.fastfood.adapters.MenuListAdapter;
import fr.greta.fastfood.model.Menu;
import fr.greta.fastfood.model.Restaurant;

public class Menu_Activity extends AppCompatActivity  implements  MenuListAdapter.MenuListClickListener {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);



        Intent intent =getIntent();
        Restaurant restaurant = intent.getParcelableExtra("restaurant");


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(restaurant.getName());
        actionBar.setSubtitle(restaurant.getAddress());


        //injection first view
        List<Menu> menuList = restaurant.getMenus();
        initRecyclerView(menuList);

    }


    private void initRecyclerView(List<Menu> menuList){
        @SuppressLint("WrongViewCast") RecyclerView recyclerView1=findViewById(R.id.menuView);
        recyclerView1.setLayoutManager(new GridLayoutManager(this, 2));
        MenuListAdapter adapter1 = new MenuListAdapter(menuList,this);
        recyclerView1.setAdapter(adapter1);
    }


    @Override
    public void onItemClick(Menu menu) {
       /* Intent intent = new Intent( Menu_Activity.class);
        intent.putExtra("restaurant", restaurant);
        startActivity(intent);
*/
    }
}
