package com.personal.oyl.event.web;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.personal.oyl.event.BaseSubscriber;
import com.personal.oyl.event.EventConsumer;
import com.personal.oyl.event.EventMapper;
import com.personal.oyl.event.Master;
import com.personal.oyl.event.SubscriberConfig;
import com.personal.oyl.event.Worker;

@Component
public class AppListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger log = LoggerFactory.getLogger(AppListener.class);
    
    @Autowired
    private EventMapper mapper;
    
    @Resource
    private BaseSubscriber dailyOrderReportSubscriber;
    
    @Resource
    private BaseSubscriber userOrderReportSubscriber;
    

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            SubscriberConfig.instance().addSubscriber("o_c", dailyOrderReportSubscriber);
            SubscriberConfig.instance().addSubscriber("o_c", userOrderReportSubscriber);
            
            
            EventConsumer consumer = new EventConsumer();
            Thread kafkaConsumer = new Thread(consumer);
            kafkaConsumer.start();
            
            Master master = new Master();
            Worker worker = new Worker(mapper);
            
            try {
                log.error("start worker...");
                worker.start();
            } catch (IOException | InterruptedException | KeeperException e) {
                log.error(e.getMessage(), e);
            }
            
            try {
                log.error("start master...");
                master.start();
            } catch (IOException | InterruptedException | KeeperException e) {
                log.error(e.getMessage(), e);
            }
            
            Runtime.getRuntime().addShutdownHook(new Thread(() ->  {
                consumer.wake();
                kafkaConsumer.interrupt();
                master.close();
                worker.close();
            }));
        }
    }

}
