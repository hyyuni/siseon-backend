package siseon.backend.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCreateRequest {
    private String name;
    private LocalDate birthDate;
    private Float height;
    private Float vision;
    private String settings;
}