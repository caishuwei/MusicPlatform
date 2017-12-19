package com.csw.musicplatform.http;

import android.support.annotation.NonNull;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Retrofit实例获取仓库，针对每个服务器地址，构建一个Retrofit
 * Created by caisw on 2017/10/27.
 */

public class RetrofitFactory {

    private static Map<String, Retrofit> retrofitMap = new HashMap<>();

    /**
     * 取得Retrofit实例
     *
     * @param baseUrl 服务器地址
     * @return Retrofit Retrofit实例
     */
    public static Retrofit getRetrofit(@NonNull String baseUrl) {
        Retrofit retrofit = retrofitMap.get(baseUrl);
        if (retrofit == null) {
            retrofit = createRetrofitByBaseUrl(baseUrl);
            retrofitMap.put(baseUrl, retrofit);
        }
        return retrofit;
    }

    private static Retrofit createRetrofitByBaseUrl(String baseUrl) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(20, TimeUnit.SECONDS);//设置链接超时时间
        builder.readTimeout(20, TimeUnit.SECONDS); // 是指读取超时时间
        builder.writeTimeout(20, TimeUnit.SECONDS);//是设置写入超时时间
        builder.retryOnConnectionFailure(true);//连接失败时重试
        // Log信息拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //设置 Debug Log 模式
        builder.addInterceptor(loggingInterceptor);

        return new Retrofit.Builder().
                addConverterFactory(ScalarsConverterFactory.create()).//String 解析支持
                addConverterFactory(GsonConverterFactory.create()).//Gson解析的支持
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).//Rx observer 回调的支持
                client(builder.build()).//使用带缓存的网络请求工具
                baseUrl(baseUrl).
                build();
    }

}
