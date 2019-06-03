package app.controller;

import app.model.Produkt;
import app.model.ProduktRepository;
import com.google.gson.Gson;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
public class UploadFileController {

    @Autowired
    private ProduktRepository produktRepository;

    @PostMapping(value = "/upload")
    public String upload(MultipartFile file) {

        List<Produkt> records = new ArrayList<>();
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
            List<String> headers = new ArrayList<>();
            while ((nextRecord = csvReader.readNext()) != null) {
                if (!hasHeaders) {
                    hasHeaders = true;
                    headers = Arrays.asList(nextRecord);
                } else {
                    List<String> values = Arrays.asList(nextRecord);
                    if (values.size() != headers.size()) {
                    } else {
                        Produkt produkt = new Produkt(values);
                        produktRepository.save(produkt);
                        records.add(produkt);
                    }
                }
            }
            Gson gson = new Gson();
            return gson.toJson(records);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
