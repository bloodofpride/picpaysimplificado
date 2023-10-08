package com.picpaysimplificado.dtos;

import com.picpaysimplificado.domain.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Long id;
    private Long senderId;
    private Long receiverId;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.senderId = transaction.getSender().getId();
        this.receiverId = transaction.getReceiver().getId();
        this.amount = transaction.getAmount();
        this.transactionDate = transaction.getTransactionDate();
    }
}
