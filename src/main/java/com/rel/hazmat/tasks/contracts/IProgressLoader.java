package com.rel.hazmat.tasks.contracts;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public abstract interface IProgressLoader {
    public void setLoading(boolean isLoading);

    public void setSupportProgress(int progress);
}