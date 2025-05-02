package lk.ijse.donationsystem.repo;

import lk.ijse.donationsystem.entity.BloodBank;
import lk.ijse.donationsystem.entity.BloodInventory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BloodInventoryRepository extends JpaRepository<BloodInventory, UUID> {
    @EntityGraph(attributePaths = {"bloodStockList"})
    Optional<BloodInventory> findByBloodBank(BloodBank bank);
}
/*
* LazyInitializationException eka enna reason eka bloodStockList
*  lazy-load karanna try karana kota session eka close wela nisa.
* Hibernate can't fetch the collection once the session is closed.
* BloodInventory inventory = bloodInventoryRepository.findByBloodBank(request.getBloodBank())
meka return karana BloodInventory entity eke bloodStockList lazy load (@OneToMany(fetch = FetchType.LAZY))
* nisa session eka close unaama access karanna ba wenawa:
*
* Option 1: Use @EntityGraph to fetch bloodStockList eagerly
Create a custom query in BloodInventoryRepository with an entity graph.

.*/