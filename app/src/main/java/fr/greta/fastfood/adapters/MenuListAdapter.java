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
    public void onBindViewHolder(@NonNull MyVHolder holder, int position) {

        holder.menuName.setText(menuList.get(position).getName());
        holder.menuPrice.setText("Price: "+ menuList.get(position).getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(menuList.get(position));
            }
        });
        holder.addToCartButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Menu menu = menuList.get(position);
                menu.setTotalInCards(1);
                clickListener.onAddToCartClick(menu);
                holder.addMoreLayout.setVisibility(View.VISIBLE);
                holder.addToCartButton.setVisibility(View.GONE);
                holder.tvCount.setText(menu.getTotalInCards()+"");
            }
        });
        holder.imageMinus.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Menu menu = menuList.get(position);
                int total = menu.getTotalInCards();
                total--;
                if(total > 0){
                    menu.setTotalInCards(total);
                    clickListener.onUpdateCartClick((menu));
                    holder.tvCount.setText(total  + "");
                }else{
                    holder.addMoreLayout.setVisibility((View.GONE));
                    holder.addToCartButton.setVisibility(View.VISIBLE);
                    menu.setTotalInCards(total);
                    clickListener.onRemoveFormCartClick(menu);
                }
            }
        }));

        Glide.with(holder.thumbImage)
                .load(menuList.get(position).getUrl())
                .into(holder.thumbImage);
    }


    @Override
    public int getItemCount() {
        return menuList.size();
    }
//interface holder
    static class MyVHolder  extends RecyclerView.ViewHolder {
    TextView menuName;
    TextView menuPrice;
    ImageView thumbImage;
    TextView addToCartButton;
    ImageView imageMinus;
    ImageView imageAddOne;
    TextView tvCount;
    LinearLayout addMoreLayout;


    public MyVHolder(View view) {
        super(view);
        menuName = view.findViewById(R.id.menuName);
        menuPrice = view.findViewById(R.id.menuPrice);
        addToCartButton = view.findViewById(R.id.addToCartButton);
        thumbImage = view.findViewById(R.id.thumbImage);
        imageMinus = view.findViewById(R.id.imageMinus);
        imageAddOne = view.findViewById(R.id.imageAddOne);
        tvCount = view.findViewById(R.id.tvCount);

        addMoreLayout = view.findViewById(R.id.addMoreLayout);
    }}

    public interface MenuListClickListener {
        public void onItemClick(Menu menu);

        public void onUpdateCartClick(Menu menu);

        public void onRemoveFormCartClick(Menu menu);

        public void onAddToCartClick(Menu menu);
    }

}
