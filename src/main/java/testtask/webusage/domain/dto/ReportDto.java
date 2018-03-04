package testtask.webusage.domain.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class ReportDto {

    Integer eventCount;
    Integer uniqueUserCount;
    Integer regularUserCount;

}
