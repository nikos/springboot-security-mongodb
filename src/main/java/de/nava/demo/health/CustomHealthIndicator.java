package de.nava.demo.health;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Builder builder) throws Exception {
        int errorCode = new Random().nextInt(2);
        log.info("*************************** errorCode: {}", errorCode);
        if (errorCode != 0) {
            builder.down().withDetail("errorCode", errorCode).build();
        } else {
            builder.up();
        }
    }

}
