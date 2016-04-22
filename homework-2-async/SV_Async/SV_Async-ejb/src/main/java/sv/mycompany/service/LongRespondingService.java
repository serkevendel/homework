package sv.mycompany.service;

import java.io.Serializable;
import java.util.concurrent.Future;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

/**
 *
 * @author Vendel
 */
@Stateless
@Asynchronous
public class LongRespondingService implements Serializable{

    @Resource
    private SessionContext ctx;

    public Future<String> longRespondingTask(int wait) throws InterruptedException {
        String status;
        for (int i = 0; i < 20; i++) {
            Thread.sleep(wait *50);

            if (ctx.wasCancelCalled()) {
                return new AsyncResult<>("cancelled");
            }
        }
        status = "completed";
        return new AsyncResult<>(status);
    }

}
