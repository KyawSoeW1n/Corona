package com.kurio.corona.data.presentation.state;


import androidx.annotation.Nullable;

public class Resource<D> {

    public ResourceState state;
    @Nullable
    public D data;

    @Nullable
    public String errorMessage;

    public String errorID;

    public Resource() {
    }

    public Resource(ResourceState state, @Nullable D data,
                    @Nullable String errorMessage,
                    @Nullable String errorID) {
        this.state = state;
        this.data = data;
        this.errorMessage = errorMessage;
        this.errorID = errorID;
    }

    public Resource<D> success(D data) {
        return new Resource<D>(ResourceState.SUCCESS, data, null, null);
    }

    public Resource<D> error(String errorMessage) {
        return new Resource<D>(ResourceState.ERROR, null, errorMessage, errorID);
    }

    public Resource<D> error(Throwable t) {
        if (t.getCause() != null) {
            return new Resource<D>(ResourceState.ERROR, null, t.getMessage(), t.getCause().getMessage());
        }
        return error(t.getMessage());

    }

    public Resource<D> loading() {
        return new Resource<D>(ResourceState.LOADING, null, null, null);
    }
}
