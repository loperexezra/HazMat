package com.rel.hazmat.dto;

import com.rel.hazmat.R;

/**
 * 
 * @author Lope Chupijay Emano
 *
 */
public class ListViewDTO {
    private String valueName;
    private String value;
    private Boolean tabbed;
    private Integer lblColor;

    public ListViewDTO(String valueName, String value) {
        this.valueName = valueName;
        this.value = value;
        this.tabbed = false;
        this.lblColor = R.color.black;
    }

    public ListViewDTO(String valueName, String value, Boolean tabbed,
            Integer lblColor) {
        this.valueName = valueName;
        this.value = value;
        this.tabbed = tabbed;
        this.lblColor = lblColor;
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

    public Boolean getTabbed() {
        return tabbed;
    }

    public void setTabbed(Boolean tabbed) {
        this.tabbed = tabbed;
    }

    public Integer getLblColor() {
        return lblColor;
    }

    public void setLblColor(Integer lblColor) {
        this.lblColor = lblColor;
    }

}
