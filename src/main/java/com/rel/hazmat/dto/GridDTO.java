package com.rel.hazmat.dto;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class GridDTO {
    private String slug;
    private Integer resourceID;

    public GridDTO(String slug, Integer resourceID) {
        this.slug = slug;
        this.resourceID = resourceID;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getResourceID() {
        return resourceID;
    }

    public void setResourceID(Integer resourceID) {
        this.resourceID = resourceID;
    }

}
