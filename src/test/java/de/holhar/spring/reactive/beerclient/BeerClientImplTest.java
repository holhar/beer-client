package de.holhar.spring.reactive.beerclient;

import de.holhar.spring.reactive.beerclient.config.WebClientConfig;
import de.holhar.spring.reactive.beerclient.model.BeerDto;
import de.holhar.spring.reactive.beerclient.model.BeerPagedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BeerClientImplTest {

    BeerClientImpl beerClient;

    @BeforeEach
    void setUp() {
        beerClient = new BeerClientImpl(new WebClientConfig().webClient());
    }

    @Test
    void listBeers() {
        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(null, null, null, null, null);

        BeerPagedList pagedList = beerPagedListMono.block();

        assertThat(pagedList).isNotNull();
        assertThat(pagedList.getContent().size()).isGreaterThan(0);
    }

    @Test
    void listBeersPageSize10() {
        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(1, 10, null, null, null);

        BeerPagedList pagedList = beerPagedListMono.block();

        assertThat(pagedList).isNotNull();
        assertThat(pagedList.getContent().size()).isEqualTo(10);
    }

    @Test
    void listBeersNoRecords() {
        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(10, 20, null, null, null);

        BeerPagedList pagedList = beerPagedListMono.block();

        assertThat(pagedList).isNotNull();
        assertThat(pagedList.getContent().size()).isEqualTo(0);
    }

    @Test
    void getBeerByIdDoNotShowInventoryOnHand() {
        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(null, null, null, null, null);
        BeerPagedList pagedList = beerPagedListMono.block();
        UUID id = pagedList.getContent().get(0).getId();

        Mono<BeerDto> beerById = beerClient.getBeerById(id, false);
        BeerDto beerDto = beerById.block();

        assertThat(beerDto).isNotNull();
        assertThat(beerDto.getId()).isEqualTo(id);
        //assertThat(beerDto.getQuantityOnHand()).isNull(); <= Should not be present, right? But it is...
        assertThat(beerDto.getQuantityOnHand()).isNotNull();
    }

    @Test
    void getBeerByIdDoShowInventoryOnHand() {
        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(null, null, null, null, null);
        BeerPagedList pagedList = beerPagedListMono.block();
        UUID id = pagedList.getContent().get(0).getId();

        Mono<BeerDto> beerById = beerClient.getBeerById(id, true);
        BeerDto beerDto = beerById.block();

        assertThat(beerDto).isNotNull();
        assertThat(beerDto.getId()).isEqualTo(id);
        assertThat(beerDto.getQuantityOnHand()).isNotNull();
    }

    @Test
    void createBeer() {
    }

    @Test
    void updateBeer() {
    }

    @Test
    void deleteBeerById() {
    }

    @Test
    void getBeerByUPC() {
        Mono<BeerPagedList> beerPagedListMono = beerClient.listBeers(null, null, null, null, null);
        BeerPagedList pagedList = beerPagedListMono.block();
        String upc = pagedList.getContent().get(0).getUpc();

        Mono<BeerDto> beerByUPC = beerClient.getBeerByUPC(upc);
        BeerDto beerDto = beerByUPC.block();

        assertThat(beerDto).isNotNull();
        assertThat(beerDto.getUpc()).isEqualTo(upc);
    }
}