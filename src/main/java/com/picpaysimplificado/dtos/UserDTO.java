package com.picpaysimplificado.dtos;

import com.picpaysimplificado.domain.user.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String firstname;

    private String lastname;

    private String cpfCnpj;

    private String email;

    private String password;

    private UserType userType;

    private BigDecimal balance;
}
