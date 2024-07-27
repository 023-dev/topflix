package com.topflix.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LogbackConfig는 메시지 로깅을 위한 유틸리티 메서드를 제공합니다.
 */
public class LogbackConfig {
    // LoggerFactory를 사용하여 com.topflix.config.LogbackConfig 클래스에 대한 Logger 인스턴스를 생성합니다.
    private static final Logger logger = LoggerFactory.getLogger(LogbackConfig.class);

    /**
     * 정보 수준 메시지를 기록합니다.
     * @param message 기록할 메시지
     */
    public static void logInfo(String message) {
        // 정보 수준(info)의 메시지를 기록합니다.
        logger.info(message);
    }

    /**
     * 예외가 있는 오류 수준 메시지를 기록합니다.
     * @param message 기록할 메시지
     * @param t 기록할 예외
     */
    public static void logError(String message, Throwable t) {
        // 예외와 함께 오류 수준(error)의 메시지를 기록합니다.
        logger.error(message, t);
    }
}
