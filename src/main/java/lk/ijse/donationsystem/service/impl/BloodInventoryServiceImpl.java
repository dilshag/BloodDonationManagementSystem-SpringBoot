/*
package lk.ijse.donationsystem.service.impl;

import lk.ijse.donationsystem.dto.BloodInventoryDTO;

import lk.ijse.donationsystem.entity.BloodBank;
import lk.ijse.donationsystem.entity.BloodInventory;
import lk.ijse.donationsystem.entity.BloodStock;

import lk.ijse.donationsystem.repo.BloodBankRepository;

import lk.ijse.donationsystem.repo.BloodInventoryRepository;

import lk.ijse.donationsystem.repo.BloodStockRepository;
import lk.ijse.donationsystem.service.BloodInventoryService;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BloodInventoryServiceImpl implements BloodInventoryService {

    @Autowired
    private BloodInventoryRepository inventoryRepository;

    @Autowired
    private BloodBankRepository bloodBankRepository;

    @Autowired
    private BloodStockRepository bloodStockRepository;

    @Autowired
    private ModelMapper modelMapper;

    */
/*@Autowired
    private BloodStockMapper stockMapper;
*//*
 @Override
    public BloodInventoryDTO createInventory(UUID bloodBankId) {
        BloodBank bloodBank = bloodBankRepository.findById(bloodBankId)
                .orElseThrow(() -> new RuntimeException("Blood Bank not found"));

        BloodInventory inventory = new BloodInventory();
        inventory.setBloodBank(bloodBank);
        //inventory.setBloodStockList(List.of()); // initially empty
        inventory.setBloodStockList(new ArrayList<>()); // Use mutable ArrayList
        BloodInventory saved = inventoryRepository.save(inventory);
        return modelMapper.map(saved, BloodInventoryDTO.class);
    }

    @Override
    public BloodInventoryDTO getInventoryByBloodBankId(UUID bloodBankId) {
        BloodInventory inventory = inventoryRepository.findByBloodBankId(bloodBankId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        return modelMapper.map(inventory, BloodInventoryDTO.class);
    }

    @Override
    @Transactional
    public BloodInventoryDTO addBloodStock(UUID inventoryId, BloodInventoryDTO dto) {
        BloodInventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        List<BloodStock> newStock = dto.getBloodStockList().stream()
                .map(stockDTO -> {
                    BloodStock stock = modelMapper.map(stockDTO, BloodStock.class);
                    stock.setInventory(inventory);
                    return stock;
                })
                .collect(Collectors.toList());

        inventory.getBloodStockList().addAll(newStock);
        inventoryRepository.save(inventory); // cascade saves

        return modelMapper.map(inventory, BloodInventoryDTO.class);
    }

    @Override
    public void removeBloodStock(UUID inventoryId, UUID bloodStockId) {
        BloodInventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        BloodStock stockToRemove = bloodStockRepository.findById(bloodStockId)
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        inventory.getBloodStockList().removeIf(stock -> stock.getId().equals(bloodStockId));
        bloodStockRepository.delete(stockToRemove);
        inventoryRepository.save(inventory);
    }

    @Override
    public List<BloodInventoryDTO> getAllInventories() {
        return inventoryRepository.findAll().stream()
                .map(inventory -> modelMapper.map(inventory, BloodInventoryDTO.class))
                .collect(Collectors.toList());
    }
}
*/
