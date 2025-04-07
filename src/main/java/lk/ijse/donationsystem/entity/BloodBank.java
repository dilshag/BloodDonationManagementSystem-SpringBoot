package lk.ijse.donationsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "blood_banks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BloodBank {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false, length = 255)
    private String location;

    @OneToOne(mappedBy = "bloodBank", cascade = CascadeType.ALL, orphanRemoval = true)
    private BloodInventory bloodInventory;

    @OneToMany(mappedBy = "bloodBank", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BloodDonation> bloodDonations;  // If you have Blood Donations entity

}


/*
@Entity
@Table(name = "blood_banks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BloodBank {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false, length = 255)
    private String location;

    @OneToMany(mappedBy = "bloodBank", cascade = CascadeType.ALL)
    private List<BloodDonation> bloodDonations;

    @OneToOne(mappedBy = "bloodBank", cascade = CascadeType.ALL)
    private BloodInventory bloodInventory;

   */
/* @OneToMany(mappedBy = "bloodBank", cascade = CascadeType.ALL)
    private List<BloodInventory> bloodInventory;*//*

}
*/
/*
* 49963356-2f13-48e8-92f7-e74e3f15b94f
 a8bd084e-9e6e-4391-8785-3ea1e0a781d
*
* */
