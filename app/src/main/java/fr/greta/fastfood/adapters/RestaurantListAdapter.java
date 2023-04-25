package fr.greta.fastfood.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.greta.fastfood.R;
import fr.greta.fastfood.model.Restaurant;


public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.MyViewHolder> {

 private   List<Restaurant> restaurantList;
 private RestaurantListClickListener restaurantListClickListener;

    public RestaurantListAdapter(List<Restaurant> restaurantList, RestaurantListClickListener restaurantListClickListener) {
        this.restaurantList = restaurantList;
        this.restaurantListClickListener = restaurantListClickListener;
    }
    public void updateData(List<Restaurant> restaurantList){
        this.restaurantList=restaurantList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.recycler_raw,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,@SuppressLint("RecyclerView") int position) {
        holder.restaurantName.setText(restaurantList.get(position).getName());
        holder.restaurantAddress.setText("address"+restaurantList.get(position).getAddress());
        holder.restaurantHours.setText("Today's hours : "+restaurantList.get(position).getHours());
        holder.itemView.setOnContextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(restaurantList.get(position));

            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView restaurantName;
        TextView  restaurantAddress;
        TextView restaurantHours;
        ImageView thumbImage;

        public MyViewHolder(View view) {
            super(view);
            restaurantName = view.findViewById(R.id.restaurantName);
            restaurantAddress = view.findViewById(R.id.restaurantAddress);
            restaurantHours = view.findViewById(R.id.restaurantHours);
            thumbImage = view.findViewById(R.id.thumbImage);

        }
    }


    public interface RestaurantListClickListener {
        public void onItemClick(Restaurant restaurant);
    }




}
