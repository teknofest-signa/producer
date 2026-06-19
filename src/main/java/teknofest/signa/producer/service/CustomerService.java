package teknofest.signa.producer.service;

import static teknofest.signa.producer.constants.ErrorConstants.BANK_NOT_FOUND;
import static teknofest.signa.producer.constants.ErrorConstants.CUSTOMER_NOT_FOUND;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import teknofest.signa.producer.enums.CustomerStatus;
import teknofest.signa.producer.handler.exception.ResourceNotFoundException;
import teknofest.signa.producer.model.dto.customer.CustomerInfo;
import teknofest.signa.producer.model.dto.customer.RegisterCustomerRequest;
import teknofest.signa.producer.model.entity.Bank;
import teknofest.signa.producer.model.entity.Customer;
import teknofest.signa.producer.repository.BankRepository;
import teknofest.signa.producer.repository.CustomerRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final static String SORT_FIELD = "createdAt";

    private final BankRepository bankRepository;
    private final CustomerRepository customerRepository;

    public Page<CustomerInfo> getAllCustomers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(SORT_FIELD).descending());
        return customerRepository.findAll(pageable).map(this::toCustomerInfo);
    }

    private CustomerInfo toCustomerInfo(Customer customer) {
        return CustomerInfo.builder()
                .id(customer.getId())
                .bankId(customer.getBankId())
                .bankName(customer.getBankName())
                .name(customer.getName())
                .customerStatus(customer.getCustomerStatus())
                .createdAt(customer.getCreatedAt())
                .build();
    }

    public void registerCustomer(RegisterCustomerRequest registerCustomerRequest) {
        Bank bank = bankRepository.findById(registerCustomerRequest.getBankId())
                .orElseThrow(() -> new ResourceNotFoundException(BANK_NOT_FOUND));

        Customer customer = Customer.builder()
                .name(registerCustomerRequest.getName())
                .bankId(bank.getId())
                .bankName(bank.getName())
                .hash(registerCustomerRequest.getFin())
                .customerStatus(CustomerStatus.ACTIVE)
                .build();
        customerRepository.save(customer);
    }

    public void blockCustomer(UUID id) {
        Customer blockedCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CUSTOMER_NOT_FOUND));

        List<Customer> customers = customerRepository.findByHash(blockedCustomer.getHash());
        for (Customer customer : customers) {
            customer.setCustomerStatus(CustomerStatus.SUSPENDED);
        }
        customerRepository.saveAll(customers);

        blockedCustomer.setCustomerStatus(CustomerStatus.BLOCKED);
        customerRepository.save(blockedCustomer);
    }
}
