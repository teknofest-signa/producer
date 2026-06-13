package teknofest.signa.producer.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotificationType {
    CREATE_ADMIN("create-admin", "Welcome to SiGNA!");

    private final String templateName;
    private final String subject;
}
