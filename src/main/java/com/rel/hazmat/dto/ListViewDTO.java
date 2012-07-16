package com.rel.hazmat.dto;

public class ListViewDTO {
    private String valueName;
    private String value;

    public ListViewDTO(String valueName, String value) {
        this.valueName = valueName;
        this.value = value;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
