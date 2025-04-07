package lk.ijse.donationsystem.service.impl;


import lk.ijse.donationsystem.BloodType;
import lk.ijse.donationsystem.dto.BloodDonationDTO;
import lk.ijse.donationsystem.entity.*;
import lk.ijse.donationsystem.repo.*;
import lk.ijse.donationsystem.service.BloodDonationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;




@Service
public class BloodDonationServiceImpl implements BloodDonationService {

    private final DonorRepository donorRepository;
    private final BloodBankRepository bloodBankRepository;
    private final BloodInventoryRepository bloodInventoryRepository;
    private final BloodStockRepository bloodStockRepository;
    private final BloodDonationRepository bloodDonationRepository;

    public BloodDonationServiceImpl(DonorRepository donorRepository, BloodBankRepository bloodBankRepository,
                                    BloodInventoryRepository bloodInventoryRepository, BloodStockRepository bloodStockRepository,
                                    BloodDonationRepository bloodDonationRepository) {
        this.donorRepository = donorRepository;
        this.bloodBankRepository = bloodBankRepository;
        this.bloodInventoryRepository = bloodInventoryRepository;
        this.bloodStockRepository = bloodStockRepository;
        this.bloodDonationRepository = bloodDonationRepository;
    }

    @Transactional
    @Override
    public String donateBlood(BloodDonationDTO bloodDonationDTO) throws Exception {
        // Find the donor
        Donor donor = donorRepository.findById(bloodDonationDTO.getDonorId())
                .orElseThrow(() -> new Exception("Donor not found."));

        // Find the blood bank
        BloodBank bloodBank = bloodBankRepository.findById(bloodDonationDTO.getBloodBankId())
                .orElseThrow(() -> new Exception("Blood Bank not found."));

        // Check if blood inventory exists
        BloodInventory bloodInventory = bloodInventoryRepository.findByBloodBankId(bloodBank.getId())
                .orElseThrow(() -> new Exception("Blood Inventory not found for this blood bank."));

        // Find existing blood stock for this blood type
        BloodStock bloodStock = bloodStockRepository.findByInventoryIdAndBloodType(bloodInventory.getId(), bloodDonationDTO.getBloodType())
                .orElse(null);

        // If no stock exists for this blood type, create a new record
        if (bloodStock == null) {
            bloodStock = new BloodStock();
            bloodStock.setInventory(bloodInventory);
            bloodStock.setBloodType(bloodDonationDTO.getBloodType());
            bloodStock.setQuantity(0);
            bloodStock.setExpiryDate(LocalDate.now().plusMonths(3)); // Set expiry date
        }

        // Update quantity
        bloodStock.setQuantity(bloodStock.getQuantity() + bloodDonationDTO.getQuantity());

        // Save updated stock
        bloodStockRepository.save(bloodStock);

        // Create a new blood donation record
        BloodDonation bloodDonation = new BloodDonation();
        bloodDonation.setDonor(donor);
        bloodDonation.setBloodBank(bloodBank);
        bloodDonation.setBloodType(bloodDonationDTO.getBloodType());
        bloodDonation.setQuantity(bloodDonationDTO.getQuantity());
        bloodDonation.setDonationDate(LocalDate.now());

        // Save blood donation
        bloodDonationRepository.save(bloodDonation);

        return "Blood donation recorded successfully.";
    }
}

/*

@Service
public class BloodDonationServiceImpl implements BloodDonationService {

    private final DonorRepository donorRepository;
    private final BloodBankRepository bloodBankRepository;
    private final BloodInventoryRepository bloodInventoryRepository;
    private final BloodDonationRepository bloodDonationRepository;

    public BloodDonationServiceImpl(DonorRepository donorRepository, BloodBankRepository bloodBankRepository,
                                    BloodInventoryRepository bloodInventoryRepository, BloodDonationRepository bloodDonationRepository) {
        this.donorRepository = donorRepository;
        this.bloodBankRepository = bloodBankRepository;
        this.bloodInventoryRepository = bloodInventoryRepository;
        this.bloodDonationRepository = bloodDonationRepository;
    }

    @Transactional
    @Override
    public String donateBlood(BloodDonationDTO bloodDonationDTO) throws Exception {
        // Find the donor by ID
        Donor donor = donorRepository.findById(bloodDonationDTO.getDonorId()).orElseThrow(() -> new Exception("Donor not found."));

        // Find the blood bank by ID
        BloodBank bloodBank = bloodBankRepository.findById(bloodDonationDTO.getBloodBankId())
                .orElseThrow(() -> new Exception("Blood Bank not found."));
*/
/*
        // ✅ Check if bloodType is provided
        if (bloodDonationDTO.getBloodType() == null || bloodDonationDTO.getBloodType().isEmpty()) {
            throw new Exception("Blood type cannot be null or empty.");
        }*//*


        // Convert String bloodType to Enum
        BloodType bloodType = bloodDonationDTO.getBloodType();

        // Create a new blood donation record
        BloodDonation bloodDonation = new BloodDonation();
        bloodDonation.setDonor(donor);
        bloodDonation.setBloodBank(bloodBank);
        bloodDonation.setBloodType(bloodDonationDTO.getBloodType()); //  Set blood type
        bloodDonation.setQuantity(bloodDonationDTO.getQuantity());
        bloodDonation.setDonationDate(bloodDonationDTO.getDonationDate());

        // Save the blood donation record
        bloodDonationRepository.save(bloodDonation);



       */
/* // Update the blood bank inventory
        BloodInventory bloodInventory = bloodInventoryRepository.findByBloodBankIdAndBloodType(
                bloodDonationDTO.getBloodBankId(), bloodDonationDTO.getQuantity()
        );*//*


       */
/* // Fetch the correct blood inventory
        BloodInventory bloodInventory = bloodInventoryRepository.findByBloodBankIdAndBloodType(
                bloodDonationDTO.getBloodBankId(), bloodType
        );*//*




        // ✅ Check if Blood Inventory exists
        BloodInventory bloodInventory = bloodInventoryRepository.findByBloodBankIdAndBloodType(
                bloodDonationDTO.getBloodBankId(), bloodType
        );

        if (bloodInventory == null) {
            System.out.println("No inventory found, creating new one...");
            bloodInventory = new BloodInventory();
            bloodInventory.setBloodBank(bloodBank);
            bloodInventory.setBloodType(bloodType);
            bloodInventory.setQuantity("0"); // Initially empty
            bloodInventory.setExpiryDate(LocalDate.now().plusMonths(3)); // ✅ Set expiry date
            bloodInventoryRepository.save(bloodInventory);  }

        // Update inventory quantity
        bloodInventory.setQuantity(String.valueOf(Integer.parseInt(bloodInventory.getQuantity()) + Integer.parseInt(bloodDonationDTO.getQuantity())));

        // Save the updated inventory
        bloodInventoryRepository.save(bloodInventory);

        return "Blood donation recorded successfully.";
    }
}
*/
