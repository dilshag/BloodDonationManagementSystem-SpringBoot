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
@Table(name = "blood_stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BloodStock {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BloodType bloodType;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private LocalDate expiryDate;

    private LocalDate donatedDate;

    @ManyToOne
    @JoinColumn(name = "inventory_id", nullable = false)
    private BloodInventory inventory;

    @OneToOne(mappedBy = "bloodStock", cascade = CascadeType.ALL)
    private BloodDonation donation;

}
