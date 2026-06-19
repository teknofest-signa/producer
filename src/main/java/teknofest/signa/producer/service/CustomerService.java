package teknofest.signa.producer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import teknofest.signa.producer.model.dto.customer.CustomerInfo;
import teknofest.signa.producer.model.entity.Customer;
import teknofest.signa.producer.repository.CustomerRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final static String SORT_FIELD = "createdAt";

    private final CustomerRepository customerRepository;

    public Page<CustomerInfo> getAllSimulationCustomers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(SORT_FIELD).descending());
        return customerRepository.findAll(pageable).map(this::toSimulationCustomerInfo);
    }

    private CustomerInfo toSimulationCustomerInfo(Customer customer) {
        return CustomerInfo.builder()
                .id(customer.getId())
                .bankId(customer.getBankId())
                .bankName(customer.getBankName())
                .name(customer.getName())
                .customerStatus(customer.getCustomerStatus())
                .createdAt(customer.getCreatedAt())
                .build();
    }
}
