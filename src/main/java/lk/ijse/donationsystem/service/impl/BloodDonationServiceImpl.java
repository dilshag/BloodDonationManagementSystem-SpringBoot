package lk.ijse.donationsystem.service.impl;

import lk.ijse.donationsystem.BloodType;
import lk.ijse.donationsystem.dto.BloodDonationDTO;
import lk.ijse.donationsystem.dto.DonationRequestDTO;
import lk.ijse.donationsystem.entity.*;
import lk.ijse.donationsystem.repo.BloodBankRepository;
import lk.ijse.donationsystem.repo.BloodDonationRepository;
import lk.ijse.donationsystem.repo.BloodInventoryRepository;
import lk.ijse.donationsystem.repo.DonorRepository;
import lk.ijse.donationsystem.service.BloodDonationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BloodDonationServiceImpl implements BloodDonationService {

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private BloodBankRepository bloodBankRepository;

    @Autowired
    private BloodInventoryRepository inventoryRepository;

    @Autowired
    private BloodDonationRepository donationRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void createDonation(DonationRequestDTO dto) {

        Donor donor = donorRepository.findById(dto.getDonorId())
                .orElseThrow(() -> new RuntimeException("Donor not found"));

        BloodBank bank = bloodBankRepository.findById(dto.getBloodBankId())
                .orElseThrow(() -> new RuntimeException("Blood bank not found"));

        BloodInventory inventory = inventoryRepository.findByBloodBank(bank)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        BloodStock stock = new BloodStock();
        stock.setBloodType(dto.getBloodType());
        stock.setQuantity(dto.getQuantity());
        stock.setDonatedDate(dto.getDonatedDate());
        stock.setExpiryDate(dto.getDonatedDate().plusDays(42));
        stock.setInventory(inventory);

        BloodDonation donation = new BloodDonation();
        donation.setDonor(donor);
        donation.setBloodBank(bank);
        donation.setDonationDate(dto.getDonatedDate());
        donation.setQuantity(dto.getQuantity());
        donation.setBloodType(dto.getBloodType());
        donation.setBloodStock(stock);

        stock.setDonation(donation); // Link back

        donationRepository.save(donation);
    }

    @Override
    public List<BloodDonationDTO> getAllDonations() {
        return donationRepository.findAll().stream()
                .map(donation -> modelMapper.map(donation, BloodDonationDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<BloodDonationDTO> filterDonations(UUID donorId, UUID bloodBankId, BloodType bloodType, String sort) {
        List<BloodDonation> donations = donationRepository.findAll(); // basic

        // Filter logic
        if (donorId != null) {
            donations = donations.stream()
                    .filter(d -> d.getDonor().getId().equals(donorId))
                    .toList();
        }

        if (bloodBankId != null) {
            donations = donations.stream()
                    .filter(d -> d.getBloodBank().getId().equals(bloodBankId))
                    .toList();
        }

        if (bloodType != null) {
            donations = donations.stream()
                    .filter(d -> d.getBloodType().equals(bloodType))
                    .toList();
        }

        // Sort by donation date
        Comparator<BloodDonation> comparator = Comparator.comparing(BloodDonation::getDonationDate);
        if (sort.equalsIgnoreCase("desc")) {
            comparator = comparator.reversed();
        }

        donations = donations.stream()
                .sorted(comparator)
                .toList();

        return donations.stream()
                .map(d -> modelMapper.map(d, BloodDonationDTO.class))
                .toList();
    }

}




/*
package lk.ijse.donationsystem.service.impl;

import lk.ijse.donationsystem.dto.BloodDonationDTO;
import lk.ijse.donationsystem.entity.*;
import lk.ijse.donationsystem.exception.*;
import lk.ijse.donationsystem.repo.*;
import lk.ijse.donationsystem.service.BloodDonationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class BloodDonationServiceImpl implements BloodDonationService {

    @Autowired
    private BloodDonationRepository donationRepository;

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private BloodBankRepository bankRepository;

    @Autowired
    private BloodStockRepository stockRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BloodDonationDTO recordDonation(BloodDonationDTO dto) {
        validateDonationDTO(dto);

        Donor donor = donorRepository.findById(dto.getDonorId())
                .orElseThrow(() -> new DonorNotFoundException(dto.getDonorId()));

        BloodBank bank = bankRepository.findById(dto.getBloodBankId())
                .orElseThrow(() -> new BloodBankNotFoundException(dto.getBloodBankId()));

        BloodDonation donation = modelMapper.map(dto, BloodDonation.class);
        donation.setDonor(donor);
        donation.setBloodBank(bank);

        BloodStock stock = createBloodStock(dto, bank, donation);
        donation.setBloodStock(stock);

        BloodDonation savedDonation = donationRepository.save(donation);
        stockRepository.save(stock);

        return modelMapper.map(savedDonation, BloodDonationDTO.class);
    }

    private BloodStock createBloodStock(BloodDonationDTO dto, BloodBank bank, BloodDonation donation) {
        if (bank.getBloodInventory() == null) {
            throw new RuntimeException("Blood inventory not found for bank: " + bank.getId());
        }

        BloodStock stock = new BloodStock();
        stock.setBloodType(dto.getBloodType());
        stock.setQuantity(dto.getQuantity());
        stock.setDonatedDate(dto.getDonationDate());
        stock.setExpiryDate(calculateExpiryDate(dto.getDonationDate()));
        stock.setInventory(bank.getBloodInventory());
        stock.setDonation(donation);
        return stock;
    }

    private LocalDate calculateExpiryDate(LocalDate donationDate) {
        return donationDate.plusDays(35); // RBC lasts 35 days
    }

    private void validateDonationDTO(BloodDonationDTO dto) {
        if (dto == null) {
            throw new InvalidDonationException("Donation data cannot be null");
        }
        if (dto.getQuantity() <= 0) {
            throw new InvalidDonationException("Quantity must be positive");
        }
        if (dto.getDonationDate() == null || dto.getDonationDate().isAfter(LocalDate.now())) {
            throw new InvalidDonationException("Invalid donation date");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<BloodDonationDTO> getDonationsByDonor(UUID donorId) {
        return donationRepository.findByDonorId(donorId).stream()
                .map(donation -> modelMapper.map(donation, BloodDonationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BloodDonationDTO> getDonationsByBloodBank(UUID bloodBankId) {
        return donationRepository.findByBloodBankId(bloodBankId).stream()
                .map(donation -> modelMapper.map(donation, BloodDonationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDonation(UUID donationId) {
        BloodDonation donation = donationRepository.findById(donationId)
                .orElseThrow(() -> new DonationNotFoundException(donationId));

        if (donation.getBloodStock() != null) {
            stockRepository.delete(donation.getBloodStock());
        }

        donationRepository.delete(donation);
    }
}*/
