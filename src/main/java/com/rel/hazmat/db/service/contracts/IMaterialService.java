package com.rel.hazmat.db.service.contracts;

import java.util.List;

import com.rel.hazmat.dto.SearchDTO;

public interface IMaterialService {
    public List<SearchDTO> searchMaterial(String query);
}
