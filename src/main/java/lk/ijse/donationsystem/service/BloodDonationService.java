
package lk.ijse.donationsystem.service;

import lk.ijse.donationsystem.BloodType;
import lk.ijse.donationsystem.dto.BloodDonationDTO;
import lk.ijse.donationsystem.dto.BloodStockDTO;
import lk.ijse.donationsystem.dto.DonationRequestDTO;

import java.util.List;
import java.util.UUID;

public interface BloodDonationService {
    /*void createDonation(DonationRequestDTO dto);*/
    List<BloodDonationDTO> getAllDonations();
    List<BloodDonationDTO> filterDonations(UUID donorId, UUID bloodBankId, BloodType bloodType, String sort);
    void createDonation(DonationRequestDTO dto);

List<BloodStockDTO> getAllStockWithDonorInfo();

    List<BloodStockDTO> getExpiredStock();
}





/*
package lk.ijse.donationsystem.service;

import lk.ijse.donationsystem.dto.BloodDonationDTO;
import lk.ijse.donationsystem.exception.InvalidDonationException;

public interface BloodDonationService {
    BloodDonationDTO makeDonation(BloodDonationDTO donationDTO) throws InvalidDonationException;
}
*//*







package lk.ijse.donationsystem.service;

import lk.ijse.donationsystem.dto.BloodDonationDTO;
import java.util.List;
import java.util.UUID;

public interface BloodDonationService {
    BloodDonationDTO recordDonation(BloodDonationDTO bloodDonationDTO);
    List<BloodDonationDTO> getDonationsByDonor(UUID donorId);
    List<BloodDonationDTO> getDonationsByBloodBank(UUID bloodBankId);
    void deleteDonation(UUID donationId);
}*/
