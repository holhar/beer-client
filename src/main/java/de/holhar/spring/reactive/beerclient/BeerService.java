package de.holhar.spring.reactive.beerclient;

import de.holhar.spring.reactive.beerclient.model.BeerDto;
import de.holhar.spring.reactive.beerclient.model.BeerPagedList;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface BeerService {

    Mono<BeerDto> getBeerById(UUID id, Boolean showInventoryOnHand);

    Mono<BeerPagedList> listBeers(Integer pageNumb, Integer pageSize, String beerName, String beerStyle,
                                  Boolean showInventoryOnHand);

    Mono<ResponseEntity> createBeer(BeerDto beerDto);

    Mono<ResponseEntity> updateBeer(BeerDto beerDto);

    Mono<ResponseEntity> deleteBeerById(UUID id);

    Mono<BeerDto> getBeerByUPC(String upc);
}
