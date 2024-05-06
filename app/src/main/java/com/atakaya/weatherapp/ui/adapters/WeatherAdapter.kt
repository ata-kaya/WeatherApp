package com.atakaya.weatherapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atakaya.weatherapp.MainApplication
import com.atakaya.weatherapp.databinding.WeatherItemBinding
import com.atakaya.weatherapp.domain.model.CurrentWeatherForUI
import com.bumptech.glide.Glide


class WeatherAdapter(
    private val data: ArrayList<CurrentWeatherForUI?>,
    private val context: MainApplication
) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            WeatherItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                holder.bind(data[position])
            }
        }
    }


    inner class ViewHolder(
        private val bind: WeatherItemBinding
    ) : RecyclerView.ViewHolder(bind.root) {

        fun bind(weather: CurrentWeatherForUI?) {
            weather?.let { weatherForUI ->
                bind.tvCityName.text = weatherForUI.location?.name
                bind.tvDegree.text = weatherForUI.current?.tempC.toString()
                bind.tvCityTime.text = weatherForUI.location?.localtime
                bind.tvWeatherCondition.text = weatherForUI.current?.condition?.text
                context.resources.getIdentifier(
                    weatherForUI.weatherIconName,
                    "drawable",
                    context.packageName
                ).let {
                    Glide.with(context).load(it).into(bind.ivWeatherIcon)
                }
            }

        }
    }
}
