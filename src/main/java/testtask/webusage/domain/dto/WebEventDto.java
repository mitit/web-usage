package testtask.webusage.domain.dto;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class WebEventDto {

    String userName;
    String url;
    long date;

}
