package david.lunchpicker.controller;

import david.lunchpicker.dto.RestaurantFrequency;
import david.lunchpicker.service.LunchPickerService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;

@RestController
@AllArgsConstructor
public class LunchPickerHandler {

    private LunchPickerService lunchPickerService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<?> spinRestaurants(@RequestParam(defaultValue = "1")  int count) {
        Flux<?> publisher = Flux.interval(Duration.ofMillis(2))
                .map((aLong) -> Collections.singletonMap(
                        "restaurant", lunchPickerService.pickRestaurant().map(RestaurantFrequency::getName)));
        return publisher.take(count);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<RestaurantFrequency> getAllRestaurantFrequencies() {
        return Flux.fromIterable(lunchPickerService.getAllRestaurantFrequencies());
    }

}
