package mashup.spring.jsmr.adapter.api.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.adapter.api.profile.dto.CreateProfileRequestDTO;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserWithProfileRequestDTO {

    @NotNull
    private LoginRequestDTO login;

    @NotNull
    private CreateProfileRequestDTO profile;
}