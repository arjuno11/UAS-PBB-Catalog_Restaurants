package com.awan.uas.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.awan.uas.entity.Categories;
import com.awan.uas.networks.APIRepo;

import java.util.List;

public class CategoryViewModel extends ViewModel{

    private LiveData<List<Categories>> categories;
    private APIRepo apiRepo;

    public CategoryViewModel()
    {
        apiRepo = new APIRepo();
        categories = getCategoryFromAPI();
    }

    public LiveData<List<Categories>> getCategories() {
        return categories;
    }

    public LiveData<List<Categories>> getCategoryFromAPI()
    {
        return apiRepo.getCategoryFromAPI();
    }

}
