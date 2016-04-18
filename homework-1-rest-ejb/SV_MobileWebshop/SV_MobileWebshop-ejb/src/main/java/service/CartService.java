/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import dto.MobileDTO;
import java.lang.invoke.LambdaMetafactory;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.inject.Inject;

@Stateful
@LocalBean
public class CartService {
    @Inject
    private InventoryService inventoryService;
    
    private List<MobileDTO> cart = new ArrayList<>();

    public List<MobileDTO> addToCart(MobileDTO mobile) {
        cart.add(mobile);
        return cart;
    }

    @Remove
    public void checkout() {
        cart.stream().forEach((mobileDTO) -> {
            inventoryService.buyMobile(mobileDTO);
        });
        this.cart.clear();
    }

    public List<MobileDTO> getProducts() {
        return cart;
    }
}
