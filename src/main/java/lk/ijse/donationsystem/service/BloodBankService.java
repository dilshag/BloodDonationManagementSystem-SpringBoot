package lk.ijse.donationsystem.service;

import lk.ijse.donationsystem.dto.BloodBankDTO;
import java.util.List;
import java.util.UUID;

public interface BloodBankService {
    String addBloodBank(BloodBankDTO bloodBankDTO);
    List<BloodBankDTO> getAllBloodBanks();
    BloodBankDTO getBloodBankById(UUID id);
    String updateBloodBank(UUID id, BloodBankDTO bloodBankDTO);
    String deleteBloodBank(UUID id);
}
