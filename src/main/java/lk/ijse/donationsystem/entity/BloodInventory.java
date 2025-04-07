package lk.ijse.donationsystem.entity;

import jakarta.persistence.*;
import lk.ijse.donationsystem.BloodType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;



@Entity
@Table(name = "blood_inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BloodInventory {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "blood_bank_id", unique = true, nullable = false)
    private BloodBank bloodBank;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BloodStock> bloodStockList;
}


/*
@Entity
@Table(name = "blood_inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BloodInventory {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BloodType bloodType;

    @Column(nullable = false)
    private String quantity;

    @Column(nullable = false)
    private LocalDate expiryDate; // Changed from String to LocalDate

    @OneToOne
    @JoinColumn(name = "blood_bank_id", unique = true)
    private BloodBank bloodBank;
   *//* @ManyToOne
    @JoinColumn(name = "blood_bank_id")
    private BloodBank bloodBank;*//*
}*/
