package teknofest.signa.producer.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import teknofest.signa.producer.model.event.CreateAdminEvent;
import teknofest.signa.producer.service.EmailService;

@Component
@RequiredArgsConstructor
public class CreateAdminListener {

    private final EmailService emailService;

    @EventListener
    public void handleCreateAdminEvent(CreateAdminEvent createAdminEvent) {
        emailService.sendEmail(createAdminEvent);
    }
}
