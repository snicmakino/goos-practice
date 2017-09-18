# テスト駆動開発学習
- 基本的には実践テスト駆動開発（GOOS）をなぞる
- JUnit5を利用する

## 事前準備（openfireのインストールおよび設定）
GOOSのサンプルプロジェクトはXMPPでのメッセージングを行うものなので、
openfireのインストールと、設定を行う必要がある  
https://www.igniterealtime.org/projects/openfire/  
  
openfireをインストール、起動したら、以下の設定を行う  

### ユーザ作成
ユーザ名/パスワード  
sniper/sniper  
auction-item-54321/auction  
auction-item-65432/auction

### 競合ポリシーの設定
Server - Server Settings - Resource Policy  
Never kickにチェックをつける