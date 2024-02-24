package application.bank.accounts.exception;

import java.time.LocalDateTime;

public record ErrorResponse(String apiPath, String errorMessage, LocalDateTime errorTime) {
}
