package lk.ijse.donationsystem.exception;

public class InvalidDonationException extends RuntimeException {
    public InvalidDonationException(String donationQuantityMustBePositive) {
        super("invalid donation");
    }
}
