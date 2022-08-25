package mashup.spring.jsmr.application.like;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.like.dto.CreateLikeRequestDTO;
import mashup.spring.jsmr.adapter.api.like.dto.CreateLikeResponseDTO;
import mashup.spring.jsmr.domain.like.Likes;
import mashup.spring.jsmr.domain.like.LikesService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikesApplicationService {

    private final LikesService likesService;

    public CreateLikeResponseDTO createLike(CreateLikeRequestDTO requestDTO) {
        Likes likes = likesService.createLike(requestDTO.getSenderProfileId(), requestDTO.getReceiverProfileId(), requestDTO.getMessage());
        return CreateLikeResponseDTO.from(likes);
    }

}
