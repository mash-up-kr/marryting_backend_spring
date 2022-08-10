package mashup.spring.jsmr.adapter.api.weddingChannel.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.picture.Picture;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.wedding.Wedding;
import mashup.spring.jsmr.domain.weddingChannel.WeddingChannel;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WeddingChannelGuestResponseDTO {

    @ApiModelProperty(value = "웨딩 Id", example = "1L")
    private Long weddingId;

    @ApiModelProperty(value = "프로필 Id", example = "1L")
    private Long profileId;

    @ApiModelProperty(value = "이름", example = "규니")
    private String name;

    @ApiModelProperty(value = "나이", example = "25")
    private Integer age;

    @ApiModelProperty(value = "주소", example = "경기도 시흥시~")
    private String address;

    @ApiModelProperty(value = "직업", example = "개발자")
    private String career;

    @ApiModelProperty(value = "사진 리스트", example = "['URL1', 'URL2']")
    private List<String> profileUrl;

    @ApiModelProperty(value = "키워드 리스트", example = "['keyword1', 'keyword3', 'keyword3']")
    private List<String> keywords;

    @Builder
    public WeddingChannelGuestResponseDTO(
            Long weddingId,
            Long profileId,
            String name,
            Integer age,
            String address,
            String career,
            List<String> profileUrl,
            List<String> keywords
    ) {
        this.weddingId = weddingId;
        this.profileId = profileId;
        this.name = name;
        this.age = age;
        this.address = address;
        this.career = career;
        this.profileUrl = profileUrl;
        this.keywords = keywords;
    }

    public static WeddingChannelGuestResponseDTO from(WeddingChannel weddingChannel) {
        Profile profile = weddingChannel.getProfile();
        Wedding wedding = weddingChannel.getWedding();

        int nowYear = LocalDate.now().getYear();
        int age = nowYear - Integer.parseInt(profile.getBirth().substring(0, 4)) + 1;

        return WeddingChannelGuestResponseDTO.builder()
                .weddingId(wedding.getId())
                .profileId(profile.getId())
                .name(profile.getName())
                .address(profile.getAddress())
                .age(age)
                .career(profile.getCareer())
                .profileUrl(profile.getPictures().stream()
                        .map(Picture::getProfileUrl)
                        .collect(Collectors.toList()))
                .keywords(profile.getProfileKeywords().stream()
                        .map(profileKeyword -> profileKeyword.getKeyword().getKeyword())
                        .collect(Collectors.toList()))
                .build();
    }
}
