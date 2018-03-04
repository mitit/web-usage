package testtask.webusage.controller.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import testtask.webusage.domain.Url;
import testtask.webusage.domain.User;
import testtask.webusage.domain.WebEvent;
import testtask.webusage.domain.dto.WebEventDto;
import testtask.webusage.repository.UrlRepository;
import testtask.webusage.repository.UserRepository;

import java.sql.Timestamp;

@Component
public class WebEventConverter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UrlRepository urlRepository;

    public WebEvent createFromDto(WebEventDto dto) {

        if (urlRepository.findByPath(dto.getUrl()) == null)
            urlRepository.save(Url
                    .builder()
                    .path(dto.getUrl())
                    .build());

        if (userRepository.findByName(dto.getUserName()) == null)
            userRepository.save(User
                    .builder()
                    .name(dto.getUserName())
                    .build());

        final Url url = Url
                .builder()
                .path(dto.getUrl())
                .build();
        final User user = User
                .builder()
                .name(dto.getUserName())
                .build();

        return WebEvent
                .builder()
                .url(url)
                .user(user)
                .date(new Timestamp(dto.getDate()))
                .build();
    }

    public WebEventDto createFromEntity(WebEvent entity) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
