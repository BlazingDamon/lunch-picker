package david.lunchpicker.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import david.lunchpicker.dto.RestaurantFrequency;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class LunchPickerService {

    private static final Integer ONE = 1;
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<RestaurantFrequency> restaurantFrequencies;
    private Integer totalFrequency;

    @PostConstruct
    private void init() throws IOException {
        TypeReference<List<RestaurantFrequency>> typeReference = new TypeReference<List<RestaurantFrequency>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/restaurants.json");
        try {
            this.restaurantFrequencies = objectMapper.readValue(inputStream,typeReference);
        } catch (IOException e){
        }
        this.totalFrequency = this.restaurantFrequencies.stream().mapToInt(RestaurantFrequency::getFrequency).sum();
    }

    public Optional<RestaurantFrequency> pickRestaurant() {
        int randomKey = ThreadLocalRandom.current().nextInt(ONE, totalFrequency);
        for (int i = 0; i < restaurantFrequencies.size(); i++) {
            randomKey -= restaurantFrequencies.get(i).getFrequency();
            if (randomKey < 0) {
                return Optional.of(restaurantFrequencies.get(i));
            }
        }
        return Optional.empty();
    }

    public List<RestaurantFrequency> getAllRestaurantFrequencies() {
        return this.restaurantFrequencies;
    }

}
