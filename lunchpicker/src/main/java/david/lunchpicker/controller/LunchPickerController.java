package david.lunchpicker.controller;

import david.lunchpicker.dto.RestaurantFrequency;
import david.lunchpicker.service.LunchPickerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
public class LunchPickerController {

    private LunchPickerService lunchPickerService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> pickRestaurant() {
        return new ResponseEntity<>(Collections.singletonMap("restaurant", lunchPickerService.pickRestaurant()), HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantFrequency>> getAllRestaurantFrequencies() {
        return new ResponseEntity<>(lunchPickerService.getAllRestaurantFrequencies(), HttpStatus.OK);
    }

}
