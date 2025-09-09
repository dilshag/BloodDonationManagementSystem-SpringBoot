package lk.ijse.donationsystem.service.impl;

import lk.ijse.donationsystem.dto.BloodStockDTO;
import lk.ijse.donationsystem.entity.BloodBank;
import lk.ijse.donationsystem.entity.BloodInventory;
import lk.ijse.donationsystem.entity.BloodStock;
import lk.ijse.donationsystem.repo.BloodBankRepository;
import lk.ijse.donationsystem.repo.BloodInventoryRepository;
import lk.ijse.donationsystem.repo.BloodStockRepository;
import lk.ijse.donationsystem.service.BloodStockService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BloodStockServiceImpl implements BloodStockService {

    @Autowired
    private BloodStockRepository bloodStockRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BloodBankRepository bloodBankRepository;

    @Autowired
    private BloodInventoryRepository bloodInventoryRepository;

    @Override
    public List<BloodStockDTO> getStockByBloodBankName(String name) {
        BloodBank bank = bloodBankRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Blood Bank not found"));

        BloodInventory inventory = bloodInventoryRepository.findByBloodBank(bank)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        List<BloodStock> stockList = bloodStockRepository.findByInventory(inventory);

        return stockList.stream()
                .map(stock -> modelMapper.map(stock, BloodStockDTO.class))
                .collect(Collectors.toList());
    }


}
