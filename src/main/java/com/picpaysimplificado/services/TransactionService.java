package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.repositories.TransactionRepository;
import com.picpaysimplificado.services.exceptions.BusinessRuleException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    public static final String AUTORIZADOR_EXTERNO = "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6";
    public static final String TRANSACAO_SUCESSO = "Transação realizada com sucesso.";
    private TransactionRepository transactionRepository;
    private UserService userService;
    private RestTemplate restTemplate;
    private NotificationService notificationService;

    public TransactionService(TransactionRepository transactionRepository, UserService userService, RestTemplate restTemplate, NotificationService notificationService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.restTemplate = restTemplate;
        this.notificationService = notificationService;
    }

    @Transactional
    public TransactionDTO makeTransfer(TransactionDTO transactionDTO) {
        User sender = userService.findByid(transactionDTO.getSenderId());
        User receiver = userService.findByid(transactionDTO.getReceiverId());

        validateTransaction(transactionDTO.getAmount(), sender);

        if(!isAuthorizedTransaction(sender, transactionDTO.getAmount())){
            throw new BusinessRuleException("Transação não autorizada.");
        }

        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAmount(transactionDTO.getAmount());

        sender.setBalance(sender.getBalance().subtract(transactionDTO.getAmount()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.getAmount()));

        transactionRepository.save(transaction);
        userService.save(sender);
        userService.save(receiver);

//        this.notificationService.sendNotification(sender, TRANSACAO_SUCESSO);
//        this.notificationService.sendNotification(receiver, TRANSACAO_SUCESSO);

        return new TransactionDTO(transaction);
    }

    private void validateTransaction(BigDecimal amount, User sender) {

        if(sender.getUserType().equals(UserType.SHOPKEEPER)){
            throw new BusinessRuleException("Lojistas não podem efetuar transferências.");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw new BusinessRuleException("A transferência não pode ser realizado por falta de saldo.");
        }
    }

    private boolean isAuthorizedTransaction(User sender, BigDecimal amount){
//        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(AUTORIZADOR_EXTERNO, Map.class);
//
//        if(authorizationResponse.getStatusCode() == HttpStatus.OK ) {
//            String message = (String) authorizationResponse.getBody().get("message");
//            return message.equalsIgnoreCase("Autorizado");
//        }

//        return false;

        return true;
    }
}
