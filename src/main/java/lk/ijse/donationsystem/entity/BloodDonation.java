package lk.ijse.donationsystem.entity;

import jakarta.persistence.*;

import lk.ijse.donationsystem.BloodType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "blood_donations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BloodDonation {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "donor_id", nullable = false)
    private Donor donor;

    @ManyToOne
    @JoinColumn(name = "blood_bank_id", nullable = false)
    private BloodBank bloodBank;

    @Column(nullable = false)
    private int quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BloodType bloodType;

    @Column(nullable = false)
    private LocalDate donationDate;
}
/*
*
*
* 0e7e7987-b418-43b5-aa21-b37885445b0d       d3
*  d3a3ddee-24ed-4c5e-88ec-ef6c1bf1969cd4
*  "id": "a8bd084e-9e6e-4391-8785-3ea1e0a781d1",
        "name": "National Blood Center",
        "location": "Colombo, Sri Lanka"
* */