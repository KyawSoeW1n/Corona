package com.kurio.corona.data.presentation;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kurio.corona.data.presentation.state.Resource;
import com.kurio.corona.domain.interactor.gettotalcases.GetAllCountries;
import com.kurio.corona.domain.interactor.gettotalcases.GetTotalCases;
import com.kurio.corona.domain.model.AllCountries;
import com.kurio.corona.domain.model.TotalCases;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class CoronaViewModel extends ViewModel {
    private GetTotalCases getTotalCases;
    private GetAllCountries getAllCountries;
    private MutableLiveData<Resource<TotalCases>> totalCaseLiveData = new MutableLiveData<>();
    private MutableLiveData<Resource<List<AllCountries>>> allCountriesLiveData = new MutableLiveData<>();
    private Resource<TotalCases> totalCasesResource = new Resource<>();
    private Resource<List<AllCountries>> allCountriesResource = new Resource<>();

    @Inject
    public CoronaViewModel(GetTotalCases getTotalCases,
                           GetAllCountries getAllCountries) {
        this.getTotalCases = getTotalCases;
        this.getAllCountries = getAllCountries;
    }

    public MutableLiveData<Resource<List<AllCountries>>> getAllCountriesLiveData() {
        return allCountriesLiveData;
    }

    public MutableLiveData<Resource<TotalCases>> getTotalCaseLiveData() {
        return totalCaseLiveData;
    }

    public void getTotalCases() {
        getTotalCases.execute(new GetTotalCasesSubscriber(), null);
    }

    public void getAllCountries() {
        getAllCountries.execute(new GetAllCountriesSubscriber(), null);
    }

    class GetTotalCasesSubscriber implements SingleObserver<TotalCases> {

        @Override
        public void onSubscribe(Disposable d) {
            totalCaseLiveData.postValue(totalCasesResource.loading());
        }

        @Override
        public void onSuccess(TotalCases totalCases) {
            totalCaseLiveData.postValue(totalCasesResource.success(totalCases));
        }

        @Override
        public void onError(Throwable e) {
            totalCaseLiveData.postValue(totalCasesResource.error(e));
        }
    }

    class GetAllCountriesSubscriber implements SingleObserver<List<AllCountries>> {

        @Override
        public void onSubscribe(Disposable d) {
            allCountriesLiveData.postValue(allCountriesResource.loading());
        }

        @Override
        public void onSuccess(List<AllCountries> allCountries) {
            allCountriesLiveData.postValue(allCountriesResource.success(allCountries));
        }

        @Override
        public void onError(Throwable e) {
            allCountriesLiveData.postValue(allCountriesResource.error(e));
        }
    }
}
