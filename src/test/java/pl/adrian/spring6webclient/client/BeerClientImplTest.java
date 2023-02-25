package pl.adrian.spring6webclient.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClient beerClient;

    AtomicBoolean atomicBoolean;

    @BeforeEach
    void setUp() {
        atomicBoolean = new AtomicBoolean(false);
    }

    @Test
    void testGetBeerDto() {

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeerDtos().subscribe(dto -> {
            System.out.println(dto.getBeerName());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerJson() {
        beerClient.listBeersJsonNode()
                .subscribe(jsonNode -> {
                    System.out.println(jsonNode.toPrettyString());
                    atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void listBeer() {
        beerClient.listBeer()
                .subscribe(response -> {
                    System.out.println(response);
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void listBeersMap() {
        beerClient.listBeersMap()
                .subscribe(response -> {
                    System.out.println(response);
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }
}