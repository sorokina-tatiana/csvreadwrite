package app.controller;

import com.opencsv.CSVWriter;
import org.apache.commons.collections4.MultiValuedMap;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.net.Proxy.Type.HTTP;


@Controller
public class DownloadFileController {

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public String download(@RequestBody String body,
            Model model) throws ParseException {

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(body);

        String filePath = "test.csv";
//        File file = new File(filePath);
//        try (
//                FileWriter outputfile = new FileWriter(file);
//                CSVWriter writer = new CSVWriter(outputfile, ';',
//                        CSVWriter.NO_QUOTE_CHARACTER,
//                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
//                        CSVWriter.DEFAULT_LINE_END)
//        ) {
//            String[] header = {"Name", "Class", "Marks"};
//            writer.writeNext(header);
//        } catch (IOException e) {
//
//            e.printStackTrace();
//            model.addAttribute("message", "Error!");
//        }
        return "index";
    }
}
