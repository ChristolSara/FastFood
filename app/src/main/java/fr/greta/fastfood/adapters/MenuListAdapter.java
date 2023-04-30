package fr.greta.fastfood.adapters;

import android.annotation.SuppressLint;
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

import fr.greta.fastfood.R;
import fr.greta.fastfood.model.Menu;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.MyVHolder> {
    private List<Menu> menuList;
    private MenuListClickListener clickListener;


    public MenuListAdapter(List<Menu> menuList, MenuListClickListener clickListener) {
        this.menuList = menuList;
        this.clickListener = clickListener;
    }
    public void updateData(List<Menu> menuList) {
        this.menuList = menuList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_recycler, parent, false);
        return  new MenuListAdapter.MyVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.menuName.setText(menuList.get(position).getName());
        holder.menuPrice.setText("Price: "+ menuList.get(position).getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(menuList.get(position));
            }
        });
        Glide.with(holder.menuImage)
                .load(menuList.get(position).getUrl())
                .into(holder.menuImage);

    }


    @Override
    public int getItemCount() {
        return menuList.size();
    }
//interface holder
    static class MyVHolder  extends RecyclerView.ViewHolder{
        TextView menuName;
        TextView  menuPrice;
        ImageView menuImage;
        TextView addToCartButton;
        ImageView imageMinus;
         ImageView imageAddOne;
        TextView  tvCount;
        LinearLayout addMoreLayout;


        public MyVHolder(View view) {
            super(view);
            this.menuName = view.findViewById(R.id.menuName);
            this.menuPrice =view.findViewById(R.id.menuPrice);
            this.menuImage = view.findViewById(R.id.menuImage);
            this.addMoreLayout = view.findViewById(R.id.addMoreLayout);
            this.addToCartButton = view.findViewById(R.id.thumbImage);
            this.imageAddOne = view.findViewById(R.id.imageAddOne);
            this.imageMinus = view.findViewById(R.id.imageMinus);
            this.tvCount = view.findViewById(R.id.tvCount);
        }
    }
    public interface MenuListClickListener {
        public void onItemClick(Menu menu);
        public void onUpdateCartClick(Menu menu);
        public void onRemoveFormCartClick(Menu menu);

    }
}
