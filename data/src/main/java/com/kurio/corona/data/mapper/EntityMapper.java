package com.kurio.corona.data.mapper;

public interface EntityMapper<E, D> {
    public D mapFromEntity(E entity);

    public E mapToEntity(D domainModel);
}
