package lk.ijse.donationsystem.dto;

import lk.ijse.donationsystem.BloodType;
import lk.ijse.donationsystem.DonorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DonorDTO {
    private UUID id;

    private String name; // From associated User entity
    private String email; // From associated User entity
    private BloodType bloodType;
    private String address;
    private String phoneNumber;
    private DonorStatus status;

    // ✅ New field for profile picture
    private String profilePictureUrl;

}
