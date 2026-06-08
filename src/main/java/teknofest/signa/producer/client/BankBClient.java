package teknofest.signa.producer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import teknofest.signa.producer.dto.CustomerInfo;

@FeignClient(name = "bank-b", url = "${feign.client.config.bank-b.url}")
public interface BankBClient {

    @GetMapping("/api/v1/search")
    CustomerInfo search(@RequestParam String hash);
}
