package de.holhar.spring.reactive.beerclient;

import de.holhar.spring.reactive.beerclient.model.BeerDto;
import de.holhar.spring.reactive.beerclient.model.BeerPagedList;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class BeerServiceImpl implements BeerService {
    @Override
    public Mono<BeerDto> getBeerById(UUID id, Boolean showInventoryOnHand) {
        return null;
    }

    @Override
    public Mono<BeerPagedList> listBeers(Integer pageNumb, Integer pageSize, String beerName, String beerStyle, Boolean showInventoryOnHand) {
        return null;
    }

    @Override
    public Mono<ResponseEntity> createBeer(BeerDto beerDto) {
        return null;
    }

    @Override
    public Mono<ResponseEntity> updateBeer(BeerDto beerDto) {
        return null;
    }

    @Override
    public Mono<ResponseEntity> deleteBeerById(UUID id) {
        return null;
    }

    @Override
    public Mono<BeerDto> getBeerByUPC(String upc) {
        return null;
    }
}
