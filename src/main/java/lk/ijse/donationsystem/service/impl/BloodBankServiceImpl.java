package lk.ijse.donationsystem.service.impl;

import lk.ijse.donationsystem.BloodBankStatus;
import lk.ijse.donationsystem.dto.BloodBankDTO;
import lk.ijse.donationsystem.entity.BloodBank;
import lk.ijse.donationsystem.repo.BloodBankRepository;
import lk.ijse.donationsystem.service.BloodBankService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BloodBankServiceImpl implements BloodBankService {
@Autowired
    private final BloodBankRepository bloodBankRepository;

    @Autowired
    private ModelMapper modelMapper;

    public BloodBankServiceImpl(BloodBankRepository bloodBankRepository) {
        this.bloodBankRepository = bloodBankRepository;
    }

    @Override
    public String addBloodBank(BloodBankDTO bloodBankDTO) {
        BloodBank bloodBank = new BloodBank();
        bloodBank.setName(bloodBankDTO.getName());
        bloodBank.setLocation(bloodBankDTO.getLocation());
        bloodBank.setPhoneNumber(bloodBankDTO.getPhoneNumber());
        bloodBank.setEmail(bloodBankDTO.getEmail());
       // bloodBank.setStatus(bloodBankDTO.getStatus());

        bloodBankRepository.save(bloodBank);
        return "Blood bank added successfully.";
    }

    @Override
    public List<BloodBankDTO> getAllBloodBanks() {
        return bloodBankRepository.findAll().stream().map(bloodBank ->
                new BloodBankDTO(bloodBank.getId(), bloodBank.getName(), bloodBank.getLocation(),bloodBank.getPhoneNumber(),bloodBank.getEmail(),bloodBank.getStatus())
        ).collect(Collectors.toList());
    }

    @Override
    public BloodBankDTO getBloodBankById(UUID id) {
        Optional<BloodBank> bloodBank = bloodBankRepository.findById(id);
        return bloodBank.map(bank -> new BloodBankDTO(bank.getId(), bank.getName(), bank.getLocation(),bank.getPhoneNumber(),bank.getEmail(),bank.getStatus()))
                .orElseThrow(() -> new RuntimeException("Blood bank not found"));
    }

    @Override
    public String updateBloodBank(UUID id, BloodBankDTO bloodBankDTO) {
        BloodBank bloodBank = bloodBankRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blood bank not found"));

        bloodBank.setName(bloodBankDTO.getName());
        bloodBank.setLocation(bloodBankDTO.getLocation());
        bloodBank.setPhoneNumber(bloodBankDTO.getPhoneNumber());
        bloodBank.setEmail(bloodBankDTO.getEmail());


        bloodBankRepository.save(bloodBank);
        return "Blood bank updated successfully.";
    }

    @Override
    public String deleteBloodBank(UUID id) {
        BloodBank bloodBank = bloodBankRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blood bank not found"));

        bloodBankRepository.delete(bloodBank);
        return "Blood bank deleted successfully.";
    }

    @Override
    public String setStatus(UUID id, BloodBankStatus status) {
        Optional<BloodBank> bloodBankOptional = bloodBankRepository.findById(id);

        if (bloodBankOptional.isPresent()) {
            BloodBank bloodBank = bloodBankOptional.get();
            // Set the blood bank status to the new status
            bloodBank.setStatus(status);
            bloodBankRepository.save(bloodBank); // Save the updated blood bank
            return "Blood bank status updated to " + status;
        } else {
            throw new RuntimeException("Blood bank not found!");
        }
    }

    @Override
    public List<BloodBankDTO> getAllActiveBloodBanks() {
        List<BloodBank> activeBanks = bloodBankRepository.findAllByStatus(BloodBankStatus.ENABLED);
        return activeBanks.stream()
                .map(bank -> new BloodBankDTO(bank.getId(), bank.getName(), bank.getLocation(),bank.getPhoneNumber(),bank.getEmail(), bank.getStatus()))
                .collect(Collectors.toList());
    }

}
