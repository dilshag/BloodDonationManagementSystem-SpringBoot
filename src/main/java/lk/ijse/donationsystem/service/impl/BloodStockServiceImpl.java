/*
package lk.ijse.donationsystem.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.donationsystem.dto.BloodStockDTO;
import lk.ijse.donationsystem.entity.BloodInventory;
import lk.ijse.donationsystem.entity.BloodStock;
import lk.ijse.donationsystem.repo.BloodInventoryRepository;
import lk.ijse.donationsystem.repo.BloodStockRepository;
import lk.ijse.donationsystem.service.BloodStockService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BloodStockServiceImpl implements BloodStockService {

    private final BloodStockRepository bloodStockRepository;
    private final BloodInventoryRepository bloodInventoryRepository;

    public BloodStockServiceImpl(BloodStockRepository bloodStockRepository, BloodInventoryRepository bloodInventoryRepository) {
        this.bloodStockRepository = bloodStockRepository;
        this.bloodInventoryRepository = bloodInventoryRepository;
    }

    @Transactional
    @Override
    public String addBloodStock(BloodStockDTO bloodStockDTO) {
        // Find the blood inventory by ID
        BloodInventory bloodInventory = bloodInventoryRepository.findById(bloodStockDTO.getInventoryId())
                .orElseThrow(() -> new RuntimeException("Blood Inventory not found"));

        // Create new BloodStock entity
        BloodStock bloodStock = new BloodStock();
        bloodStock.setInventory(bloodInventory);
        bloodStock.setBloodType(bloodStockDTO.getBloodType());
        bloodStock.setQuantity(bloodStockDTO.getQuantity());
        bloodStock.setExpiryDate(bloodStockDTO.getExpiryDate());

        // Save the blood stock entry
        bloodStockRepository.save(bloodStock);

        return "Blood stock added successfully.";
    }

    @Transactional
    @Override
    public String updateBloodStock(UUID stockId, int newQuantity) {
        BloodStock bloodStock = bloodStockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("Blood Stock not found"));

        // Update quantity
        bloodStock.setQuantity(newQuantity);

        // Save updated blood stock
        bloodStockRepository.save(bloodStock);

        return "Blood stock updated successfully.";
    }

    @Transactional
    @Override
    public String removeBloodStock(UUID stockId) {
        BloodStock bloodStock = bloodStockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("Blood Stock not found"));

        bloodStockRepository.delete(bloodStock);

        return "Blood stock removed successfully.";
    }

    @Override
    public BloodStockDTO getBloodStockById(UUID stockId) {
        Optional<BloodStock> bloodStock = bloodStockRepository.findById(stockId);
        return bloodStock.map(stock -> new BloodStockDTO(
                stock.getId(),
                stock.getInventory().getId(),
                stock.getBloodType(),
                stock.getQuantity(),
                stock.getExpiryDate()
        )).orElseThrow(() -> new RuntimeException("Blood Stock not found"));
    }

    @Override
    public List<BloodStockDTO> getAllBloodStock() {
        return bloodStockRepository.findAll().stream().map(stock ->
                new BloodStockDTO(
                        stock.getId(),
                        stock.getInventory().getId(),
                        stock.getBloodType(),
                        stock.getQuantity(),
                        stock.getExpiryDate()
                )
        ).collect(Collectors.toList());
    }
}
*/
