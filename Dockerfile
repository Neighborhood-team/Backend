# jdk11 가져오기
FROM openjdk:11-jdk

# jar 파일이 저장된 경로 인자
ARG JAR_FILE=build/libs/*.jar

# jar 파일 컨테이너 내로 복사
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]