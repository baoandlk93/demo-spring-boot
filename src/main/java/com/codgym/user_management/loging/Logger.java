package com.codgym.user_management.loging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
    private final DiscordLogger discordLogger;

    public Logger(DiscordLogger discordLogger) {
        this.discordLogger = discordLogger;
    }

    @Pointcut("execution(* com.codgym.user_management.service..*(..)) && !within(com.codgym.user_management.loging.Logger)")
    public void log() {
    }

    @AfterThrowing(pointcut = "log()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        String message = "Exception in method: " + joinPoint.getSignature() + " with cause: " + exception.getMessage() + exception.getCause();
        discordLogger.sendLog(message);
    }
}
