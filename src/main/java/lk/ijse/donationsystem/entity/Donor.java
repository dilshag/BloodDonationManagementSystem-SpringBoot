package lk.ijse.donationsystem.entity;

import jakarta.persistence.*;
import lk.ijse.donationsystem.BloodType;
import lk.ijse.donationsystem.DonorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "donors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Donor {

    @Id
    private UUID id; // Uses the same UUID as the User entity

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId // Ensures that this entity shares the same primary key as User
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BloodType bloodType;



    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DonorStatus status = DonorStatus.ACTIVE; // Default status (ACTIVE or INACTIVE)

    // ✅ New field for profile picture
    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    // ✅ Getter for Donor Name (from User entity)
    public String getName() {
        return user != null ? user.getName() : null;
    }




    /*@Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DonorStatus status; // Change this to DonorStatus
*/
    //private DonorStatus status = DonorStatus.ACTIVE;// Default status (ACTIVE or INACTIVE)


    // Future collection of donations (if needed)
    // @OneToMany(mappedBy = "donor")
    // private List<BloodDonation> bloodDonations;
}
