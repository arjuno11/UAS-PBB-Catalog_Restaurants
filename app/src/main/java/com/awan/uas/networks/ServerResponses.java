package com.awan.uas.networks;

import com.awan.uas.entity.Categories;
import com.awan.uas.entity.Location;
import com.awan.uas.entity.Restaurants;
import com.awan.uas.entity.ReviewRespone;

import java.util.List;

public class    ServerResponses {

    private int reviews_count;

    public int getReviews_count() { return reviews_count; }
    public void setReviews_count(int reviews_count) { this.reviews_count = reviews_count; }

    private List<Categories> categories;

    public List<Categories> getCategories() {
        return categories;
    }

    private List<Restaurants> restaurants;

    private List<Location> location_suggestions;

    private long results_found;

    private ReviewRespone reviewsrespone;

    public void setReviews(ReviewRespone reviews) { this.reviewsrespone = reviews; }
    public ReviewRespone getReviews() { return reviewsrespone; }

    public List<Location> getLocation_suggestions() {
        return location_suggestions;
    }

    public void setLocation_suggestions(List<Location> location_suggestions) {
        this.location_suggestions = location_suggestions;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public long getResults_found() {
        return results_found;
    }

    public void setResults_found(long results_found) {
        this.results_found = results_found;
    }

    public long getResults_start() {
        return results_start;
    }

    public void setResults_start(long results_start) {
        this.results_start = results_start;
    }

    public long getResults_shown() {
        return results_shown;
    }

    public void setResults_shown(long results_shown) {
        this.results_shown = results_shown;
    }

    public List<Restaurants> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurants> restaurants) {
        this.restaurants = restaurants;
    }

    private long results_start;
    private long results_shown;

}
