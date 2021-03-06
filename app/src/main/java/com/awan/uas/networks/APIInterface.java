package com.awan.uas.networks;

import com.awan.uas.entity.Constant;
import com.awan.uas.entity.ReviewRespone;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET(Constant.GET_CATEGORIES)
    Call<ServerResponses> getCategories();

    @GET(Constant.SEARCH_RESTAURANTS)
    Call<ServerResponses> searchRestaurants(@Query("entity_id") long entityId, @Query("entity_type") String entityType,
                                            @Query("start") int start, @Query("count") int count, @Query("category") long categoryId);

    @GET(Constant.SEARCH_RESTAURANTS)
    Call<ServerResponses> searchRestaurantsAsPaging(@Query("entity_id") long entityId, @Query("entity_type") String entityType, @Query("start") long start, @Query("category") long categoryId);

    @GET(Constant.SEARCH_LOCATIONS)
    Call<ServerResponses> searchLocations(@Query("query") String query);

    @GET(Constant.SEARCH_REVIEWS)
    Call<ReviewRespone> getReviews(@Query("res_id") int res_id, @Query("count")int count);

}
