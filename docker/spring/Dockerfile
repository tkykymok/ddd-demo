FROM amazoncorretto:17

WORKDIR /app

COPY build.gradle ./
COPY gradlew ./
COPY gradle ./gradle/
COPY settings.gradle ./

# gradlewに実行権限を追加
RUN chmod +x ./gradlew

# イメージからコンテナを作成・起動する際に実行されるコマンド
CMD [ "/bin/bash", "-c", "./gradlew bootRun" ]
