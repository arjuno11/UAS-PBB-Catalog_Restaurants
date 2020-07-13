package com.awan.uas.adapters;

import com.awan.uas.entity.Categories;
import com.awan.uas.entity.Restaurants;

public interface AdapterInterface {
    void onClick(Restaurants.Restaurant restaurant);
    void onClickSeeAll(Categories.Category category);
}
