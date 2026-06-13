package teknofest.signa.producer.model.dto;

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
    Boolean isExists;
    String bank;
    CustomerStatus customerStatus;
}
