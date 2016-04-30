package sv.mycompany.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import sv.mycompany.bean.StatisticsBean;
import sv.mycompany.message.JobMessage;

/**
 *
 * @author Vendel
 */
@MessageDriven(mappedName = "dzsobTopik")
public class BeanService implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(StatisticsBean.class.getSimpleName());

    @Inject
    private StatisticsBean statisticsBean;

    @Override
    public void onMessage(Message message) {

        try {
            JobMessage jms = message.getBody(JobMessage.class);

            if (jms.isSchedule()) {
                statisticsBean.setScheduledJobs(statisticsBean.getScheduledJobs() + 1);
            } else if (!jms.isSchedule() && statisticsBean.getScheduledJobs() > 0) {
                boolean success = jms.getTimestamp() < 5;
                LOGGER.log(Level.INFO, "Job " + jms.getJobnumber() + " successful: " + Boolean.toString(success));
                statisticsBean.getResults().put(jms.getJobnumber(), success);
                statisticsBean.setScheduledJobs(statisticsBean.getScheduledJobs() - 1);
            }
        } catch (JMSException ex) {
            Logger.getLogger(BeanService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
