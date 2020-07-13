package com.awan.uas.entity;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

public class ReviewRespone{

    @SerializedName("reviews_count")
    @Expose
    private int reviews_count;

    @SerializedName("reviews_start")
    @Expose
    private int reviews_start;

    @SerializedName("reviews_shown")
    @Expose
    private int reviews_shown;

    @SerializedName("user_reviews")
    @JsonAdapter(ReviewListDeserializer.class)
    @Expose
    private List<Review> user_reviews;

    public void setReviews_count(int reviews_count) { this.reviews_count = reviews_count; }
    public int getReviews_count() { return reviews_count; }

    public void setReviews_shown(int reviews_shown) { this.reviews_shown = reviews_shown; }
    public int getReviews_shown() { return reviews_shown; }

    public void setReviews_start(int reviews_start) { this.reviews_start = reviews_start; }
    public int getReviews_start() { return reviews_start; }

    public void setUser_reviews(List<Review> user_reviews) { this.user_reviews = user_reviews; }
    public List<Review> getUser_reviews() { return user_reviews; }

    public static class ReviewListDeserializer implements JsonDeserializer<List<Review>> {
        @Override
        public List<Review> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            JsonArray reviewJsonArray = json.getAsJsonArray();
            List<Review> reviewList = new ArrayList<>(reviewJsonArray.size());
            for (int i = 0; i < reviewJsonArray.size(); i++){
                JsonObject obj = reviewJsonArray.get(i).getAsJsonObject();
                Gson gson = new Gson();
                reviewList.add(gson.fromJson(obj.get("review").getAsJsonObject(), Review.class));
            }
            return reviewList;
        }
    }

    @Override
    public String toString(){
        return
                "ReviewResponse{" +
                        "reviews_count = '" + reviews_count + '\'' +
                        ",reviews_start = '" + reviews_start + '\'' +
                        ",reviews_shown = '" + reviews_shown + '\'' +
                        ",user_reviews = '" + user_reviews + '\'' +
                        "}";
    }


}
