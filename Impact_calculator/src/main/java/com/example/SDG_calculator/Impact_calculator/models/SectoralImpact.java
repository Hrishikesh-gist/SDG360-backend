package com.example.SDG_calculator.Impact_calculator.models;

import lombok.Data;

@Data
public class SectoralImpact {
    private String sectorCode;
    private String sectorName;
    private double totalNegative;
    private double marketValue;
    private Integer count;
}
