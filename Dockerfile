# Dockerfile

# OpenJDK 17을 베이스 이미지로 사용
FROM openjdk:17-jdk

# 작업 디렉토리 설정
WORKDIR /app

# Gradle 또는 Maven 등을 사용하여 빌드된 JAR 파일을 /app 디렉토리로 복사
COPY build/libs/*.jar /app/app.jar

# 환경 변수 설정 (예시: 포트 설정)
ENV SERVER_PORT=9090

# 컨테이너가 사용할 포트를 외부에 노출
EXPOSE 9090

# 컨테이너 실행 시 실행될 명령어 설정
CMD ["java", "-jar", "/app/app.jar"]
