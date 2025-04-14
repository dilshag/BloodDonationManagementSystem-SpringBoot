package lk.ijse.donationsystem.exception;

import java.util.UUID;

public class BloodBankNotFoundException extends RuntimeException {
    public BloodBankNotFoundException(UUID id) {
        super("Blood bank not found");
    }
}
