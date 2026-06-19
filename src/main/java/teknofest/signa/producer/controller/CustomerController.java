package teknofest.signa.producer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teknofest.signa.producer.model.dto.customer.CustomerInfo;
import teknofest.signa.producer.service.CustomerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    public Page<CustomerInfo> getAllSimulationCustomers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return customerService.getAllSimulationCustomers(page, size);
    }
}
