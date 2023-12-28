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

# イメージからコンテナを作成・起動する際に実行されるコマンド
#CMD [ "/bin/bash", "-c", "./gradlew build && ./gradlew generateJooq && ./gradlew bootRun" ]
# エントリーポイントを設定
ENTRYPOINT ["/app/spring-entrypoint.sh"]