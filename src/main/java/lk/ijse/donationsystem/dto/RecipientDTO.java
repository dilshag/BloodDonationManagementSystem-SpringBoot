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
public class RecipientDTO {

   // private UUID id;  // UUID as String, it will be mapped to the User entity

 //   private String bloodBankId;  // BloodBank UUID as String
 private String bloodBankName;
    private BloodType bloodType;  // The blood type of the recipient

    private int quantity;  // The quantity of blood the recipient needs

    private LocalDate requestedDate;  // The date when the blood was requested

    private RequestStatus status;  // The status of the request (PENDING, APPROVED, REJECTED)

    private String contactNumber;  // The recipient's contact number

    private String address;  // The recipient's address

    private String nic;  // National Identity Card number for validation and record keeping

    private LocalDate dateOfBirth;  // The recipient's date of birth (to ensure age eligibility)

    private String medicalCondition;  // The recipient's medical condition

    private String doctorName;  // Doctor's name for verification purposes

    private String doctorContact;  // Doctor's contact number for verification purposes
}
