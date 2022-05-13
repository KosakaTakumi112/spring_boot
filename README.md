# spring_boot

#セットアップ方法


- git clone
- cd spring_boot

## コンテナ起動&web サーバー立ち上げ

- docker-compose up --build

## 画面確認

- localhost:8080 にて Hello world が出力される。

## テスト方法

- appコンテナ内にて./gradlew testを実行する。

## コンテナ停止

- Ctr + C
- docker-compose down

## ログファイルの出力先

- server/logs/spring.log
