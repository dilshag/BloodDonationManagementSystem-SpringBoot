package lk.ijse.donationsystem.dto;

import lk.ijse.donationsystem.BloodType;
import lk.ijse.donationsystem.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodRequestDTO {
    private UUID id;
    private UUID recipientId; // ID of the recipient
    private BloodType bloodType;
    private int quantity;
    private RequestStatus status;
    private LocalDateTime requestDate;
    private UUID bloodBankId; // Blood Bank ID where blood is requested from

}
