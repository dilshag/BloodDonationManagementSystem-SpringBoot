package lk.ijse.donationsystem.service;

import lk.ijse.donationsystem.BloodBankStatus;
import lk.ijse.donationsystem.dto.BloodBankDTO;
import java.util.List;
import java.util.UUID;

public interface BloodBankService {
    String addBloodBank(BloodBankDTO bloodBankDTO);

    List<BloodBankDTO> getAllBloodBanks();

    BloodBankDTO getBloodBankById(UUID id);

    String updateBloodBank(UUID id, BloodBankDTO bloodBankDTO);

    String deleteBloodBank(UUID id);
    //public String toggleStatus(UUID id);
    public String setStatus(UUID id, BloodBankStatus newStatus);

    public List<BloodBankDTO> getAllActiveBloodBanks();

    List<String> getAllBloodBankNames();
    BloodBankDTO getBloodBankByName(String name);
}