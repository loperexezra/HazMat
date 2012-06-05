package com.rel.hazmat.exception;

public class NullSlugException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "The web server returned a null or empty slug for the model, this should not happen";
    }
}
