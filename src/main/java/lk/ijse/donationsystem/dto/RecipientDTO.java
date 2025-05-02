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

   // private UUID id;  //

 //   private String bloodBankId;
 private String bloodBankName;
    private BloodType bloodType;

    private int quantity;

    private LocalDate requestedDate;

    private RequestStatus status;

    private String contactNumber;

    private String address;

    private String nic;

    private LocalDate dateOfBirth;

    private String medicalCondition;

    private String doctorName;

    private String doctorContact;
}
