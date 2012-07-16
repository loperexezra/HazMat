package com.rel.hazmat.db.service;

import java.util.ArrayList;
import java.util.List;

import com.rel.hazmat.db.dao.HazardousMaterialDAO;
import com.rel.hazmat.db.model.HazardousMaterial;
import com.rel.hazmat.db.service.contracts.IMaterialService;
import com.rel.hazmat.dto.SearchDTO;
import com.rel.hazmat.utils.DTOConverter;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class MaterialService implements IMaterialService {
    private HazardousMaterialDAO materialDAO;

    public MaterialService(HazardousMaterialDAO materialDAO) {
        super();
        this.materialDAO = materialDAO;
    }

    @Override
    public List<SearchDTO> searchMaterial(String query) {
        List<HazardousMaterial> materialList = materialDAO
                .getHazardousMaterialListUsingQuery(query);
        List<SearchDTO> searchDTOList = new ArrayList<SearchDTO>();
        for (HazardousMaterial material : materialList) {
            searchDTOList.add(DTOConverter.toSearchDTO(material));
        }
        return searchDTOList;
    }
}
