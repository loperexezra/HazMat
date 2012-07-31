package com.rel.hazmat.db.service;

import java.util.ArrayList;
import java.util.List;

import com.rel.hazmat.db.dao.HazardousMaterialDAO;
import com.rel.hazmat.db.model.HazardousMaterial;
import com.rel.hazmat.db.service.contracts.IMaterialService;
import com.rel.hazmat.dto.SearchDTO;
import com.rel.hazmat.exception.NullSlugException;
import com.rel.hazmat.json.model.serializable.ChemicalResponseSerializable;
import com.rel.hazmat.utils.DBConverter;
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

    @Override
    public List<HazardousMaterial> save(ChemicalResponseSerializable[] chemicals) {
        List<HazardousMaterial> materialList = new ArrayList<HazardousMaterial>();
        for (ChemicalResponseSerializable response : chemicals) {
            materialList.add(save(response));
        }
        return materialList;
    }

    @Override
    public HazardousMaterial save(ChemicalResponseSerializable chemical) {
        HazardousMaterial material = null;
        try {
            materialDAO.save(DBConverter.toChemical(chemical));
        } catch (NullSlugException e) {
            e.printStackTrace();
        }
        return material;
    }

    @Override
    public Integer getLastID() {
        return materialDAO.getLastID();
    }
}
