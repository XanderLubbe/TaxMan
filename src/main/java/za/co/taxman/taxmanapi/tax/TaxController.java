package za.co.taxman.taxmanapi.tax;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

@RestController
public class TaxController {

    private final ResourceLoader resourceLoader;

    public TaxController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/tax/personal")
    PersonalTaxResponse returnPersonalTax(
            @RequestParam int monthlyIncome,
            @RequestParam int age
    ) throws IOException {

        if (monthlyIncome < 0 || age < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Income and/or age cannot be negative.");
        }

        String resourcePath;

        if (monthlyIncome <= 26756) {
            resourcePath = "classpath:paye-brackets-0-26756.json";
        } else if (monthlyIncome <= 49984) {
            resourcePath = "classpath:paye-brackets-26757-49984.json";
        } else if (monthlyIncome <= 87112) {
            resourcePath = "classpath:paye-brackets-49985-87112.json";
        } else if (monthlyIncome <= 130940) {
            resourcePath = "classpath:paye-brackets-87113-130940.json";
        } else if (monthlyIncome <= 197078) {
            resourcePath = "classpath:paye-brackets-130941-197078.json";
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Monthly income is only supported up to R197 078.00");
        }


        Resource resource = resourceLoader.getResource(resourcePath);
        Reader streamReader = new InputStreamReader(resource.getInputStream());
        Type listType = new TypeToken<List<PayeBracket>>() {
        }.getType();

        List<PayeBracket> listOfBrackets = new Gson().fromJson(streamReader, listType);
        int index = Collections.binarySearch(listOfBrackets, monthlyIncome);
        PayeBracket taxInformation = listOfBrackets.get(index);

        int taxAmount;
        if (age <= 64){
            taxAmount = taxInformation.under65;
        } else if (age <= 74){
            taxAmount = taxInformation.from65To74;
        } else {
            taxAmount = taxInformation.over74;
        }

        return new PersonalTaxResponse(taxAmount);
    }
}
