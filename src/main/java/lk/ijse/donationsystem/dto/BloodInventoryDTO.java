package lk.ijse.donationsystem.dto;

import lk.ijse.donationsystem.BloodType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodInventoryDTO {
    private UUID id;
    private UUID bloodBankId; // Blood Bank ID
    private List<BloodStockDTO> bloodStockList;
}
