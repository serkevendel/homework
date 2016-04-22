package sv.mycompany.service;

import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author Vendel
 */
@Stateless
public class InstantRespondingService implements Serializable {
    
    public String instantMethod(String text){
        return text;
    }
}
