package com.awan.uas.entity;

import com.google.gson.annotations.SerializedName;

public class UserReviews {
    @SerializedName("review")
    private Review review;

    public void setReview(Review review){
        this.review = review;
    }

    public Review getReview(){
        return review;
    }

    @Override
    public String toString(){
        return
                "UserReviewsItem{" +
                        "review = '" + review + '\'' +
                        "}";
    }
}
