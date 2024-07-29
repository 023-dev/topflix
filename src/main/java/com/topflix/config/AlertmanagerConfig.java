package com.topflix.config;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AlertmanagerConfig {
    public static void start() throws IOException {
        // Alertmanager 설정 파일 경로
        String configFilePath = "alertmanager.yml";

        // 클래스패스에서 설정 파일 로드
        InputStream inputStream = AlertmanagerConfig.class.getClassLoader().getResourceAsStream(configFilePath);
        if (inputStream == null) {
            throw new IOException("Configuration file " + configFilePath + " not found");
        }

        // 설정 파일 내용을 읽어오기
        String configContent = readFromInputStream(inputStream);

        // Alertmanager 서버를 초기화하고 시작하는 로직 추가
        // 실제 Alertmanager 서버는 외부 프로세스로 실행되어야 합니다.
        // 여기서는 설정 파일을 확인하는 예시만 보여줍니다.

        System.out.println("Alertmanager configuration loaded:");
        System.out.println(configContent);
    }
    private static String readFromInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString(StandardCharsets.UTF_8.name());
    }
}