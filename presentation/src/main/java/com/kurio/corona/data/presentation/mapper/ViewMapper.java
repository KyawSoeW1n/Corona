package com.kurio.corona.data.presentation.mapper;

public interface ViewMapper<V, D> {
    public V mapToView(D domainModel);
}
