package sv.mycompany.service;

import sv.mycompany.dto.MobileDTO;
import sv.mycompany.exception.BadRequestException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.inject.Inject;

@Stateful
public class CartService implements Serializable {

    @Inject
    private InventoryService inventoryService;

    private final List<MobileDTO> cart = new ArrayList<>();

    public List<MobileDTO> addToCart(MobileDTO mobile) {
        if (inventoryService.getMobilesList().contains(mobile)) {
            cart.add(mobile);
            return cart;
        } else {
            throw new BadRequestException("Mobile is not available in the shop");
        }
    }

    @Remove
    public void checkout() {
        for (MobileDTO mobileDTO : cart) {
            inventoryService.buyMobile(mobileDTO);
        }
    }

    public List<MobileDTO> getCart() {
        return cart;
    }

}
