package siseon.backend.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponse {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private Float height;
    private Float leftVision;
    private Float rightVision;
    private String imageUrl;
    private String settings;
}
