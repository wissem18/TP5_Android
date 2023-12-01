package com.gl4.tp5

import com.gl4.tp5.weatherModels.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface WeatherAPI {
    @GET("weather?APPID=4ed071705019536d4cd55e0240ed6e5f")
    fun getWeather(@Query("q")city: String): Call<WeatherResponse>
}