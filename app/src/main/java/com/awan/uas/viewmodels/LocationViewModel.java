package com.awan.uas.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.awan.uas.entity.Location;
import com.awan.uas.networks.APIRepo;

import java.util.List;

public class LocationViewModel extends ViewModel{

    public LocationViewModel(){}

    public LiveData<List<Location>> getLocationSuggestionsFromApi(String query)
    {
        APIRepo apiRepo = new APIRepo();
        return apiRepo.getLocationSuggestions(query);
    }

}
