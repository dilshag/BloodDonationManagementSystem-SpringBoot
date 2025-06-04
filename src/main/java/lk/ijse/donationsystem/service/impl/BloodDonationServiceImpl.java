package lk.ijse.donationsystem.service.impl;

import lk.ijse.donationsystem.BloodType;
import lk.ijse.donationsystem.dto.BloodDonationDTO;
import lk.ijse.donationsystem.dto.BloodStockDTO;
import lk.ijse.donationsystem.dto.DonationRequestDTO;
import lk.ijse.donationsystem.entity.*;
import lk.ijse.donationsystem.repo.*;
import lk.ijse.donationsystem.service.BloodDonationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    private  BloodStockRepository stockRepo;


    @Autowired
    private ModelMapper modelMapper;


    @Override
    @Transactional

    public void createDonation(DonationRequestDTO dto) {

        Donor donor = donorRepository.findById(dto.getDonorId())
                .orElseThrow(() -> new RuntimeException("Donor not found"));

        BloodBank bank = bloodBankRepository.findById(dto.getBloodBankId())
                .orElseThrow(() -> new RuntimeException("Blood bank not found"));

        BloodInventory inventory = inventoryRepository.findByBloodBank(bank)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        //  Check if a stock already exists for this blood type in this inventory
        BloodStock stock = stockRepo.findByBloodTypeAndInventory(dto.getBloodType(), inventory)
                .orElse(null);

        if (stock != null) {
            //  Update existing stock
            stock.setQuantity(stock.getQuantity() + dto.getQuantity());
            stock.setDonatedDate(dto.getDonatedDate()); // optional: set latest donation date
            stock.setExpiryDate(dto.getDonatedDate().plusDays(42)); // refresh expiry
        } else {
            //  Create new stock if not exists
            stock = new BloodStock();
            stock.setBloodType(dto.getBloodType());
            stock.setQuantity(dto.getQuantity());
            stock.setDonatedDate(dto.getDonatedDate());

            stock.setExpiryDate(dto.getDonatedDate().plusDays(42));
            stock.setInventory(inventory);

            // Add to inventory's stock list
            inventory.getBloodStockList().add(stock);
        }

        //  Create new donation record
        BloodDonation donation = new BloodDonation();
        donation.setDonor(donor);
        donation.setBloodBank(bank);
        donation.setDonationDate(dto.getDonatedDate());
        donation.setQuantity(dto.getQuantity());
        donation.setBloodType(dto.getBloodType());
        donation.setBloodStock(stock);

        //  Link back from stock to donation (optional, for traceability)
        stock.setDonation(donation);

        //  Save both
        donationRepository.save(donation);
        stockRepo.save(stock); // always save updated stock
        inventoryRepository.save(inventory);

        System.out.println("Donation recorded: " + dto.getQuantity() + "ml of " + dto.getBloodType());
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

    // ✅ Get all stocks with donor info
    public List<BloodStockDTO> getAllStockWithDonorInfo() {
        return stockRepo.findAll().stream().map(stock -> {
            BloodStockDTO dto = modelMapper.map(stock, BloodStockDTO.class);

            BloodDonation donation = stock.getDonation();
            if (donation != null && donation.getDonor() != null) {
                dto.setDonorId(donation.getDonor().getId());

                // ✅ Fix: Use getUser().getName() instead
                dto.setDonorName(donation.getDonor().getUser().getName());
            }

            return dto;
        }).toList();
    }

    // ✅ Get expired stock
    public List<BloodStockDTO> getExpiredStock() {
        LocalDate today = LocalDate.now();
        return stockRepo.findAll().stream()
                .filter(stock -> stock.getExpiryDate().isBefore(today))
                .map(stock -> {
                    BloodStockDTO dto = modelMapper.map(stock, BloodStockDTO.class);

                    BloodDonation donation = stock.getDonation();
                    if (donation != null && donation.getDonor() != null) {
                        dto.setDonorId(donation.getDonor().getId());
                        dto.setDonorName(donation.getDonor().getUser().getName()); // ✅ Fix here too
                    }

                    return dto;
                }).toList();
    }


}













