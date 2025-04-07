package lk.ijse.donationsystem.service;

import lk.ijse.donationsystem.dto.BloodInventoryDTO;

import java.util.UUID;

public interface BloodInventoryService {
  //  String addBloodInventory(UUID bloodBankId);
  String addBloodInventory(BloodInventoryDTO bloodInventoryDTO);
    String updateBloodInventory(UUID bloodInventoryId, String quantity);
  BloodInventoryDTO getBloodInventory(UUID bloodBankId);
  //String addBloodInventory(BloodInventoryDTO bloodInventoryDTO);
    //BloodInventoryDTO getBloodInventory(UUID bloodBankId);
    //String updateBloodInventory(UUID bloodInventoryId, String quantity);
}
