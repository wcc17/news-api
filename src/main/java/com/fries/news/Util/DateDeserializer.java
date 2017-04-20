package com.fries.news.Util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class DateDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = jp.getText();

        try {
            Date parsedDate = format.parse(date);
//            return parsedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return parsedDate;
        } catch (ParseException e) {
            //TODO: THIS NEEDS TO BE HANDLED THO
            System.out.println(e);
            return null;
        }
    }
}