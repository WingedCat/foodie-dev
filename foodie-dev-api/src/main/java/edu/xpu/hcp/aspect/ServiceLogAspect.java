package edu.xpu.hcp.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import sun.security.ec.point.ProjectivePoint;

/**                                                                                ____________________
      _                _                                                           < 神兽护体，永无bug! >
    | |__  _   _  ___| |__   ___ _ __   __ _ _ __   ___ _ __   __ _                --------------------
   | '_ \| | | |/ __| '_ \ / _ \ '_ \ / _` | '_ \ / _ \ '_ \ / _` |                       \   ^__^
  | | | | |_| | (__| | | |  __/ | | | (_| | |_) |  __/ | | | (_| |                        \  (oo)\_______
 |_| |_|\__,_|\___|_| |_|\___|_| |_|\__, | .__/ \___|_| |_|\__, |                           (__)\       )\/\
                                   |___/|_|                |___/                                ||----w |
                                                                                                ||     ||
 * @author huchengpeng
 * @date 2020/11/15 19:38
 */
@Aspect
@Slf4j
@Component
public class ServiceLogAspect {

    @Pointcut("@annotation(edu.xpu.hcp.annotation.ServiceTimer)")
    public void pointcut() {
        // do nothing
    }

    @Pointcut("execution(* edu.xpu.hcp.service.impl..*.*(..))")
    public void pointcutV2() {
        // do nothing
    }

    @Around("pointcutV2()")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint)  throws Throwable{
        log.info("**********开始执行{}.{}**********",joinPoint.getTarget().getClass(),joinPoint.getSignature().getName());
        //开始时间
        long beginTime = System.currentTimeMillis();
        //执行目标方法
        Object result = joinPoint.proceed();
        log.info("**********执行完毕{}.{}**********",joinPoint.getTarget().getClass(),joinPoint.getSignature().getName());
        //结束时间
        long endTime = System.currentTimeMillis();
        long takeTime = endTime - beginTime;
        if(takeTime > 3000){
            log.error("==========执行结束，耗时：{}毫秒==========",takeTime);
        }else if(takeTime > 2000){
            log.warn("==========执行结束，耗时：{}毫秒==========",takeTime);
        }else{
            log.info("==========执行结束，耗时：{}毫秒==========",takeTime);
        }
        return result;
    }
}
