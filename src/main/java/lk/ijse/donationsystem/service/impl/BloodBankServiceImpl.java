package lk.ijse.donationsystem.service.impl;

import lk.ijse.donationsystem.dto.BloodBankDTO;
import lk.ijse.donationsystem.entity.BloodBank;
import lk.ijse.donationsystem.repo.BloodBankRepository;
import lk.ijse.donationsystem.service.BloodBankService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BloodBankServiceImpl implements BloodBankService {

    private final BloodBankRepository bloodBankRepository;

    public BloodBankServiceImpl(BloodBankRepository bloodBankRepository) {
        this.bloodBankRepository = bloodBankRepository;
    }

    @Override
    public String addBloodBank(BloodBankDTO bloodBankDTO) {
        BloodBank bloodBank = new BloodBank();
        bloodBank.setName(bloodBankDTO.getName());
        bloodBank.setLocation(bloodBankDTO.getLocation());

        bloodBankRepository.save(bloodBank);
        return "Blood bank added successfully.";
    }

    @Override
    public List<BloodBankDTO> getAllBloodBanks() {
        return bloodBankRepository.findAll().stream().map(bloodBank ->
                new BloodBankDTO(bloodBank.getId(), bloodBank.getName(), bloodBank.getLocation())
        ).collect(Collectors.toList());
    }

    @Override
    public BloodBankDTO getBloodBankById(UUID id) {
        Optional<BloodBank> bloodBank = bloodBankRepository.findById(id);
        return bloodBank.map(bank -> new BloodBankDTO(bank.getId(), bank.getName(), bank.getLocation()))
                .orElseThrow(() -> new RuntimeException("Blood bank not found"));
    }

    @Override
    public String updateBloodBank(UUID id, BloodBankDTO bloodBankDTO) {
        BloodBank bloodBank = bloodBankRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blood bank not found"));

        bloodBank.setName(bloodBankDTO.getName());
        bloodBank.setLocation(bloodBankDTO.getLocation());

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
}
