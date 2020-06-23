package com.kurio.corona.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.kurio.corona.R;
import com.kurio.corona.adapters.CountryListAdapter;
import com.kurio.corona.data.presentation.CoronaViewModel;
import com.kurio.corona.data.presentation.state.Resource;
import com.kurio.corona.data.presentation.state.ResourceState;
import com.kurio.corona.domain.model.AllCountries;
import com.kurio.corona.domain.model.TotalCases;
import com.kurio.corona.injection.ViewModelFactory;
import com.kurio.corona.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends BaseActivity {
    @Inject
    ViewModelFactory viewModelFactory;
    SwipeRefreshLayout swipeLayout;
    CountryListAdapter countryListAdapter;
    CoronaViewModel coronaViewModel;
    RecyclerView recycler_view_rates;
    RecyclerView.LayoutManager layoutManager;
    LinearLayout searchLayout;
    EditText edtSearch;
    List<AllCountries> countriesList = new ArrayList<>();
    List<AllCountries> filterCountryList = new ArrayList<>();
    TextView totalCases, totalDeaths, totalRecovered, cancelTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        coronaViewModel = ViewModelProviders.of(this, viewModelFactory).get(CoronaViewModel.class);
        coronaViewModel.getTotalCases();
        coronaViewModel.getAllCountries();
        coronaViewModel.getTotalCaseLiveData().observe(this, this::getTotalCases);
        coronaViewModel.getAllCountriesLiveData().observe(this, this::getAllCountries);
    }

    @Override
    protected void initComponent() {
        swipeLayout = findViewById(R.id.swipeLayout);
        recycler_view_rates = findViewById(R.id.recycler_view_rates);
        cancelTxt = findViewById(R.id.txt_cancel);
        searchLayout = findViewById(R.id.search_layout);
        edtSearch = findViewById(R.id.edt_search);
        totalCases = findViewById(R.id.totalCases);
        totalDeaths = findViewById(R.id.totalDeaths);
        totalRecovered = findViewById(R.id.totalRecovered);
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        countryListAdapter = new CountryListAdapter(this);
        recycler_view_rates.setAdapter(countryListAdapter);
        recycler_view_rates.setLayoutManager(layoutManager);
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
        edtSearch.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                cancelTxt.setVisibility(View.VISIBLE);
            } else {
                cancelTxt.setVisibility(View.GONE);
            }
        });

        cancelTxt.setOnClickListener(v -> {
            ViewUtils.hideKeyboard(this);
            edtSearch.setText("");
            edtSearch.clearFocus();
            cancelTxt.setVisibility(View.GONE);
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                cancelTxt.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equalsIgnoreCase("")) {
                    cancelTxt.setVisibility(View.GONE);
                    countryListAdapter.addNewData(countriesList);
                } else {
                    cancelTxt.setVisibility(View.VISIBLE);
                    filterByCountry(s.toString());
                }
            }
        });

        swipeLayout.setOnRefreshListener(() -> {
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
            if (swipeLayout.isRefreshing()) {
                swipeLayout.setRefreshing(false);
            }
        } else if (resource.state == ResourceState.SUCCESS) {
            Log.i("SUCCESS", "ALL COUNTRY");
            if (swipeLayout.isRefreshing()) {
                swipeLayout.setRefreshing(false);
            }
            countriesList.clear();
            if (resource.data != null) {
                countriesList = resource.data;
                countryListAdapter.addNewData(countriesList);
            }
        } else if (resource.state == ResourceState.LOADING) {
            Log.i("Currency Loading", "Loading");
        }
    }

    private void getTotalCases(Resource<TotalCases> resource) {
        if (resource.state == ResourceState.ERROR) {
            Log.i("ERROR", "error \t" + resource.errorMessage);
        } else if (resource.state == ResourceState.SUCCESS) {
            if (resource.data != null) {
                totalCases.setText(String.valueOf(resource.data.getCases()));
                totalDeaths.setText(String.valueOf(resource.data.getDeaths()));
                totalRecovered.setText(String.valueOf(resource.data.getRecovered()));
            }
        } else if (resource.state == ResourceState.LOADING) {
            Log.i("Currency Loading", "Loading");
        }
    }
}
