package teknofest.signa.producer.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorConstants {

    public static final String ADMIN_NOT_FOUND = "Admin not found!";
    public static final String TOKEN_NOT_FOUND = "Token not found!";

    public static final String EMAIL_ALREADY_EXISTS = "Email already exists!";
    public static final String USERNAME_ALREADY_EXISTS = "Username already exists!";

    public static final String FAILED_TO_UPLOAD_PHOTO = "Failed to upload photo!";
}

