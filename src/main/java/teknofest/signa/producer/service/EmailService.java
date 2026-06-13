package teknofest.signa.producer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import teknofest.signa.producer.model.event.CreateAdminEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    public void sendEmail(CreateAdminEvent createAdminEvent) {
    }
}
