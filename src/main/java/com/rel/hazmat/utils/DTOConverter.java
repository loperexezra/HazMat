package com.rel.hazmat.utils;

import java.util.ArrayList;
import java.util.List;

import com.rel.hazmat.db.model.HazardousMaterial;
import com.rel.hazmat.dto.SearchDTO;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class DTOConverter {
    public static SearchDTO toSearchDTO(HazardousMaterial material) {
        return new SearchDTO(material.getName(), material.getName(),
                material.getFormula());
    }

    public static List<SearchDTO> toSearchDTO(
            List<HazardousMaterial> materialList) {
        List<SearchDTO> searchList = new ArrayList<SearchDTO>();
        for (HazardousMaterial material : materialList) {
            searchList.add(toSearchDTO(material));
        }
        return searchList;
    }
}
