package com.example.SDG_calculator.Impact_calculator.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectoralDto {
    private String sectorCode;
    private String sectorName;
    private Integer count;
    private Double impactShare;
    private Double investmentImpact;
    private Double totalImpact;

}
