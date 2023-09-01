package com.example.SDG_calculator.Impact_calculator.models;

import lombok.Data;

@Data
public class SdgImpact {
    private String name;
    private Double total_impact;
    private Double investment_impact;
    private Double impact_share;
}
