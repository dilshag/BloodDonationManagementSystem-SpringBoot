package lk.ijse.donationsystem.service;

import jakarta.transaction.Transactional;
import lk.ijse.donationsystem.dto.BloodStockDTO;

import java.util.List;
import java.util.UUID;

public interface BloodStockService {
    List<BloodStockDTO> getStockByBloodBankName(String name);
  /*  @Transactional
    String addBloodStock(BloodStockDTO bloodStockDTO);

    @Transactional
    String updateBloodStock(UUID stockId, int newQuantity);

    @Transactional
    String removeBloodStock(UUID stockId);

    BloodStockDTO getBloodStockById(UUID stockId);

    List<BloodStockDTO> getAllBloodStock();
*/}
