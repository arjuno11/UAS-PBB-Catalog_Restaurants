package com.awan.uas.adapters;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.awan.uas.R;
import com.awan.uas.entity.Restaurants;
import com.awan.uas.entity.Utility;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.RestaurantHolder>{

    List<Restaurants> restaurants;
    AdapterInterface mInterface;

    public RestaurantsAdapter(List<Restaurants> restaurants, AdapterInterface mInterface)
    {
        this.restaurants = restaurants;
        this.mInterface = mInterface;
    }

    @NonNull
    @Override
    public RestaurantHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.child_recycler, viewGroup, false);
        return new RestaurantHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantHolder holder, int i) {
        Restaurants.Restaurant restaurant = restaurants.get(i).getRestaurant();
        holder.textView.setText(restaurant.getName());
        holder.range.setText("Range Price: "+restaurant.getCurrency()+" "+restaurant.getRange());
        if(Utility.isValidStr(restaurant.getThumb()))
            Picasso.get().load(restaurant.getThumb()).placeholder(R.drawable.ic_restaurant_black_24dp).into(holder.imageView);
        if(restaurant.Online()==1){
            holder.online.setText("Avaiable Online");
            Drawable onlinedrawable = holder.online.getBackground();
            onlinedrawable = DrawableCompat.wrap(onlinedrawable);
            DrawableCompat.setTint(onlinedrawable, Color.parseColor("#" + restaurant.getUser_rating().getRating_color()));
            holder.online.setBackground(onlinedrawable);
        }
        else{
            holder.online.setText("Not Avaiable Online");
            Drawable onlinedrawable = holder.online.getBackground();
            onlinedrawable = DrawableCompat.wrap(onlinedrawable);
            DrawableCompat.setTint(onlinedrawable, Color.parseColor("#000000"));
            holder.online.setBackground(onlinedrawable);}
        holder.ratings.setText(restaurant.getUser_rating().getAggregate_rating());
        Drawable drawable = holder.ratings.getBackground();
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, Color.parseColor("#" + restaurant.getUser_rating().getRating_color()));
        holder.ratings.setBackground(drawable);

        holder.mView.setTag(restaurant);
        holder.mView.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class RestaurantHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        TextView range;
        TextView online, ratings;
        View mView;

        public RestaurantHolder(View view)
        {
            super(view);
            imageView = view.findViewById(R.id.image);
            textView = view.findViewById(R.id.name);
            range = view.findViewById(R.id.range);
            online = view.findViewById(R.id.online);
            ratings = view.findViewById(R.id.ratings);
            mView = view;
        }
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Restaurants.Restaurant restaurant = (Restaurants.Restaurant) view.getTag();
            mInterface.onClick(restaurant);
        }
    };

}