package teknofest.signa.producer.model.dto.transaction;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import teknofest.signa.producer.enums.TransactionMethod;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateTransactionRequest {

    @NotNull(message = "Transaction method cannot be blank!")
    TransactionMethod transactionMethod;

    UUID fromAccountId;
    UUID fromCardId;

    UUID toAccountId;
    UUID toCardId;

    String billId;
    String creditId;

    BigDecimal amount;

    String currency;

    String description;

    String referenceId;
}
