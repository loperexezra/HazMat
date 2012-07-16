package com.rel.hazmat.dto;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class SearchDTO {
    private String slug;
    private String chemicalName;
    private String formula;

    public SearchDTO(String slug, String chemicalName, String formula) {
        this.slug = slug;
        this.chemicalName = chemicalName;
        this.formula = formula;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getChemicalName() {
        return chemicalName;
    }

    public void setChemicalName(String chemicalName) {
        this.chemicalName = chemicalName;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

}
