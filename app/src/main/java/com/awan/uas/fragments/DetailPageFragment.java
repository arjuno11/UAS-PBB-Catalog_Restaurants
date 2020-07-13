package com.awan.uas.fragments;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.awan.uas.R;
import com.awan.uas.adapters.ReviewAdapter;
import com.awan.uas.comp.RecyclerViewMargin;
import com.awan.uas.entity.Restaurants;
import com.awan.uas.entity.Review;
import com.awan.uas.entity.ReviewRespone;
import com.awan.uas.entity.Utility;
import com.awan.uas.viewmodels.ReviewViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Retrofit;

public class DetailPageFragment extends BaseFragment implements OnMapReadyCallback {

    public static final String TAG = DetailPageFragment.class.getName();
    private Restaurants.Restaurant restaurant;
    private ImageView imageView;
    private TextView nameText, ratingText, onlineText, rangeText, addressText, review;
    RecyclerView recyclerView;
    ReviewAdapter adapter;
    ReviewRespone reviewRespone;
    private List<Review> reviews;
    private static Retrofit retrofit = null;
    ReviewViewModel viewModel;
    DetailPageFragment context;
    private GoogleMap mMap;
    private SupportMapFragment mapFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View mView = inflater.inflate(R.layout.restraurant_detail, container, false);
        imageView = mView.findViewById(R.id.image);
        nameText = mView.findViewById(R.id.name);
        ratingText = mView.findViewById(R.id.ratings);
        onlineText = mView.findViewById(R.id.onlines);
        rangeText=mView.findViewById(R.id.ranges);
        addressText=mView.findViewById(R.id.address);
        recyclerView=(RecyclerView) mView.findViewById(R.id.review_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        context=this;
        RecyclerViewMargin decoration = new RecyclerViewMargin(0, 0, getResources().getDimensionPixelSize(R.dimen.size_4), 0);
        recyclerView.addItemDecoration(decoration);


        return mView;
    }
    Observer<List<Review>> userListUpdateObserver = new Observer<List<Review>>() {
        @Override
        public void onChanged(List<Review> reviewList){
                adapter= new ReviewAdapter(reviewList,context);
                recyclerView.setAdapter(adapter);
        }
    };

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        init();
        viewModel= ViewModelProviders.of(this).get(ReviewViewModel.class);
        viewModel.getReviewListFromApi(restaurant.getId()).observe(context,userListUpdateObserver);


        if(Utility.isValidStr(restaurant.getFeatured_image()))
        {
            int height = getActivity().getResources().getDimensionPixelSize(R.dimen.size_85);
            int width = getActivity().getResources().getDimensionPixelSize(R.dimen.size_170);
            Picasso.get().load(restaurant.getFeatured_image()).placeholder(R.drawable.ic_restaurant_black_24dp).resize(width, height).into(imageView);
        }
        nameText.setText(restaurant.getName());
        ratingText.setText(restaurant.getUser_rating().getAggregate_rating());
        Drawable drawable = ratingText.getBackground();
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, Color.parseColor("#" + restaurant.getUser_rating().getRating_color()));
        ratingText.setBackground(drawable);
        rangeText.setText("Price Range: "+restaurant.getCurrency()+" "+restaurant.getRange());
//        review.setText(Integer.toString(x));
        if(restaurant.Online()==1){
            onlineText.setText("Avaiable Online");
            Drawable onlinedrawable = onlineText.getBackground();
            onlinedrawable = DrawableCompat.wrap(onlinedrawable);
            DrawableCompat.setTint(onlinedrawable, Color.parseColor("#" + restaurant.getUser_rating().getRating_color()));
            onlineText.setBackground(onlinedrawable);
        }
        else{
            onlineText.setText("Not Avaiable Online");
            Drawable onlinedrawable = onlineText.getBackground();
            onlinedrawable = DrawableCompat.wrap(onlinedrawable);
            DrawableCompat.setTint(onlinedrawable, Color.parseColor("#000000"));
            onlineText.setBackground(onlinedrawable);}
        addressText.setText(restaurant.getLocation().getAddress());
//        webview.setWebViewClient(new WebViewClient());
//        webview.getSettings().setJavaScriptEnabled(true);
//        webview.getSettings().setBuiltInZoomControls(false);
//        double longitude = restaurant.getLocation().getLongtitude();
//        double latitude = restaurant.getLocation().getLatitude();
//        webview.loadUrl("https://had3ae.team/ppb/osm.php?long="+longitude+"&lat="+latitude);
//        Log.d(TAG, "https://had3ae.team/ppb/osm.php?long="+longitude+"&lat="+latitude);

        createMapFragmentIfNeeded();

    }

    protected SupportMapFragment createMapFragment() {
        return SupportMapFragment.newInstance();
    }

    @Override
    public void onResume() {
        super.onResume();
        SupportMapFragment smf = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map));

        smf.getMapAsync(this);
    }

    private void createMapFragmentIfNeeded() {
        FragmentManager fm = getChildFragmentManager();
        mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = createMapFragment();
            FragmentTransaction tx = fm.beginTransaction();
            tx.add(R.id.map, mapFragment);
            tx.commit();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng location = new LatLng(restaurant.getLocation().getLatitude(),
                restaurant.getLocation().getLongtitude());
        mMap.setMinZoomPreference(16.0f);
        mMap.setMaxZoomPreference(20.0f);
        mMap.addMarker(new MarkerOptions().position(location).title(restaurant.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
    }

    private void init()
    {
        Bundle bundle = getArguments();
        restaurant = (Restaurants.Restaurant) bundle.getSerializable("RESTAURANT");
    }
}
