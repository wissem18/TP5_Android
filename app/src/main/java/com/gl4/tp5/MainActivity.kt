package com.gl4.tp5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.gl4.tp5.databinding.ActivityMainBinding
import com.gl4.tp5.weatherModels.WeatherResponse
import com.google.android.material.R
import android.util.Log

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    var weatherViewModel : WeatherViewModel = WeatherViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        weatherViewModel.weather.observe(this) {
            if (it != null)
                setWeather(it)
        }

        val cities = listOf<String>("Tunis", "Gabes", "Bizerte","Cairo","Moscow","London","Tokyo","California")
        val citiesAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, cities)
        val spinner = binding.citiesSpinner
        spinner.adapter = citiesAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                weatherViewModel.changeCity(cities[p2])
                if(weatherViewModel.weather.value != null){
                    println("hereeeeeeeee"+cities[p2])
                    setWeather(weatherViewModel.weather.value!!)
                    println(weatherViewModel.weather.value)
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
    }
    }

        fun setWeather(weather : WeatherResponse){
            binding.cityTextView.text = weather.name
            binding.temperatureTextView.text = "${weather.main.temp.toString()}°C"
            binding.weatherTextView.text = weather.weather[0].description
            binding.humidityTextView.text = "Humidity : ${weather.main.humidity}"
            binding.pressureTextView.text = "Pressure : ${weather.main.pressure}"

        }
}