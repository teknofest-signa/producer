package teknofest.signa.producer.model.dto.customer;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterCustomerRequest {

    @NotBlank(message = "Name cannot be empty!")
    String name;

    @NotBlank(message = "FIN cannot be empty!")
    String fin;

    @NotBlank(message = "BankId cannot be empty!")
    UUID bankId;
}
