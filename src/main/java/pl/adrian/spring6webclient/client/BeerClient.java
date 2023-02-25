package pl.adrian.spring6webclient.client;

import com.fasterxml.jackson.databind.JsonNode;
import pl.adrian.spring6webclient.model.BeerDTO;
import reactor.core.publisher.Flux;

import java.util.Map;

public interface BeerClient {

    Flux<String> listBeer();

    Flux<Map> listBeersMap();

    Flux<JsonNode> listBeersJsonNode();

    Flux<BeerDTO> listBeerDtos();
}
