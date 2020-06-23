package com.kurio.corona.data.remote.mapper;

public interface ResponseMapper<R, E> {

    public E mapFromResponse(R response);

}
