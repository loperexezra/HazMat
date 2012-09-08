package com.rel.hazmat.utils;

import com.rel.hazmat.db.model.HazardousMaterial;
import com.rel.hazmat.json.model.serializable.ChemicalResponseSerializable;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class DBConverter {
    public static HazardousMaterial toChemical(
            ChemicalResponseSerializable response) {
        return new HazardousMaterial(Integer.parseInt(response.getId()),
                response.getChemicalName(), response.getChemicalName(),
                response.getFormula(), response.getPhysicalDescription(),
                response.getDotNumber(), response.getMolecularWeight(),
                response.getSolubility(), response.getIonizationPressure(),
                response.getSpecificGravity(), response.getFlashPoint(),
                response.getPctUel(), response.getPctLel(), response.getIdlh(),
                response.getRel(), response.getPel(),
                response.getRescChemicalHazards(),
                response.getRescStateOfMatter(), response.getRescIniIsoZone(),
                response.getRescHazmatIqSog(), response.getRescLosRescPpe(),
                response.getRescMeterCockpit(), response.getRescDecon(),
                response.getPlumChemicalHazards(),
                response.getPlumStateofMatter(), response.getPlumIniIsoZone(),
                response.getPlumHazmatIqSog(), response.getPlumPlumPpe(),
                response.getPlumMeterCockpit(),
                response.getPlumTechnicalDecon(), response.getUnID(),
                response.getRadioactivity(), response.getCorrosivity(),
                response.getPolymerizationPotential(),
                response.getReactivity(), response.getDustMonitor(),
                response.getPid(), response.getFid(), response.getFreonMeter(),
                response.getTubeChip(), response.getPlumRadmeter(),
                response.getPlumTempgun(), response.getPlumCgi(),
                response.getPlumKiss(), response.getResFValue(),
                response.getResPhValue(), response.getPlumFValue(),
                response.getPlumPhValue(), response.getResRADMeter(),
                response.getResTempGun(), response.getResCGI());
    }

}
