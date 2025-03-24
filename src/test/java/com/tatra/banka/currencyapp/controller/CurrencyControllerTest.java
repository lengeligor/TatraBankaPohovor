package com.tatra.banka.currencyapp.controller;

import com.tatra.banka.currencyapp.ObjectFactory;
import com.tatra.banka.currencyapp.dto.CurrencyDto;
import com.tatra.banka.currencyapp.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CurrencyControllerTest {

    protected MockMvc mvc;
    private final CurrencyService service = Mockito.mock(CurrencyService.class);
    private final CurrencyController controller = new CurrencyController(service);
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
        pageable = PageRequest.of(0, 10);
    }

    @Test
    public void testGetExchangeRateList() throws Exception {
        //TODO
//        Page<CurrencyDto> mockPage = new PageImpl<>(List.of(ObjectFactory.createCurrencyDto()));
//
//        when(service.getExchangeRateList(pageable)).thenReturn(mockPage);
//
//        mvc.perform(MockMvcRequestBuilders.get("/currency/list?page=0&size=10")
//                        .accept(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk());
//
//        verify(service).getExchangeRateList(pageable);
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
