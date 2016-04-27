package sv.mycompany.bean;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.jms.JMSContext;

/**
 *
 * @author Vendel
 */
@Singleton
@Startup
public class StatisticsBean {

    private int scheduledJobs = 0;

    @Inject
    private JMSContext jmsContext;

    private Map<Integer, Boolean> results = new HashMap<>();

    public StatisticsBean() {
    }

    public int getScheduledJobs() {
        return scheduledJobs;
    }

    public void setScheduledJobs(int scheduledJobs) {
        this.scheduledJobs = scheduledJobs;
    }

    public JMSContext getJmsContext() {
        return jmsContext;
    }

    public void setJmsContext(JMSContext jmsContext) {
        this.jmsContext = jmsContext;
    }

    public Map<Integer, Boolean> getResults() {
        return results;
    }

    public void setResults(Map<Integer, Boolean> results) {
        this.results = results;
    }
}
