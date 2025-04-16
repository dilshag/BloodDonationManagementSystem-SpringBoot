/*
package lk.ijse.donationsystem.entity;

import jakarta.persistence.*;
import lk.ijse.donationsystem.BloodType;
import lk.ijse.donationsystem.RequestStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "recipients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipient {

    @Id
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;  // This links the Recipient entity to the User entity (assuming you have a User entity for authentication)

    @ManyToOne
    @JoinColumn(name = "blood_bank_id", nullable = false)
    private BloodBank bloodBank;  // The blood bank where the recipient is requesting blood from

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BloodType bloodType;  // The blood type of the recipient

    @Column(nullable = false)
    private int quantity;  // The quantity of blood the recipient needs

    @Column(nullable = false)
    private LocalDate requestedDate;  // The date when the blood was requested

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status;  // The status of the request (PENDING, APPROVED, REJECTED)

    @Column(nullable = false)
    private String contactNumber;  // The recipient's contact number

    @Column(nullable = false)
    private String address;  // The recipient's address (for delivery if necessary)

    @Column(nullable = false)
    private String nic;  // National Identity Card number for validation and record keeping

    @Column(nullable = false)
    private LocalDate dateOfBirth;  // The recipient's date of birth (to ensure age eligibility for blood donation)

    @Column(nullable = false)
    private String medicalCondition;  // The recipient's medical condition (to assess compatibility and urgency of the request)

    @Column(nullable = true)
    private String doctorName;  // Doctor's name for verification purposes

    @Column(nullable = true)
    private String doctorContact;  // Doctor's contact number for verification purposes
}
*/
