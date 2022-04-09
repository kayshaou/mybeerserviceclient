package com.anocha.mybeerclient.web.client;

import com.anocha.mybeerclient.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BeerClientTest {

    @Autowired
    BeerClient client;

    @Test
    void getBeerById() {
        BeerDto beerDto = client.getBeerById(UUID.randomUUID());
        assertNotNull(beerDto);
    }

    @Test
    void saveBeer() {
        BeerDto beerDto = BeerDto.builder().beerName("ABC").build();
        Object obj = client.saveBeer(beerDto);
        System.out.print((ResponseEntity) obj);
        assertNotNull(obj);
    }
}