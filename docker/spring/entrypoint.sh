#!/bin/bash
# entrypoint.sh

# フラグファイルのパスを指定
FLAG_FILE="/app/build/jooq-codegen-completed"

# jOOQのコード生成がまだ行われていないか確認
if [ ! -f "$FLAG_FILE" ]; then
  echo "Generating jOOQ code..."
  ./gradlew generateJooq

  echo "Creating flag file: $FLAG_FILE"
  touch "$FLAG_FILE"
else
  echo "jOOQ code generation is not needed."
fi

# アプリケーションの起動
./gradlew bootRun -x generateJooq
