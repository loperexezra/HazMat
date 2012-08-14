package com.rel.hazmat.utils;

import com.google.common.base.Strings;

/**
 * 
 * @author Lope Chupijay Emano
 * 
 */
public class HazMatUtils {
    public static String formatFormula(String formula) {
        if (!Strings.isNullOrEmpty(formula))
            return formula.replaceAll("(\\w|\\))([0-9])", "$1<sub>$2</sub>");
        else
            return "TBA";
    }
}
