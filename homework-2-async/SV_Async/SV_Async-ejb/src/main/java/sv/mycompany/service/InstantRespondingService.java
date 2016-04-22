package sv.mycompany.service;

import javax.ejb.Stateless;

/**
 *
 * @author Vendel
 */
@Stateless
public class InstantRespondingService {
    
    public String instantMethod(String text){
        return text;
    }
}
