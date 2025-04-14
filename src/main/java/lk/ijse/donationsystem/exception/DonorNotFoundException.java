package lk.ijse.donationsystem.exception;

import java.util.UUID;

public class DonorNotFoundException extends RuntimeException {
    public DonorNotFoundException(UUID donorId) {
        super("Donor not fount with id " + donorId);

    }
}
