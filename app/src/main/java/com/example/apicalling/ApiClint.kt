package com.example.apicalling

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClint {



    companion object{

        fun getRetrofit() : Retrofit{
            val url = "https://newsapi.org/v2/"
            var retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit
        }

    }
}