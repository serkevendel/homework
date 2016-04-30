package sv.mycompany.bean;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.Topic;
import sv.mycompany.dto.Job;
import sv.mycompany.message.JobMessage;

/**
 *
 * @author Vendel
 */
@Singleton
@Startup
public class JobScheduler {

    @Inject
    private JMSContext jmsContext;

    @Resource(lookup = "dzsobKju")
    private Queue queue;

    @Resource(lookup = "dzsobTopik")
    private Topic topic;

    @Resource
    private TimerService timerService;

    @PostConstruct
    public void setTimer() {

        ScheduleExpression se = new ScheduleExpression();
        se.dayOfMonth("*");
        se.hour("*");
        se.minute("*");
        timerService.createCalendarTimer(se);
    }

    @Timeout
    public void queueNewJobs(Timer timer) {
        for (int i = 0; i < 10; i++) {
            Job.setNumber(Job.getNumber()+1);
            Job job = new Job();
            jmsContext.createProducer().send(queue, job);
            jmsContext.createProducer().send(topic, new JobMessage(job.getEstimatedSeconds(), job.getNumberid(), true));
        }
    }

}
