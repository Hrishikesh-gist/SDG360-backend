package com.example.SDG_calculator.Impact_calculator.utils;

import com.example.SDG_calculator.Impact_calculator.models.SectoralImpact;
import com.example.SDG_calculator.Impact_calculator.models.TotalValues;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Component
@Getter
public class GetSectoralData {
    private List<SectoralImpact> sectoralImpactListList;
    private GetSectoralData() throws FileNotFoundException{
        sectoralImpactListList= new ArrayList<>();
        String currentDirectory = System.getProperty("user.dir");
        String currPath= currentDirectory + "\\Impact_calculator\\src\\main\\resources\\total_negative_sector_wise.csv";
	    System.out.println("arbitrary text : "+currentDirectory);
        String filePath=currPath;
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            CsvToBean<SectoralImpact> csvToBean = new CsvToBeanBuilder<SectoralImpact>(reader)
                    .withType(SectoralImpact.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            // Parse all records from the CSV and store them in ttList
            sectoralImpactListList = csvToBean.parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
