package com.rel.hazmat.tasks.contracts;

public abstract interface IProgressLoader {
    public void setLoading(boolean isLoading);

    public void setSupportProgress(int progress);
}