package com.kurio.corona.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kurio.corona.R;
import com.kurio.corona.adapters.CountryListAdapter;
import com.kurio.corona.data.presentation.CoronaViewModel;
import com.kurio.corona.data.presentation.state.Resource;
import com.kurio.corona.data.presentation.state.ResourceState;
import com.kurio.corona.databinding.ActivityMainBinding;
import com.kurio.corona.domain.model.AllCountries;
import com.kurio.corona.injection.ViewModelFactory;
import com.kurio.corona.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {
    @Inject
    ViewModelFactory viewModelFactory;
    CountryListAdapter countryListAdapter;
    CoronaViewModel coronaViewModel;
    RecyclerView.LayoutManager layoutManager;
    List<AllCountries> countriesList = new ArrayList<>();
    List<AllCountries> filterCountryList = new ArrayList<>();
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        coronaViewModel = ViewModelProviders.of(this, viewModelFactory).get(CoronaViewModel.class);
        coronaViewModel.getTotalCases();
        coronaViewModel.getAllCountries();
        activityMainBinding.recyclerViewRates.setAdapter(countryListAdapter);
        activityMainBinding.recyclerViewRates.setLayoutManager(layoutManager);
        coronaViewModel.getAllCountriesLiveData().observe(this, this::getAllCountries);
    }

    @Override
    protected void initDataBinding() {
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setVm(coronaViewModel);
        activityMainBinding.setLifecycleOwner(this);
    }

    @Override
    protected void initComponent() {
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        countryListAdapter = new CountryListAdapter(this);
    }

    @Override
    protected void initData() {
        setUpListener();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void setUpListener() {
        activityMainBinding.edtSearch.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                activityMainBinding.txtCancel.setVisibility(View.VISIBLE);
            } else {
                activityMainBinding.txtCancel.setVisibility(View.GONE);
            }
        });

        activityMainBinding.txtCancel.setOnClickListener(v -> {
            ViewUtils.hideKeyboard(this);
            activityMainBinding.edtSearch.setText("");
            activityMainBinding.edtSearch.clearFocus();
            activityMainBinding.txtCancel.setVisibility(View.GONE);
        });

        activityMainBinding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                activityMainBinding.txtCancel.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equalsIgnoreCase("")) {
                    activityMainBinding.txtCancel.setVisibility(View.GONE);
                    countryListAdapter.addNewData(countriesList);
                } else {
                    activityMainBinding.txtCancel.setVisibility(View.VISIBLE);
                    filterByCountry(s.toString());
                }
            }
        });

        activityMainBinding.swipeLayout.setOnRefreshListener(() -> {
            coronaViewModel.getTotalCases();
            coronaViewModel.getAllCountries();
        });
    }

    private void filterByCountry(String countryName) {
        filterCountryList.clear();
        for (AllCountries countries : countriesList) {
            if (countries.getCountry().toLowerCase().contains(countryName.toLowerCase())) {
                filterCountryList.add(countries);
            }
        }
        if (!filterCountryList.isEmpty()) {
            countryListAdapter.addNewData(filterCountryList);
            countryListAdapter.notifyDataSetChanged();
        } else {
            countryListAdapter.clearData();
            Toast.makeText(this, "No Country", Toast.LENGTH_SHORT).show();
        }

    }

    private void getAllCountries(Resource<List<AllCountries>> resource) {
        if (resource.state == ResourceState.ERROR) {
            Log.i("ERROR", "error \t" + resource.errorMessage);
            hideSwipe();
        } else if (resource.state == ResourceState.SUCCESS) {
            Log.i("SUCCESS", "ALL COUNTRY");
            hideSwipe();
            countriesList.clear();
            if (resource.data != null) {
                countriesList = resource.data;
                countryListAdapter.addNewData(countriesList);
            }
        } else if (resource.state == ResourceState.LOADING) {
            Log.i("Currency Loading", "Loading");
        }
    }

    private void hideSwipe() {
        if (activityMainBinding.swipeLayout.isRefreshing()) {
            activityMainBinding.swipeLayout.setRefreshing(false);
        }
    }
}
