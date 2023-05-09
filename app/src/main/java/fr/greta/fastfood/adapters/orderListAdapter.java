package fr.greta.fastfood.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import fr.greta.fastfood.PlaceYourOrderActivity;
import fr.greta.fastfood.R;
import fr.greta.fastfood.model.Menu;
import fr.greta.fastfood.model.Restaurant;

public class orderListAdapter extends RecyclerView.Adapter<orderListAdapter.MyHolder>{
        private List<Menu> menus;
        private orderListClickListener clickListener;

    public orderListAdapter(List<Menu> menus, orderListClickListener clickListener) {
        this.menus = menus;
        this.clickListener = clickListener;
    }
    public void updateData(List<Menu> menus) {
        this.menus = menus;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public orderListAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_order_raw_view, parent, false);
        return  new orderListAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull orderListAdapter.MyHolder holder, int position) {
        holder.menuName.setText(menus.get(position).getName());
        holder.menuPrice.setText("Price: "+ menus.get(position).getPrice());
        Glide.with(holder.thumbImage)
                .load(menus.get(position).getUrl())
                .into(holder.thumbImage);
        holder.menuQty.setText(String.valueOf(menus.size()));

    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

static class MyHolder  extends RecyclerView.ViewHolder {
    TextView menuName;
    TextView menuPrice;
    ImageView thumbImage;
    TextView menuQty;



    public MyHolder(View view) {
        super(view);
        menuName = view.findViewById(R.id.menuName);
        menuPrice = view.findViewById(R.id.menuPrice);
        thumbImage = view.findViewById(R.id.thumbImage);
        menuQty = view.findViewById(R.id.menuQty);

    }}

    public interface orderListClickListener {

    }

}