package com.radekrates.service.apireader;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
@Slf4j
@NoArgsConstructor
public class DataFixerJsonReader {
    private DataFixerEurBase readerEurBase = new DataFixerEurBase();

    public DataFixerEurBase saveRatesBasedOnEur(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            readerEurBase.setEur(jsonObject.getJSONObject("rates").getDouble("EUR"));
            readerEurBase.setChf(jsonObject.getJSONObject("rates").getDouble("CHF"));
            readerEurBase.setUsd(jsonObject.getJSONObject("rates").getDouble("USD"));
            readerEurBase.setPln(jsonObject.getJSONObject("rates").getDouble("PLN"));
            readerEurBase.setGbp(jsonObject.getJSONObject("rates").getDouble("GBP"));
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            log.info("Data Fixer Api has been parsed");
        }
        return readerEurBase;
    }
}
