package com.booking.aspect;

import com.booking.annotation.SingleExecutionCron;
import com.booking.repository.CronRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;


@Aspect
@Component
public class SingleExecution {

    private CronRepository cronRepo;

    @Autowired
    public SingleExecution(CronRepository cronRepo) {
        this.cronRepo = cronRepo;
    }

    @Around("@annotation(com.booking.annotation.SingleExecutionCron)")
    @Transactional
    public void ensureSingleExecution(ProceedingJoinPoint pjp) throws Throwable {

        MethodSignature signature = (MethodSignature) pjp.getSignature();

        SingleExecutionCron annotation = signature.getMethod().getAnnotation(SingleExecutionCron.class);
        String cronName = annotation.cronName();

        DateTime currentTimestamp = new DateTime().minuteOfDay().roundHalfEvenCopy();
        if (cronRepo.updateCron(cronName, new Timestamp(currentTimestamp.getMillis())) > 0) {
            pjp.proceed();
        }
    }

}