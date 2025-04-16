package lk.ijse.donationsystem.dto;

import lk.ijse.donationsystem.BloodType;
import lk.ijse.donationsystem.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BloodRequestDTO {
    private BloodType bloodType;
    private int quantity;
    private String bloodBankName;
    private String contactNumber;
    private String nic;
    private String address;
    private String medicalCondition;
    private String doctorName;
    private String doctorContact;


/*

    private UUID id;  // Unique identifier for the blood request

    private UUID recipientId;  // The recipient who is requesting blood

    private UUID bloodBankId;  // The blood bank that will provide the blood

    private BloodType bloodType;  // The type of blood being requested

    private int quantity;  // The quantity of blood being requested

    private LocalDate requestDate;  // The date the request was made

    private RequestStatus status;  // The status of the request (PENDING, APPROVED, REJECTED)
*/
}
