package app.controller;

import app.model.Produkt;
import com.northconcepts.datapipeline.core.Record;
import com.northconcepts.datapipeline.core.RecordList;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class UploadFileController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String upload(@RequestParam("fileToUpload") MultipartFile file, Model model) {

        List<List<String>> records = new ArrayList<>();
        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
                ByteArrayInputStream inputFilestream = new ByteArrayInputStream(bytes);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputFilestream))
        ) {
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            CSVReader csvReader = new CSVReaderBuilder(br).withCSVParser(parser).build();
            String[] nextRecord;
            boolean hasHeaders = false;
            while ((nextRecord = csvReader.readNext()) != null) {
                if (!hasHeaders) {
                    hasHeaders = true;
                    model.addAttribute("headers", nextRecord);
                } else {
                    List<String> values = Arrays.asList(nextRecord);
                    records.add(values);
                    }
            }
            model.addAttribute("records", records);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "index";
    }
}
