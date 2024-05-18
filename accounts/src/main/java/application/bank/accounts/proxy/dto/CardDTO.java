package application.bank.accounts.proxy.dto;

public record CardDTO(

        String cardNumber,
        String cardType,
        int totalLimit,
        int amountUsed,
        int availableAmount
) { }
