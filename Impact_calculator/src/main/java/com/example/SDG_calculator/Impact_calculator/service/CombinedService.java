package com.example.SDG_calculator.Impact_calculator.service;

import com.example.SDG_calculator.Impact_calculator.models.CombinedDto;
import com.example.SDG_calculator.Impact_calculator.models.SdgImpact;
import com.example.SDG_calculator.Impact_calculator.models.SectoralDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CombinedService {
    @Autowired
    SDG_Service sdgService;
    @Autowired
    SectoralService sectoralService;
//    private ResponseEntity<Double> totalNegativeImpactResponse;
//    private ResponseEntity<List<SdgImpact>> sdgWiseImpactResponse;
//    private ResponseEntity<List<SectoralDto>> sectoralDataResponse;
    public ResponseEntity<CombinedDto> getCombinedData(Double invest){

        ResponseEntity<Double> totalNegativeImpactResponse= sdgService.getNegativeImpact(invest);
        ResponseEntity<List<SdgImpact>> sdgWiseImpactResponse=sdgService.getSdgWiseImpact(invest);
        ResponseEntity<List<SectoralDto>> sectoralDataResponse = sectoralService.getSectoralData(invest);
        if (totalNegativeImpactResponse.getStatusCode() != HttpStatus.OK ||
                sdgWiseImpactResponse.getStatusCode() != HttpStatus.OK ||
                sectoralDataResponse.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        CombinedDto combinedResponse = new CombinedDto(
                totalNegativeImpactResponse.getBody(),
                sdgWiseImpactResponse.getBody(),
                sectoralDataResponse.getBody()
        );
        return new ResponseEntity<CombinedDto>(combinedResponse, HttpStatus.OK);


    }
}
