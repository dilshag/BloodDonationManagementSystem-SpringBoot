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
public class BloodDonationDTO {
    private UUID id;
    private UUID donorId; // Donor ID
    private UUID bloodBankId; // Blood Bank ID
    private int quantity;
    private LocalDate donationDate;
    private BloodType bloodType;

}