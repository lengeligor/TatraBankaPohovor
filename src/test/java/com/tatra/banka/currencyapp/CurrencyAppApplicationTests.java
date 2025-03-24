package com.tatra.banka.currencyapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {CurrencyAppApplicationTests.class})
class CurrencyAppApplicationTests {

    @Test
    void contextLoads() {
        Assertions.assertTrue(true);
    }

    @Test
    void testMain(){
        String[] args = new String[]{};

        try(MockedStatic<SpringApplication> mockedStatic = Mockito.mockStatic(SpringApplication.class)) {
            mockedStatic.when(() -> SpringApplication.run(CurrencyAppApplication.class, args))
                    .thenReturn(null);

            CurrencyAppApplication.main(args);

            mockedStatic.verify(() -> SpringApplication.run(CurrencyAppApplication.class,args), Mockito.times(1));
        }
    }


}
