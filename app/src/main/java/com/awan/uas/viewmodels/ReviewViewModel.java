package com.awan.uas.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.awan.uas.entity.Review;
import com.awan.uas.entity.ReviewRespone;
import com.awan.uas.networks.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewViewModel extends ViewModel {
    private MutableLiveData<List<Review>> reviewlivedata;
    List<Review>  reviews;
    public ReviewViewModel(){
        reviewlivedata = new MutableLiveData<>();
    }
    public LiveData<List<Review>> getReviewListFromApi(int id) {
        API.reviewRespone().getReviews(id ,3).enqueue(new Callback<ReviewRespone>() {
            @Override
            public void onResponse(Call<ReviewRespone> call, Response<ReviewRespone> response) {

                reviews=response.body().getUser_reviews();
                reviewlivedata.setValue(reviews);
            }

            @Override
            public void onFailure(Call<ReviewRespone> call, Throwable throwable) {
            }
        });
        return reviewlivedata;
    }

}
