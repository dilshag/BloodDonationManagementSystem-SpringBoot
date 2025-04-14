package lk.ijse.donationsystem.dto;

import lk.ijse.donationsystem.BloodBankStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodBankDTO {
    private UUID id;
    private String name;
    private String location;
    private String phoneNumber;
    private String email;
    private BloodBankStatus status;


}