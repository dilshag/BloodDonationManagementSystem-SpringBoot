package lk.ijse.donationsystem.exception;

import java.util.UUID;

public class InventoryNotFoundException2 extends RuntimeException {
    public InventoryNotFoundException2(UUID id) {
        super("Inventory Not Found Exception");
    }
}
