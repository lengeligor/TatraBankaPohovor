package com.tatra.banka.currencyapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tatra.banka.currencyapp.ObjectFactory;
import com.tatra.banka.currencyapp.dto.CurrencyDto;
import com.tatra.banka.currencyapp.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CurrencyControllerTest {

    protected MockMvc mvc;
    private final CurrencyService service = Mockito.mock(CurrencyService.class);
    private final CurrencyController controller = new CurrencyController(service);

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    @Test
    void testCreateCurrency() throws Exception {
        CurrencyDto currencyDto = ObjectFactory.createCurrencyDto();
        when(service.createCurrency(currencyDto)).thenReturn(1L);

        ObjectMapper objectMapper = new ObjectMapper();

        mvc.perform(MockMvcRequestBuilders.post("/currency")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(currencyDto)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetCurrency() throws Exception {
        CurrencyDto currencyDto = ObjectFactory.createCurrencyDto();
        when(service.getCurrency(1L)).thenReturn(currencyDto);

        mvc.perform(MockMvcRequestBuilders.get("/currency/1")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(service).getCurrency(1L);
    }

    @Test
    void testDeleteCurrency() throws Exception {
        doNothing().when(service).delete(1L);

        mvc.perform(MockMvcRequestBuilders.delete("/currency/1")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(service).delete(1L);
    }

    @Test
    void testGetExchangeRateList() throws Exception {
        List<CurrencyDto> mockPage = List.of(ObjectFactory.createCurrencyDto());

        when(service.getExchangeRateList()).thenReturn(mockPage);

        mvc.perform(MockMvcRequestBuilders.get("/currency/list")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(service).getExchangeRateList();
    }

    @Test
    void convert() throws Exception {
        String uri = "/currency/convert?fromCurrencyCode=CHF&toCurrencyCode=CZK&value=1";

        when(service.convert("CHF", "CZK", 1.0)).thenReturn(26.13);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        // verify response
        verify(service).convert("CHF", "CZK", 1.0);
    }
}
