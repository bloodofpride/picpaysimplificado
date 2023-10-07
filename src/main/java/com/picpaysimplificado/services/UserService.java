package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dtos.UserDTO;
import com.picpaysimplificado.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByid(Long id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(Exception::new);
    }

    public User findByDoc(String doc) throws Exception {
        return userRepository.findByCpfCnpj(doc)
                .orElseThrow(Exception::new);
    }

    public User save(UserDTO userDTO) throws Exception {
        validateUser(userDTO);
        User newUser = new User(userDTO);
        userRepository.save(newUser);
        return newUser;
    }

    private void validateUser(UserDTO userDTO) throws Exception {
        if(userRepository.findByCpfCnpj(userDTO.getCpfCnpj()).isPresent()){
            throw new Exception("já existe um usuário cadastrado no sistema com o documento: " + userDTO.getCpfCnpj());
        }

        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()){
            throw new Exception("já existe um usuário cadastrado no sistema com o email: " + userDTO.getEmail());
        }
    }
}
