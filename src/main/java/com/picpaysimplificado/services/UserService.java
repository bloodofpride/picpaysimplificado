package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dtos.UserDTO;
import com.picpaysimplificado.repositories.UserRepository;
import com.picpaysimplificado.services.exceptions.BusinessRuleException;
import com.picpaysimplificado.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByid(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado com o id: " + id));
    }

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    public User findByDoc(String doc) throws Exception {
        return userRepository.findByCpfCnpj(doc)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado com o cpf/cnpj " + doc));
    }

    public UserDTO save(UserDTO userDTO) {
        validateUser(userDTO);
        User newUser = new User(userDTO);
        save(newUser);
        return new UserDTO(newUser);
    }

    public User save(User user) {
        userRepository.save(user);
        return user;
    }

    private void validateUser(UserDTO userDTO) {
        if(userRepository.findByCpfCnpj(userDTO.getCpfCnpj()).isPresent()){
            throw new BusinessRuleException("já existe um usuário cadastrado no sistema com o documento: " + userDTO.getCpfCnpj());
        }

        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()){
            throw new BusinessRuleException("já existe um usuário cadastrado no sistema com o email: " + userDTO.getEmail());
        }
    }
}
