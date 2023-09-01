package com.example.SDG_calculator.Impact_calculator.controllers;

import com.example.SDG_calculator.Impact_calculator.models.CombinedDto;
import com.example.SDG_calculator.Impact_calculator.models.SdgImpact;
import com.example.SDG_calculator.Impact_calculator.models.SectoralDto;
import com.example.SDG_calculator.Impact_calculator.models.SectoralImpact;
import com.example.SDG_calculator.Impact_calculator.service.CombinedService;
import com.example.SDG_calculator.Impact_calculator.service.SDG_Service;
import com.example.SDG_calculator.Impact_calculator.service.SectoralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sdg")
public class SDG_Controller {
    @Autowired
    SDG_Service sdgService;
    @Autowired
    SectoralService sectoralService;
    @Autowired
    CombinedService combinedService;
    @GetMapping("/Impact/{invest}")
    public  ResponseEntity<CombinedDto> getImpact(@PathVariable Double invest)
    {
        return combinedService.getCombinedData(invest);
    }
    @GetMapping("/negativeImpact/{invest}")
    public ResponseEntity<Double> getTotalNegativeImpact(@PathVariable Double invest)
    {
        return sdgService.getNegativeImpact(invest);
    }
    @GetMapping("/negativeImpact/sdgWise/{invest}")
    public ResponseEntity<List<SdgImpact>> SdgWiseImpact(@PathVariable Double invest)
    {
        return sdgService.getSdgWiseImpact(invest);

    }
    @GetMapping("/sectoralData/{invest}")
    public ResponseEntity<List<SectoralDto>> sectoralData(@PathVariable Double invest)
    {
        return sectoralService.getSectoralData(invest);
    }
}
