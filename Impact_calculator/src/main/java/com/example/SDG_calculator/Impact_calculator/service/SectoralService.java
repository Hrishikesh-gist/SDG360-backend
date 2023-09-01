package com.example.SDG_calculator.Impact_calculator.service;

import com.example.SDG_calculator.Impact_calculator.models.SectoralDto;
import com.example.SDG_calculator.Impact_calculator.models.SectoralImpact;
import com.example.SDG_calculator.Impact_calculator.models.TotalValues;
import com.example.SDG_calculator.Impact_calculator.utils.GetSectoralData;
import com.example.SDG_calculator.Impact_calculator.utils.GetTotalValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Service
public class SectoralService {
    @Autowired
    GetSectoralData getSectoralData;
    @Autowired
    GetTotalValues getTotalValues;
    DecimalFormat newFormat = new DecimalFormat("#.##");
    public ResponseEntity<List<SectoralDto>> getSectoralData(Double invest)
    {
        List<SectoralDto> ll=new ArrayList<>();
        TotalValues tt= getTotalValues.getTt();
        Double investImpact= tt.getTotal_negative_SDGs()/(tt.getTotal_market_value()*1000000)*invest;
        Double totalNegative= tt.getTotal_negative_SDGs();
        List<SectoralImpact> siList=getSectoralData.getSectoralImpactListList();
        for(SectoralImpact si : siList)
        {
            SectoralDto sd= new SectoralDto();
            sd.setSectorCode(si.getSectorCode());
            sd.setSectorName(si.getSectorName());
            sd.setCount(si.getCount());
            Double impactShare=Double.valueOf(newFormat.format(si.getTotalNegative()/tt.getTotal_negative_SDGs()*100));
            sd.setImpactShare(impactShare);
            sd.setInvestmentImpact(Double.valueOf(newFormat.format(sd.getImpactShare()*investImpact/100)));
            sd.setTotalImpact(si.getMarketValue());
            ll.add(sd);
        }
        Collections.sort(ll, Comparator.comparingDouble(SectoralDto::getInvestmentImpact));
        return new ResponseEntity<>(ll, HttpStatus.OK);
    }
}
