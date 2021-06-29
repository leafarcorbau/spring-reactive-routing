package com.dh.sp.reactive.routing.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestHandlerTest {

  public static final String URL = "/routing";
  @Autowired
  private WebTestClient webTestClient;

  @Test
  public void getFlux(){
    //When
    final Flux<Integer> actual = webTestClient.get()
        .uri(URL +"/flux")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .returnResult(Integer.class)
        .getResponseBody();

    //Then
    StepVerifier.create(actual)
        .expectSubscription()
        .expectNext(1,2,3, 4)
        .verifyComplete();
  }

  @Test
  public void getMono(){
    //Given
    final Integer expected = 1;
    //When
    final EntityExchangeResult<Integer> actual = webTestClient.get()
        .uri(URL +"/mono")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(Integer.class)
        .returnResult();

    //Then
    assertEquals(expected, actual.getResponseBody());
  }
}
