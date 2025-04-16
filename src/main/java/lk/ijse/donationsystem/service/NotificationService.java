package lk.ijse.donationsystem.service;

import lk.ijse.donationsystem.entity.BloodBank;
import lk.ijse.donationsystem.entity.Donor;
import lk.ijse.donationsystem.entity.User;

public interface NotificationService {
    void notifyBloodBankAdded(User admin, BloodBank bloodBank);
    void notifyDonorAdded(User admin, Donor donor);
}
