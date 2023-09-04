package com.example.SDG_calculator.Impact_calculator.service;

import com.example.SDG_calculator.Impact_calculator.exception.BusinessException;
import com.example.SDG_calculator.Impact_calculator.exception.ErrorModel;
import com.example.SDG_calculator.Impact_calculator.models.SdgImpact;
import com.example.SDG_calculator.Impact_calculator.models.TotalValues;
import com.example.SDG_calculator.Impact_calculator.utils.GetTotalValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Service
public class SDG_Service {
    @Autowired
    private GetTotalValues getTotalValues;
    DecimalFormat newFormat = new DecimalFormat("#.####");
    public ResponseEntity<Double> getNegativeImpact(Double invest) {


        TotalValues tt= getTotalValues.getTt();
        if(tt!=null)
        {
            Double total_market=tt.getTotal_market_value();
            Double total_negative_sdg=tt.getTotal_negative_SDGs();
            double ans = (total_negative_sdg/(total_market*1000000))* invest;
            return new ResponseEntity<>(ans,HttpStatus.OK);
        }
        return new ResponseEntity<>(00.00, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    public ResponseEntity<List<SdgImpact>> getSdgWiseImpact(Double invest) {
        TotalValues tt= getTotalValues.getTt();
        if(tt==null)
        {
            List<ErrorModel> errors = new ArrayList<>();
            ErrorModel error = new ErrorModel();
            error.setCode("Null from CSV read");
            error.setMessage("Either of the CSV is not read successfully");
            errors.add(error);
            throw new BusinessException(errors);
        }

        List<SdgImpact> sdgList = new ArrayList<>();
        Double total_market=tt.getTotal_market_value();
        Double total_negative_sdg=tt.getTotal_negative_SDGs();
        double impactPerDollar = (total_negative_sdg/(total_market*1000000))* invest;
        for (int i = 1; i < 18; i++) {
            SdgImpact sdgImpact = new SdgImpact();
            sdgImpact.setName("SDG " + i);

            try {
                // Assuming getTotal_N_SDG1() methods exist up to getTotal_N_SDG17()
                double impact = (double) tt.getClass()
                        .getMethod("getTotal_N_SDG" + i)
                        .invoke(tt);
                double roundedImpact=Double.valueOf(newFormat.format(impact));
                double impactShare = impact / tt.getTotal_negative_SDGs();
                double roundedImpactShare =  Double.valueOf(newFormat.format(impactShare*100));
                double roundedInvestmentImpact=Double.valueOf(newFormat.format(impactShare*impactPerDollar));

                sdgImpact.setImpact_share(roundedImpactShare);
                sdgImpact.setInvestment_impact(roundedInvestmentImpact);
                sdgImpact.setTotal_impact(impact/1000000);
            } catch (Exception e) {
                List<ErrorModel> errors = new ArrayList<>();
                ErrorModel error = new ErrorModel();
                error.setCode("Method Calling error");
                error.setMessage("Unable to get the sdg data");
                errors.add(error);
                throw new BusinessException(errors);
            }
            sdgList.add(sdgImpact);

            // Continue with other actions for each iteration...
        }
        Collections.sort(sdgList, Comparator.comparingDouble(SdgImpact::getImpact_share).reversed());
        return  new ResponseEntity<>(sdgList,HttpStatus.OK);

    }
}
