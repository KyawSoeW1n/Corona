package com.kurio.corona.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.kurio.corona.R;
import com.kurio.corona.databinding.CountryListitemBinding;
import com.kurio.corona.domain.model.AllCountries;
import com.kurio.corona.viewholder.BaseViewHolder;
import com.kurio.corona.viewholder.CoronaViewHolder;

public class CountryListAdapter extends BaseRecyclerAdapter<CoronaViewHolder, AllCountries> {

    public CountryListAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder<AllCountries> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CountryListitemBinding countryListitemBinding = CountryListitemBinding.inflate(layoutInflater, parent, false);
        return new CoronaViewHolder(countryListitemBinding);
    }
}
