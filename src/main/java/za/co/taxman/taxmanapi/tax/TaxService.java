package za.co.taxman.taxmanapi.tax;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

@Service
public class TaxService {

    private final ResourceLoader resourceLoader;

    public TaxService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public int calculateTaxAmount(int monthlyIncome, int age) throws IOException {

        List<PayeBracket> taxBrackets = loadTaxBracketsForMonthlyIncome(monthlyIncome);

        int indexOfBracket = Collections.binarySearch(taxBrackets, monthlyIncome);
        PayeBracket taxInformation = taxBrackets.get(indexOfBracket);

        return getTaxAmountForAge(age, taxInformation);
    }

    private List<PayeBracket> loadTaxBracketsForMonthlyIncome(int monthlyIncome) throws IOException {
        String resourcePath;

        // TODO: Extract these into an enum for improved extensibility.
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

        Reader streamReader = new InputStreamReader(resourceLoader.getResource(resourcePath).getInputStream());
        Type listType = new TypeToken<List<PayeBracket>>() {
        }.getType();

        return new Gson().fromJson(streamReader, listType);
    }

    private int getTaxAmountForAge(int age, PayeBracket taxInformation) {
        int taxAmount;

        if (age <= 64) {
            taxAmount = taxInformation.under65;
        } else if (age <= 74) {
            taxAmount = taxInformation.from65To74;
        } else {
            taxAmount = taxInformation.over74;
        }

        return taxAmount;
    }
}
