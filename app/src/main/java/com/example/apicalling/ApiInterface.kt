package com.example.apicalling

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

        @GET("top-headlines")
        fun getNews(@Query("country")c1 :String,@Query("category")cate : String,@Query("apiKey")key : String) : Call<NewsModel>
}