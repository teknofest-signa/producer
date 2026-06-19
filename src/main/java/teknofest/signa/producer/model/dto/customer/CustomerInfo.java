package teknofest.signa.producer.model.dto.customer;

import java.time.Instant;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import teknofest.signa.producer.enums.CustomerStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerInfo {

    UUID id;

    UUID bankId;

    String bankName;

    String name;

    CustomerStatus customerStatus;

    Instant createdAt;
}
