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
