package com.slashmobility.bottleflip_android.services;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**

 * @author Rony Diaz
 * @version %I, %G
 * @since 1.0
 */

public class ServiceHelper {
    private Retrofit mRetrofit;
    private ServiceInterface mService;
    public static ServiceHelper mInstance;

    public static ServiceInterface getInterface(String url){
        if (mInstance==null){
            mInstance = new ServiceHelper(url,true);
        }
        return mInstance.mService;
    }

    public static ServiceInterface getAlternative(String url)
    {
        return  new ServiceHelper(url,false).mService;
    }



    private ServiceHelper(String url, Boolean cookies){


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        final OkHttpClient mokHttpClient;
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        if(cookies){
            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            mokHttpClient = new OkHttpClient.Builder()
                    .cookieJar(new JavaNetCookieJar(cookieManager))
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();
                            Request.Builder requestBuilder = original.newBuilder()
                                    //.addHeader("Accept-Language", SingletonSession.Instance().getLanguage())
                                    ;
                            Request request = requestBuilder.build();
                            return chain.proceed(request);
                        }
                    })
                   // .addNetworkInterceptor(new StethoInterceptor())
                    .addInterceptor(logging)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build();

        }
        else
        {
            mokHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();
                            Request.Builder requestBuilder = original.newBuilder()
                                    //.addHeader("Accept-Language", SingletonSession.Instance().getLanguage())
                                    ;
                            Request request = requestBuilder.build();
                            return chain.proceed(request);
                        }
                    })
                    //.addNetworkInterceptor(new StethoInterceptor())
                    .addInterceptor(logging)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build();

        }



        mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(mokHttpClient)
                .build();
        mService = mRetrofit.create(ServiceInterface.class);

    }

}
