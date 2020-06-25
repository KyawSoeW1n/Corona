package com.kurio.corona.viewholder;

import android.view.View;

import androidx.annotation.NonNull;

import com.kurio.corona.databinding.CountryListitemBinding;
import com.kurio.corona.domain.model.AllCountries;

public class CoronaViewHolder extends BaseViewHolder<AllCountries> {
    private CountryListitemBinding countryListitemBinding;

    public CoronaViewHolder(@NonNull CountryListitemBinding itemView) {
        super(itemView.getRoot());
        this.countryListitemBinding = itemView;
    }

    @Override
    public void setData(AllCountries data) {
        countryListitemBinding.setCountryData(data);
        countryListitemBinding.executePendingBindings();
    }

    @Override
    public void onClick(View v) {

    }
}
