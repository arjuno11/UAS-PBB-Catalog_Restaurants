package com.awan.uas.networks;

import androidx.annotation.NonNull;


import com.awan.uas.entity.Constant;

import android.util.Log;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    private static String TAG = APIClient.class.getName() + " fatal";
    private static <T> T builder(Class<T> endpoint) {
        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(endpoint);
    }

    public static APIInterface reviewRespone(){return builder(APIInterface.class);}
    public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor()
            {
                @Override
                public Response intercept(@NonNull Chain chain) throws IOException
                {
                    Request request = chain.request();
                    request = getRequestWithAPIKey(request);
                    Response response = chain.proceed(request);
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            ResponseBody responseBody = response.body();
                            String jsonResponse = responseBody.string();
                            Log.d(TAG, "URL :" + request.url().toString());
                            Log.d(TAG, "Got response from server");
                            Log.d(TAG, jsonResponse);
                            ResponseBody original = ResponseBody.create(responseBody.contentType(), jsonResponse);
                            response = response.newBuilder().body(original).build();
                        }
                    }
                    return response;
                }
            })
            .build();

    private static Request getRequestWithAPIKey(Request request)
    {
        Request.Builder builder = request.newBuilder();
        builder.header("user-key", Constant.API_KEY);
        return builder.build();
    }
}
