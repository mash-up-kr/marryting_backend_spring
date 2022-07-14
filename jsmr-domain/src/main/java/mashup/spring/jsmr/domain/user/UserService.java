package mashup.spring.jsmr.domain.user;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.domain.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }
}
