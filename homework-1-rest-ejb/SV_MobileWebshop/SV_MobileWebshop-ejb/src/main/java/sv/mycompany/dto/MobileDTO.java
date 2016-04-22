
package sv.mycompany.dto;

import sv.mycompany.annotation.Validate;
import sv.mycompany.interceptor.BeanValidation;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Validate
@BeanValidation
public class MobileDTO {
    @Pattern(regexp=".{36}")
    private String id = UUID.randomUUID().toString();
    @NotNull
    @Pattern(regexp="....*")
    private String type;
    @NotNull
    @Pattern(regexp="....*")
    private String manufacturer;
    @Min(value=1)
    private int price;
    @Min(value=0)
    private int piece;

    public MobileDTO(String type, String manufacturer, int price, int piece) {
        this.type = type;
        this.manufacturer = manufacturer;
        this.price = price;
        this.piece = piece;
    }

    public MobileDTO() {
        //Empty public constructor for Mobile
    }

    
    
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPiece() {
        return this.piece;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.type);
        hash = 71 * hash + Objects.hashCode(this.manufacturer);
        hash = 71 * hash + this.price;
        hash = 71 * hash + this.piece;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MobileDTO other = (MobileDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.manufacturer, other.manufacturer)) {
            return false;
        }
        return true;
    }

    
}