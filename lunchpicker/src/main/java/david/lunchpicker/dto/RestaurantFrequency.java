package david.lunchpicker.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantFrequency {

    private String name;
    private Integer frequency;

}
