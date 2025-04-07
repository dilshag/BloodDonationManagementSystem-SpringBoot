package lk.ijse.donationsystem.service;

import lk.ijse.donationsystem.dto.BloodDonationDTO;

public interface BloodDonationService {
    String donateBlood(BloodDonationDTO bloodDonationDTO) throws Exception;
}
