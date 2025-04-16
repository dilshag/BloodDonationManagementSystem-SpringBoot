package lk.ijse.donationsystem.entity;

import jakarta.persistence.*;
import lk.ijse.donationsystem.BloodType;
import lk.ijse.donationsystem.RequestStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "blood_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BloodRequest {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 5)
    private BloodType bloodType;

    @Column(nullable = false)
    private int quantity;

    @Column(length = 15)
    private String contactNumber;

    @Column(length = 12)
    private String nic;

    @Column(length = 255)
    private String address;

    @Column(length = 255)
    private String medicalCondition;

    @Column(length = 100)
    private String doctorName;

    @Column(length = 15)
    private String doctorContact;

    @Column(nullable = false)
    private LocalDateTime requestDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RequestStatus requestStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", nullable = false)
    private User recipient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blood_bank_id", nullable = false)
    private BloodBank bloodBank;

    @Version
    private int version;
}






/*
package lk.ijse.donationsystem.entity;

import jakarta.persistence.*;
import lk.ijse.donationsystem.BloodType;
import lk.ijse.donationsystem.RequestStatus;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "blood_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BloodRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    private Recipient recipient;

    @ManyToOne
    @JoinColumn(name = "blood_bank_id", nullable = false)
    private BloodBank bloodBank;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BloodType bloodType;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private LocalDate requestedDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status;
}
*/
