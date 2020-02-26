package com.godeltech.pt11.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.godeltech.pt11.entity.Colour;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.core.convert.converter.Converter;

import java.io.IOException;

@JsonComponent
public class ColourConverter extends JsonDeserializer<Colour> implements Converter<String, Colour> {

    @Override
    public Colour convert(String colour) {
        return Colour.valueOf(colour.toUpperCase());
    }

    @Override
    public Colour deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String text = jsonParser.getText().toUpperCase();
        return Colour.valueOf(text);
    }
}