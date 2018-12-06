package david.lunchpicker;

import david.lunchpicker.controller.LunchPickerHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.reactive.function.server.*;

@SpringBootApplication
@PropertySource("classpath:application.yml")
public class LunchPickerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LunchPickerApplication.class, args);
    }

}
