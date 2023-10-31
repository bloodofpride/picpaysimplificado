package com.picpaysimplificado.dtos;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
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
    private Long id;
    private String firstname;

    private String lastname;


    private String cpfCnpj;

    private String email;

    private String password;

    private UserType userType;

    private BigDecimal balance;

    public UserDTO(User user) {
        this.id = user.getId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.cpfCnpj = user.getCpfCnpj();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.userType = user.getUserType();
        this.balance = user.getBalance();
    }
}
