package com.example.SDG_calculator.Impact_calculator.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CombinedDto {

    private Double totalNegativeImpact;
    private List<SdgImpact> sdgImpacts;
    private List<SectoralDto> sectoralDto;
}
