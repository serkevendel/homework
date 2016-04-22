package sv.mycompany.service;

import sv.mycompany.dto.MobileDTO;
import sv.mycompany.exception.BadRequestException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class InventoryService {

    private final List<MobileDTO> inventory = new ArrayList<>();

    @PostConstruct
    public void init() {
        this.addMobile(new MobileDTO("Iphone6s", "Apple inc", 2000, 3));
        this.addMobile(new MobileDTO("Galaxy S6 Edge", "Samsung", 1800, 12));
        this.addMobile(new MobileDTO("G4", "LG", 1600, 5));
        this.addMobile(new MobileDTO("P8 Lite", "Huawei", 1400, 0));
    }

    public MobileDTO addMobile(MobileDTO mobile) {
        for (MobileDTO m : inventory) {
            if (m.equals(mobile)) {
                throw new BadRequestException("Given mobile is already added");
            }
        }
        this.inventory.add(mobile);
        return mobile;
    }

    public MobileDTO buyMobile(MobileDTO mobile) {
        for (MobileDTO mobileDTO : inventory) {
            if (mobile.getId().equals(mobileDTO.getId()) && mobileDTO.getPiece() > 0) {
                mobileDTO.setPiece(mobileDTO.getPiece() - 1);
                return mobileDTO;
            }
        }
        throw new BadRequestException("Requested mobile is not available or ran out of stock!");
    }

    public List<MobileDTO> getMobilesList() {
        return inventory;
    }
}
