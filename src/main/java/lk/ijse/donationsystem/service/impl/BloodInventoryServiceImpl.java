package lk.ijse.donationsystem.service.impl;

import lk.ijse.donationsystem.dto.BloodInventoryDTO;
import lk.ijse.donationsystem.entity.BloodInventory;
import lk.ijse.donationsystem.entity.BloodBank;
import lk.ijse.donationsystem.repo.BloodInventoryRepository;
import lk.ijse.donationsystem.repo.BloodBankRepository;
import lk.ijse.donationsystem.repo.BloodStockRepository;
import lk.ijse.donationsystem.service.BloodInventoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class BloodInventoryServiceImpl implements BloodInventoryService {

    private final BloodInventoryRepository bloodInventoryRepository;
    private final BloodBankRepository bloodBankRepository;
    private final BloodStockRepository bloodStockRepository;

    public BloodInventoryServiceImpl(BloodInventoryRepository bloodInventoryRepository, BloodBankRepository bloodBankRepository, BloodStockRepository bloodStockRepository) {
        this.bloodInventoryRepository = bloodInventoryRepository;
        this.bloodBankRepository = bloodBankRepository;
        this.bloodStockRepository = bloodStockRepository;
    }
    @Override
    public String addBloodInventory(BloodInventoryDTO bloodInventoryDTO) {
        // Extract BloodBank ID
        UUID bloodBankId = bloodInventoryDTO.getBloodBankId();

        // Find BloodBank by ID
        BloodBank bloodBank = bloodBankRepository.findById(bloodBankId)
                .orElseThrow(() -> new RuntimeException("BloodBank not found"));

        // Check if Blood Inventory already exists
        if (bloodInventoryRepository.findByBloodBankId(bloodBankId).isPresent()) {
            return "Blood inventory already exists for this blood bank.";
        }

        // Create new Blood Inventory
        BloodInventory bloodInventory = new BloodInventory();
        bloodInventory.setBloodBank(bloodBank);
        bloodInventory.setBloodStockList(new ArrayList<>()); // Initialize empty stock list

        bloodInventoryRepository.save(bloodInventory);

        return "Blood inventory added successfully.";
    }

    @Override
    public String updateBloodInventory(UUID bloodInventoryId, String quantity) {
        return "";
    }

    @Override
    public BloodInventoryDTO getBloodInventory(UUID bloodBankId) {
        BloodInventory bloodInventory = bloodInventoryRepository.findByBloodBankId(bloodBankId)
                .orElseThrow(() -> new RuntimeException("Blood Inventory not found for this Blood Bank"));

        return new BloodInventoryDTO(
                bloodInventory.getId(),
                bloodInventory.getBloodBank().getId(),
                new ArrayList<>()
        );
    }


}

/*
@Service
public class BloodInventoryServiceImpl implements BloodInventoryService {

    private final BloodInventoryRepository bloodInventoryRepository;
    private final BloodBankRepository bloodBankRepository;

    public BloodInventoryServiceImpl(BloodInventoryRepository bloodInventoryRepository, BloodBankRepository bloodBankRepository) {
        this.bloodInventoryRepository = bloodInventoryRepository;
        this.bloodBankRepository = bloodBankRepository;
    }

    @Override
    public String addBloodInventory(BloodInventoryDTO bloodInventoryDTO) {
        // Find BloodBank by ID
        BloodBank bloodBank = bloodBankRepository.findById(bloodInventoryDTO.getBloodBankId())
                .orElseThrow(() -> new RuntimeException("BloodBank not found"));

        // Create a new BloodInventory entity
        BloodInventory bloodInventory = new BloodInventory();
        bloodInventory.setBloodType(bloodInventoryDTO.getBloodType());
        bloodInventory.setQuantity(bloodInventoryDTO.getQuantity());
        bloodInventory.setExpiryDate(bloodInventoryDTO.getExpiryDate());
        bloodInventory.setBloodBank(bloodBank);

        // Save the new BloodInventory
        bloodInventoryRepository.save(bloodInventory);

        return "Blood inventory added successfully.";
    }

    @Override
    public BloodInventoryDTO getBloodInventory(UUID bloodBankId) {
        BloodInventory bloodInventory = bloodInventoryRepository.findByBloodBankId(bloodBankId);
        if (bloodInventory == null) {
            return null; // or throw an exception if needed
        }

        // Convert the entity to DTO
        return new BloodInventoryDTO(
                bloodInventory.getId(),
                bloodInventory.getBloodType(),
                bloodInventory.getQuantity(),
                bloodInventory.getExpiryDate(),
                bloodInventory.getBloodBank().getId()
        );
    }

    @Override
    public String updateBloodInventory(UUID bloodInventoryId, String quantity) {
        BloodInventory bloodInventory = bloodInventoryRepository.findById(bloodInventoryId)
                .orElseThrow(() -> new RuntimeException("Blood Inventory not found"));

        // Update the quantity in the inventory
        bloodInventory.setQuantity(String.valueOf(Integer.parseInt(bloodInventory.getQuantity()) + quantity));

        // Save the updated inventory
        bloodInventoryRepository.save(bloodInventory);

        return "Blood inventory updated successfully.";
    }*/

