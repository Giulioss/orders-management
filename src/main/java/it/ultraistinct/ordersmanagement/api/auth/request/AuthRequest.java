package it.ultraistinct.ordersmanagement.api.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class AuthRequest {

    @NotBlank
    @Length(max = 255)
    private String username;

    @NotBlank
    private String password;
}
