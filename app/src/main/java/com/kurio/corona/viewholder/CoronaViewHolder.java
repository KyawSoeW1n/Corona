package com.kurio.corona.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.kurio.corona.R;
import com.kurio.corona.domain.model.AllCountries;

public class CoronaViewHolder extends BaseViewHolder<AllCountries> {
    private TextView tvCountryName;
    private TextView tvTotalCases;
    private TextView tvTodayCases;
    private TextView tvTotalDeaths;
    private TextView tvTodayDeaths;
    private TextView tvRecovered;
    private ImageView imgFlag;

    public CoronaViewHolder(@NonNull View itemView) {
        super(itemView);
        tvCountryName = itemView.findViewById(R.id.tvCountryName);
        tvTotalCases = itemView.findViewById(R.id.tvTotalCases);
        tvTodayCases = itemView.findViewById(R.id.tvTodayCases);
        tvTotalDeaths = itemView.findViewById(R.id.tvTotalDeaths);
        tvTodayDeaths = itemView.findViewById(R.id.tvTodayDeaths);
        tvRecovered = itemView.findViewById(R.id.tvRecovered);
        imgFlag = itemView.findViewById(R.id.imgFlag);
    }

    @Override
    public void setData(AllCountries data) {
        Glide.with(itemView.getContext())
                .load(data.getFlag())
                .into(imgFlag);
        tvCountryName.setText(data.getCountry());
        tvTotalCases.setText(String.valueOf(data.getCases()));
        tvTodayCases.setText(String.valueOf(data.getTodayCases()));
        tvTotalDeaths.setText(String.valueOf(data.getDeaths()));
        tvTodayDeaths.setText(String.valueOf(data.getTodayDeaths()));
        tvRecovered.setText(String.valueOf(data.getRecovered()));
    }

    @Override
    public void onClick(View v) {

    }
}
