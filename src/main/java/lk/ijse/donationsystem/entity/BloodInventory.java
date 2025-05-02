package lk.ijse.donationsystem.entity;

import jakarta.persistence.*;
import lk.ijse.donationsystem.BloodType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private List<BloodStock> bloodStockList = new ArrayList<>();

    /*public void addBloodStock(BloodStock bloodStock) {
        this.bloodStockList.add(bloodStock);
        bloodStock.setInventory(this);
    }

    public void removeBloodStock(BloodStock bloodStock) {
        this.bloodStockList.remove(bloodStock);
        bloodStock.setInventory(null);
    }*/
}

