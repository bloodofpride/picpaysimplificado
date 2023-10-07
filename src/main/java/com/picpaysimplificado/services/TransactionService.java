package com.picpaysimplificado.services;

import com.picpaysimplificado.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;
    private UserService userService;

}
