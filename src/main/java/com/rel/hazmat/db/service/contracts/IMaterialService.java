package com.rel.hazmat.db.service.contracts;

import java.util.List;

import com.rel.hazmat.db.model.HazardousMaterial;
import com.rel.hazmat.dto.SearchDTO;
import com.rel.hazmat.json.model.serializable.ChemicalResponseSerializable;

/**
 * 
 * @author Lope Chupijay Emano
 *
 */
public interface IMaterialService {
    public List<SearchDTO> searchMaterial(String query);

    public List<HazardousMaterial> save(ChemicalResponseSerializable[] chemicals);

    public HazardousMaterial save(ChemicalResponseSerializable chemicals);

    public Integer getLastID();
}
