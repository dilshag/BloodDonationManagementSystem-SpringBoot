package lk.ijse.donationsystem.dto;

import lk.ijse.donationsystem.BloodType;
import lk.ijse.donationsystem.DonorStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class DonorProfileDTO {
    private UUID id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private BloodType bloodType;
    private DonorStatus status;
    private String profilePictureUrl;
}
