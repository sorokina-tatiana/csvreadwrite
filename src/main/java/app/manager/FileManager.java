package app.manager;

import app.entities.Product;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class FileManager {

    static public List<Product> uploadFile(MultipartFile file) {
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
                        records.add(new Product(values));
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return records;
    }


    static public void downloadFile(List<Product> productArray) {
        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter("text-output.csv"))
        ) {
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(bw)
                    .withSeparator(';')
                    .withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
                    .build();
            try {
                beanToCsv.write(productArray);
            } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException ex) {
                ex.printStackTrace();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    static public byte[] getFileBytes(MultipartFile file) {
        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

}
