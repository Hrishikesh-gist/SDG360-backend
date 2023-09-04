package com.example.SDG_calculator.Impact_calculator.utils;
import com.example.SDG_calculator.Impact_calculator.exception.BusinessException;
import com.example.SDG_calculator.Impact_calculator.exception.ErrorModel;
import com.example.SDG_calculator.Impact_calculator.models.TotalValues;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import java.io.FileReader;
import java.io.IOException;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class GetTotalValues {
    private TotalValues tt;
    private GetTotalValues() throws FileNotFoundException {
        String currentDirectory = System.getProperty("user.dir");
        String currPath= currentDirectory + "\\Impact_calculator\\src\\main\\resources\\calculated_totals_new.csv";
        String filePath = currPath;
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            CsvToBean<TotalValues> csvToBean = new CsvToBeanBuilder<TotalValues>(reader)
                    .withType(TotalValues.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            // Get the first record from the CSV and return it
            tt= csvToBean.parse().stream().findFirst().orElse(null);

        } catch (IOException e) {
            List<ErrorModel> errors = new ArrayList<>();
            ErrorModel error = new ErrorModel();
            error.setCode("File read Problem");
            error.setMessage("Cannot read the file GetTotalValues");
            errors.add(error);
            throw new BusinessException(errors);
        }
    }
}
