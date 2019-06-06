package app.controller;

import app.entities.Product;
import app.entities.ProductRepository;
import app.manager.FileManager;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


@RestController
public class AppController {

    @Autowired
    private ProductRepository productRepository;


    @POST
    @RequestMapping(value = "/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String upload(MultipartFile file) {
        List<Product> records = FileManager.uploadFile(file);
        for (Product p : records
        ) {
            productRepository.save(p);
        }
        return new Gson().toJson(records);
    }


    @GET
    @RequestMapping(value = "/download")
    public void download() {
        FileManager.downloadFile(productRepository.findAll());
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
            Product productChange = gson.fromJson(crunchifyBuilder.toString(), Product.class);
            Product productDatabase = productRepository.findByHauptartikelnr(productChange.getHauptartikelnr());
            productDatabase.copyFields(productChange);
            productRepository.save(productDatabase);
        } catch (Exception e) {
            System.out.println("Error Parsing: - ");
        }
    }
}
