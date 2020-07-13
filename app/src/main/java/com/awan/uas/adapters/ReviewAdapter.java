package com.awan.uas.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.awan.uas.R;
import com.awan.uas.entity.Review;
import com.awan.uas.entity.Utility;
import com.awan.uas.fragments.BaseFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {
    List<Review> reviewRespones;
    BaseFragment mInterface;
    public ReviewAdapter(List<Review> reviewRespones, BaseFragment mInterface){
        this.reviewRespones=reviewRespones;
        this.mInterface = mInterface;
    }

    @NonNull
    @Override
    public ReviewAdapter.ReviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.review_recycler, viewGroup, false);
        return new ReviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ReviewHolder holder, int i) {
       holder.rating.setText(Double.toString(reviewRespones.get(i).getRating()));
       holder.ratingtext.setText(reviewRespones.get(i).getRatingText());
       holder.reviewdate.setText(reviewRespones.get(i).getReviewTimeFriendly());
       holder.reveiwername.setText(reviewRespones.get(i).getUser().getName());
       holder.reviewcontent.setText(reviewRespones.get(i).getReviewText());
        if(Utility.isValidStr(reviewRespones.get(i).getUser().getProfileImage()))
            Picasso.get().load(reviewRespones.get(i).getUser().getProfileImage()).into(holder.user);

    }

    @Override
    public int getItemCount() {
        return reviewRespones.size();

    }


    public class ReviewHolder extends RecyclerView.ViewHolder{

        TextView rating, ratingtext, reviewdate, reveiwername, reviewcontent;
        ImageView user;
        public ReviewHolder(View view)
        {
            super(view);
            rating = view.findViewById(R.id.rb_rating);
            ratingtext = view.findViewById(R.id.tv_rating_text);
            reviewdate = view.findViewById(R.id.tv_review_date);
            reveiwername = view.findViewById(R.id.tv_reviewer_name);
            reviewcontent=view.findViewById(R.id.tv_review_content);
            user=view.findViewById(R.id.userImage);
        }
    }

}
