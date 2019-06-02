package app.controller;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DownloadFileController {

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public String download(@RequestBody String body,
            Model model) throws ParseException {

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(body);

        String filePath = "test.csv";
//        File file = new File(filePath);ó
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
