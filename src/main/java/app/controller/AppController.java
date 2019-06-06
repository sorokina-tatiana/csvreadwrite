package app.controller;

import app.model.Product;
import app.model.ProduktRepository;
import com.google.gson.Gson;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
public class AppController {

    @Autowired
    private ProduktRepository productRepository;


    @PostMapping(value = "/upload")
    public String upload(MultipartFile file) {
        List<Product> records = new ArrayList<>();
        byte[] bytes = getFileBytes(file);

        try (
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                BufferedReader br = new BufferedReader(new InputStreamReader(bais))
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
                        records.add(saveNewProduct(values));
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


    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download() throws ParseException {
        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter("text-output.csv"))
        ) {
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(bw)
                    .withSeparator(';')
                    .withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
                    .build();

            List<Product> productArray = productRepository.findAll();

            try {
                beanToCsv.write(productArray);
            } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException ex) {

            }

        } catch (IOException ex) {
        }
    }


    @POST
    @RequestMapping(value = "/save-entry")
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveEntry(InputStream incomingData) {
        StringBuilder crunchifyBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
            String line = null;
            while ((line = in.readLine()) != null) {
                crunchifyBuilder.append(line);
            }
            Gson gson = new Gson();
            Product produktChange = gson.fromJson(crunchifyBuilder.toString(), Product.class);
            Product produktDatabase = productRepository.findByHauptartikelnr(produktChange.getHauptartikelnr());
            produktDatabase.copyFields(produktChange);
            productRepository.save(produktDatabase);
        } catch (Exception e) {
            System.out.println("Error Parsing: - ");
        }
    }


    public byte[] getFileBytes(MultipartFile file) {
        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }


    public Product saveNewProduct(List<String> values) {
        Product product = new Product(values);
        productRepository.save(product);
        return product;
    }
}
