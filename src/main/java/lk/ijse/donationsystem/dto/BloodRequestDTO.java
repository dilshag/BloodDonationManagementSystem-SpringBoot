package lk.ijse.donationsystem.dto;

import lk.ijse.donationsystem.BloodType;
import lk.ijse.donationsystem.RequestStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodRequestDTO {
    private BloodType bloodType;
    private int quantity;
    private String medicalCondition;
    private String recipientEmail;   // 👈 Only this from frontend
    private UUID bloodBankId;
    private String contactNumber;
    private UUID recipientId;
    private RequestStatus requestStatus;
}


