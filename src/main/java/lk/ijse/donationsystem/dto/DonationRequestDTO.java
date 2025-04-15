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
public class DonationRequestDTO {
    private UUID donorId;
    private UUID bloodBankId;
    private int quantity;
    private LocalDate donatedDate;
    private BloodType bloodType;
}
