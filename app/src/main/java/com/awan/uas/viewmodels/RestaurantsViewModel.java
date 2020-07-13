package com.awan.uas.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.awan.uas.entity.Restaurants;
import com.awan.uas.networks.APIRepo;

import java.util.List;

import static com.awan.uas.entity.Constant.ROW_COUNT;
import static com.awan.uas.entity.Constant.TYPE_CITY;

public class RestaurantsViewModel extends ViewModel {

    private LiveData<List<Restaurants>> restaurantList;
    private APIRepo apiRepo;

    public RestaurantsViewModel() {
        apiRepo = new APIRepo();
    }

    public LiveData<List<Restaurants>> getRestaurantListFromApi(long cityId, long categoryId,int start) {
        return apiRepo.getRestaurantsFromAPI(cityId, TYPE_CITY, start, ROW_COUNT, categoryId);
    }



}
