package com.topflix.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AlertmanagerConfig {
    public static void start() throws IOException {
        // 현재 작업 디렉토리 가져오기
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("대답 : "+currentDirectory);
        // Alertmanager 설정 파일 경로
        String configFilePath = currentDirectory+"/resources/alertmanager.yml";

        // 설정 파일 내용을 읽어오기
        String configContent = new String(Files.readAllBytes(Paths.get(configFilePath)));

        // Alertmanager 서버를 초기화하고 시작하는 로직 추가
        // 실제 Alertmanager 서버는 외부 프로세스로 실행되어야 합니다.
        // 여기서는 설정 파일을 확인하는 예시만 보여줍니다.

        System.out.println("Alertmanager configuration loaded:");
        System.out.println(configContent);
    }

    public static void main(String[] args) {
        try {
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}