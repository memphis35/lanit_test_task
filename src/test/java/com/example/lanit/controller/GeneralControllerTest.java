package com.example.lanit.controller;

import com.example.lanit.domain.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:data.sql")
public class GeneralControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenAppropriatePersonIsPost_thenSuccess() throws Exception {
        final Person person = new Person(10L, "Michael Gerard Tyson", new Date(-108097200));
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        final ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        final String jsonPerson = writer.writeValueAsString(person);
        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonPerson))
                .andExpect(status().isOk());
    }

    @Test
    public void whenInappropriatePersonIsPost_thenFail_nullId() throws Exception {
        final Person person = new Person(null, "Michael Gerard Tyson", new Date(-108097200));
        final String jsonPerson = convertToJson(person);

        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonPerson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenInappropriatePersonIsPost_thenFail_nullName() throws Exception {
        final Person person = new Person(1L, "     ", new Date(-108097200));
        final String jsonPerson = convertToJson(person);

        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonPerson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenInappropriatePersonIsPost_thenFail_nullDate() throws Exception {
        final Person person = new Person(1L, "Michael Gerard Tyson", null);
        final String jsonPerson = convertToJson(person);

        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonPerson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenInappropriatePersonIsPost_thenFail_alreadyExists() throws Exception {
        final Person person = new Person(1L, "Michael Gerard Tyson", new Date(-108097200));
        final String jsonPerson = convertToJson(person);

        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonPerson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenAppropriateCarIsPost_thenSuccess() throws Exception {
        String normalCar = "{\"id\": 10, \"model\": \"Mazda-RX7\", \"horsepower\": 280, \"ownerId\" : 3}";
        mockMvc.perform(post("/car")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(normalCar))
                .andExpect(status().isOk());
    }

    @Test
    public void whenInaproppriateCarIsPost_thenFail_nullId() throws Exception {
        String nullIdCar = "{\"id\": null, \"model\": \"Mazda-RX7\", \"horsepower\": 280, \"ownerId\" : 3}";
        mockMvc.perform(post("/car")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(nullIdCar))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenInaproppriateCarIsPost_thenFail_nullModel() throws Exception {
        String nullModelCar = "{\"id\": 10, \"model\": null, \"horsepower\": 280, \"ownerId\" : 3}";
        mockMvc.perform(post("/car")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(nullModelCar))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenInaproppriateCarIsPost_thenFail_nullHp() throws Exception {
        String nullHpCar = "{\"id\": 1, \"model\": \"Mazda-RX7\", \"horsepower\": 280, \"ownerId\" : 3}";
        mockMvc.perform(post("/car")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(nullHpCar))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenInaproppriateCarIsPost_thenFail_negativeHp() throws Exception {
        String negativeHpCar = "{\"id\": 1, \"model\": \"Mazda-RX7\", \"horsepower\": -1, \"ownerId\" : 3}";
        mockMvc.perform(post("/car")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(negativeHpCar))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenInaproppriateCarIsPost_thenFail_zeroHp() throws Exception {
        String zeroHpCar = "{\"id\": 1, \"model\": \"Mazda-RX7\", \"horsepower\": 0, \"ownerId\" : 3}";
        mockMvc.perform(post("/car")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(zeroHpCar))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenInaproppriateCarIsPost_thenFail_nullOwner() throws Exception {
        String nullOwnerCar = "{\"id\": 1, \"model\": \"Mazda-RX7\", \"horsepower\": 0, \"ownerId\" : 3}";
        mockMvc.perform(post("/car")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(nullOwnerCar))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenInaproppriateCarIsPost_thenFail_absentOwner() throws Exception {
        String notExistedOwnerCar = "{\"id\": 1, \"model\": \"Mazda-RX7\", \"horsepower\": 0, \"ownerId\" : -1}";
        mockMvc.perform(post("/car")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(notExistedOwnerCar))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenInaproppriateCarIsPost_thenFail_youngOwner() throws Exception {
        String tooYoungOwnerCar = "{\"id\": 1, \"model\": \"Mazda-RX7\", \"horsepower\": 0, \"ownerId\" : 5}";
        mockMvc.perform(post("/car")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(tooYoungOwnerCar))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenInaproppriateCarIsPost_thenFail_existedId() throws Exception {
        String existedIdCar = "{\"id\": 1, \"model\": \"Mazda-RX7\", \"horsepower\": 280, \"ownerId\" : 3}";
        mockMvc.perform(post("/car")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(existedIdCar))
                .andExpect(status().isBadRequest());
    }

    private <T> String convertToJson(T value) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        final ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString(value);
    }
}
