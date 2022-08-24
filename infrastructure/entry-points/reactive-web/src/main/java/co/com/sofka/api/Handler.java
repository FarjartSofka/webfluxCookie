package co.com.sofka.api;

import co.com.sofka.model.cookie.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class Handler {
//private  final UseCase useCase;
//private  final UseCase2 useCase2;

    public Mono<ServerResponse> listenGETCookie(ServerRequest serverRequest) {
        int codeCookie = Integer.parseInt(serverRequest.queryParam("codeCookie").orElse("-1"));
        return ServerResponse.ok().body(getCookie(codeCookie), Mono.class);
    }

    private Flux<Cookie> cookieFlux() {
        return Flux.fromIterable(Arrays.asList(
                new Cookie(1, "Cookie1"),
                new Cookie(2, "Cookie2"),
                new Cookie(3, "Cookie3"),
                new Cookie(4, "Cookie4"),
                new Cookie(5, "Cookie5"),
                new Cookie(6, "Cookie6"),
                new Cookie(7, "Cookie7"),
                new Cookie(8, "Cookie8"),
                new Cookie(9, "Cookie9"),
                new Cookie(10, "Cookie10")
        ));
    }

    private Mono<Cookie> getCookie(int code) {
        Flux<Cookie> cookies = cookieFlux();

        return cookies.filter(cookie -> cookie.getCode() == code)
                .reduce((object, datag) -> {
                    object.setCode(datag.getCode());
                    object.setName(datag.getName());
                    return object;
                })
                .switchIfEmpty(Mono.error(new RuntimeException("El codigo que esta buscando no pertenece a los existentes")))
                .onErrorResume(error -> Mono.just(new Cookie(-1, "N/A Sin Crear esta galleta")));
    }

}
