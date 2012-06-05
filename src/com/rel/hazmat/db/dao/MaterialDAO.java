package com.rel.hazmat.db.dao;

import com.j256.ormlite.dao.Dao;
import com.rel.hazmat.db.model.Material;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class MaterialDAO extends GenericDAO<Material> {

    public MaterialDAO(Dao<Material, Integer> commentDAO) {
        super(commentDAO);
    }

}