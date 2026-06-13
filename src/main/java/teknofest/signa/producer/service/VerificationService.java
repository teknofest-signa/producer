package teknofest.signa.producer.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import teknofest.signa.producer.client.BankAClient;
import teknofest.signa.producer.client.BankBClient;
import teknofest.signa.producer.model.dto.CustomerInfo;

@Slf4j
@Service
@RequiredArgsConstructor
public class VerificationService {

    private final BankAClient bankAClient;
    private final BankBClient bankBClient;

    public List<CustomerInfo> verify(String hash) {
        CustomerInfo customerInfoFromA = bankAClient.search(hash);
        CustomerInfo customerInfoFromB = bankBClient.search(hash);

        return List.of(customerInfoFromA, customerInfoFromB);
    }
}
