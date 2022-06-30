package mashup.spring.jsmr.adapter.api.like.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.picture.Picture;
import mashup.spring.jsmr.domain.profile.Profile;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchingProfileResponseDTO {

    private List<String> pictures;
    private String address;
    private Integer age;
    private String career;
    private List<String> keywords;
    private String message;

    @Builder
    public MatchingProfileResponseDTO(List<String> pictures, String address, Integer age, String career, List<String> keywords, String message){
        this.pictures = pictures;
        this.address = address;
        this.age = age;
        this.career = career;
        this.keywords = keywords;
        this.message = message;
    }

    public static MatchingProfileResponseDTO from(Profile profile,String message){
        return MatchingProfileResponseDTO.builder()
                .pictures(profile.getPictures().stream()
                        .map(Picture::getProfileUrl)
                        .collect(Collectors.toList()))
                .address(profile.getAddress())
                .age(profile.getAge())
                .career(profile.getCareer())
                .keywords(profile.getProfileKeywords().stream()
                        .map(p -> p.getKeyword().getKeyword())
                        .collect(Collectors.toList()))
                .message(message)
                .build();
    }
}
