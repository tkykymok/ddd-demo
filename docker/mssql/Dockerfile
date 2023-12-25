FROM mcr.microsoft.com/mssql/server:2022-latest

# rootユーザーに変更
USER root

# シェルスクリプトをイメージ内にコピー
COPY docker/initdb.d /docker-initdb.d

# シェルスクリプトに実行権限を追加
RUN chmod +x /docker-initdb.d/entrypoint.sh

# イメージからコンテナを作成・起動する際に実行されるコマンド
ENTRYPOINT ["/docker-initdb.d/entrypoint.sh"]
