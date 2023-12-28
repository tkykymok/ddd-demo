FROM amazoncorretto:17

WORKDIR /app

COPY build.gradle ./
COPY gradlew ./
COPY gradle ./gradle/
COPY settings.gradle ./
# シェルスクリプをイメージ内にコピー
COPY docker/spring/entrypoint.sh ./spring-entrypoint.sh
# 実行権限を付与
RUN chmod +x ./spring-entrypoint.sh
# gradlewに実行権限を追加
RUN chmod +x ./gradlew

# コンテナを起動する際に実行されるコマンド
ENTRYPOINT ["/app/spring-entrypoint.sh"]