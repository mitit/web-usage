package testtask.webusage.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "web_events")
public class WebEvent {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_name", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "url_path", nullable = false)
    private Url url;

    @Column(name = "date")
    private Timestamp date;

}
