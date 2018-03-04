package testtask.webusage.controller.converter;

import org.springframework.stereotype.Component;
import testtask.webusage.domain.Url;
import testtask.webusage.domain.User;
import testtask.webusage.domain.WebEvent;
import testtask.webusage.domain.dto.WebEventDto;

import java.util.Random;

@Component
public class WebEventConverter {

    public WebEvent createFromDto(WebEventDto dto) {
        final Url url = Url
                .builder()
                .path(dto.getUrl())
                .build();
        final User user = User
                .builder()
                .id(dto.getUserId())
                .build();

        return WebEvent
                .builder()
                .id(new Random().nextLong())
                .url(url)
                .user(user)
                .build();
    }

    public WebEventDto createFromEntity(WebEvent entity) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
