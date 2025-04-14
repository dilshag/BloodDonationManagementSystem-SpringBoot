package lk.ijse.donationsystem.service.impl;

import java.util.UUID;

public class DonationNotFoundException extends RuntimeException {
    public DonationNotFoundException(UUID donationId) {
    }
}
