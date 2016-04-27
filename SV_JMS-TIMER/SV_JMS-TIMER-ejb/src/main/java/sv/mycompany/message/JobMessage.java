package sv.mycompany.message;

import java.io.Serializable;

/**
 *
 * @author Vendel
 */
public class JobMessage implements Serializable {

    private int timestamp;
    private int jobnumber;
    private boolean schedule;

    public JobMessage(int timestamp, int jobnumber, boolean schedule) {
        this.timestamp = timestamp;
        this.jobnumber = jobnumber;
        this.schedule = schedule;
    }

    public boolean isSchedule() {
        return schedule;
    }

    public void setSchedule(boolean schedule) {
        this.schedule = schedule;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(int jobnumber) {
        this.jobnumber = jobnumber;
    }

}
