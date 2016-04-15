/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

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
public class AsyncEJB {
    
    @Resource
    private SessionContext ctx;
    
    public Future<Integer> longTimeOperation(){
        
        int num=4000000;
        int returnNum = 0;
        
        for(int i = 0; i<= num ; i++){
            if(i == num){
                returnNum=num;
			}
        }
    
    
    if (ctx.wasCancelCalled()) {
            return new AsyncResult<>(0);
        } else {
            return new AsyncResult<>(returnNum);
        }
    }
}
