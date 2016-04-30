package sv.mycompany.worker;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Topic;
import sv.mycompany.dto.Job;
import sv.mycompany.message.JobMessage;

/**
 *
 * @author Vendel
 */
@MessageDriven(mappedName = "dzsobKju")
public class SlowWorker implements MessageListener {

    @Inject
    protected JMSContext jmsContext;

    @Resource(lookup = "dzsobTopik")
    protected Topic topic;

    public SlowWorker() {
        //empty constructor
    }

    @Override
    public void onMessage(Message message) {
        try {
            Job receivedJob = message.getBody(Job.class);
            try {
                int worktime = receivedJob.getEstimatedSeconds() * 2;
                Thread.sleep(worktime * 1000);
                JobMessage jcm = new JobMessage(worktime, receivedJob.getNumberid(), false);
                jmsContext.createProducer().send(topic, jcm);
            } catch (InterruptedException ex) {
                Logger.getLogger(FastWorker.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (JMSException ex) {
            Logger.getLogger(FastWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
