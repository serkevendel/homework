package sv.mycompany.dto;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Vendel
 */
public class Job implements Serializable {

    private static int number = 0;
    private int estimatedSeconds;
    private int numberid;

    public Job() {
        number++;
        numberid = number;
        Random random = new Random();
        estimatedSeconds = random.nextInt(5 - 1) + 1;
    }

    public int getNumberid() {
        return numberid;
    }

    public void setNumberid(int numberid) {
        this.numberid = numberid;
    }

    public static int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        Job.number = number;
    }

    public int getEstimatedSeconds() {
        return estimatedSeconds;
    }

    public void setEstimatedSeconds(int estimatedSeconds) {
        this.estimatedSeconds = estimatedSeconds;
    }

}
