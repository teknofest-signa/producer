package teknofest.signa.producer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import teknofest.signa.producer.dto.CustomerInfo;

@Component
@FeignClient(name = "bank-a", url = "${feign.client.config.bank-a.url")
public interface BankAClient {

    @GetMapping("/api/v1/search")
    CustomerInfo search(@RequestParam String hash);
}
