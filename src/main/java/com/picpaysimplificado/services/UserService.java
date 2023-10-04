package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.user.User;
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
}
