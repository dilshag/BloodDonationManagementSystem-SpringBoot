package lk.ijse.donationsystem.dto;

import lk.ijse.donationsystem.BloodType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodStockDTO {
    private UUID id;
    private BloodType bloodType;
    private int quantity;
    private LocalDate donatedDate;
    private LocalDate expiryDate;

    private UUID inventoryId;
    private UUID donorId;
    private String donorName;
}







/*

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodStockDTO {
    private UUID id;
    private BloodType bloodType;
    private int quantity;
    private LocalDate expiryDate;
    private UUID inventoryId;  // Add this field

}
*/
