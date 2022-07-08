# 後悔しないためのSpring Boot 入門書：Spring 解体新書（第2版）: Spring Bootが丸分かり Spring解体新書 Kindle版

本と環境を変えた点  
IDE:vscode
  
依存関係（H2ではなくMySQLを利用）

- Spring Boot DevTools
- Lombok
- JDBC API
- Spring Data JDBC
- MySQL Driver
- Thymeleaf
- Spring Web


メモ：アーティファクトIDは小文字だけ？
https://spring.pleiades.io/initializr/docs/current/reference/html/

## 進め方
- 1,2章は環境構築なのでVSCodeとMySQLに合わせる。
第3章からgit管理。7/10までに回し終える。
- 章ごとにbranch切ってみる。


0706
- （未解決）UbutuにMySQL入れて起動せず。＞＞　windowsで起動
  https://www.digitalocean.com/community/tutorials/how-to-install-mysql-on-ubuntu-20-04-ja

- （解決）JDBCエラーが起きて起動できなかった。＞＞application.ymlの中身を書いていなかった。

0707
- VSCodeからgit push出来なくなった問題。＞＞configにgmailが２つ
- ssh
- 4章はWebアプリの仕組み
- 5章　DI！は依存性（オブジェクト）の注入のこと
- 6章　webjar

### DIとは
- インターフェースにインスタンスを自動で代入
- インスタンスの生成と破棄をしてくれる
- インスタンスのスコープ管理ができる

### DIの実装
@Autowiredアノテーション（フィールド、コンストラクタ、setter）

### DIの落とし穴
- singletonスコープ
- 異なるスコープのBeanをフィールドに持つと、インスタンスが破棄されない
- Bean以外からはDIできない


- 404エラーが起きた時にチェックする項目
部品のスキャン対象はメインのクラスが属するパッケージ  
https://rainbow-engine.com/java-spring-boot-404error-fix/

- ローカライズはymlファイルにすると上手くいかなかった

0708
- バインド（入力内容とクラスをマッピング）

0709
0710



## 1 章 Spring の 概要  
 1. 1 Spring とは
 1. 1. 1 フレーム ワーク とは 何 か？ 
 1. 2 Spring Boot とは 
 
## 2 章 開発環境 の 構築 
2. 1 通常 の eclipse 2. 2 Spring 用 の eclipse を インストール する … STS 2. 2. 1 STS の インストール 2. 2. 2 STS の 日本語 化 2. 3 Lombok の インストール 2. 4 プロジェクト の 作成 2. 4. 1 プロジェクト 作成 

## 3 章 HelloWorld… 簡単 な サンプル 
初歩文法
|||
|---|---|
|@Controller|画面からリクエストを受けるクラス。コントローラー|
|@GetMapping|GETメソッドのリクエストを受け付ける。|
|@PostMapping|POSTメソッドのリクエストを受け付ける。|
|@RequestParam|入力された値をサーバーが取得する方法の内ひとつ|
|@Repository|DB操作を表すリポジトリにつける|
|@Autowired|DI用|

- JdbcTemplateクラス：

lombok
|||
|---|---|
|@Data|getter,setter,toString,hashCode,equals|

- th:属性の値に${キー名}でModelクラスから値取得

3. 1 HTML の 表示 3. 2 画面 から 別 画面 に 値 を 渡す 3. 3 データベース から 値 を 取得 する 

## 4 章 Web アプリケーション の 概要 
## 5 章 Dependency Injection( 依存 性 の 注入) 
DIの処理
1. DIの対象クラスを探す
2. @Autowiredアノテーションがついている個所に、インスタンスを注入。

下記がDIのコンポねーとスキャンの対象
- @Component
- @Controller
- @Service 
- @Repository 
- @Configuration 
- @RestController 
- @ControllerAdvice 
- @ManagedBean 
- @Named
- @Mapper
- @Bean

### @Scopeでライフサイクル管理
スコープとは、インスタンスが生存する期間のことです。例えば、リクエストスコープではHTTPのリクエストが送られてくるたびにインスタンスが生成されます。そして、リクエストの処理が終わったらインスタンスが破棄されます。


|スコープ|説明|
|---|---|
|singleton(デフォルト)	|DIコンテナの起動時にBeanのインスタンスを生成し、同一のインスタンスを共有して利用する。スコープを設定しない場合はsingletonとして扱われる。|
|prototype	|Beanの取得時に毎回インスタンスを生成する。スレッドアンセーフなBeanの場合、singletonスコープを利用できないためprototypeを利用する。|
|session	|HTTPのセッション単位でBeanのインスタンスを生成する。Webアプリケーションの場合のみ有効。|
|request	|HTTPのリクエスト単位でBeanのインスタンスを生成する。Webアプリケーションの場合のみ有効。|
|globalSession	|ポートレット環境におけるGlobalSessionの単位でインスタンスを生成する。ポートレットに対応したWebアプリケーションの場合のみ有効。|
|application	|サーブレットのコンテキスト単位でBeanのインスタンスを生成する。Webアプリケーションの場合のみ有効。|
|カスタムスコープ(独自の命名)	|独自に定義したルールでBeanのインスタンスを生成する。|

DIの実装（@Autowired）
- フィールド
- コンストラクタ（@Autowiredを省略できる）
- setter

よく使うアノテーション
- @Component
- @Controller
- @Service
- @Repository
- @Bean
- @Mapper


流れ：クラスにアノテーションをつけてDIコンテナにBeanを登録
Beanのインスタンスを生成するクラス：JavaConfig

### DIの落とし穴
1. singletonスコープ（デフォルトがsingletonになるので複数からアクセスされる場合は注意）
2. 異なるスコープをフィールドに持つとインスタンスが破棄されない場合がある。
3. Bean以外からはDIできない

5. 1 DI( 依存 性 の 注入) とは 何 か? 5. 1. 1 依存 性 の 注入 5. 1. 2 インスタンス の ライフサイクル 管理 5. 2 DI の 実装 方法 5. 2. 1 依存 性 の 注入 方法5. 2. 2 Bean の 登録 方法 5. 3 DI の 落とし穴 5. 3. 1 singleton スコープ 5. 3. 2 異なる スコープ 5. 3. 3 Bean 以外 からは DI でき ない 

## 6 章 バインド＆ バリデーション( 入力 チェック) 

- @RequestMappingをクラスにつけるとURLのプリフィックスとして利用出来る。
- @GetMapping("/signup")

@GetMappingの代用
```java
@RequestMapping(value="/sample",method=RequestMethod.GET)
public String getSample(){
  //略
}
```
### PRG(POST-Redirect-GET)パターン
POSTメソッド＞リダイレクト＞画面遷移（GETメソッド）  
メリット：誤登録防止

- JS読み込み時は、defer必須：画面読み込みが改善

6. 1 画面 作成 6. 1. 1 ライブラリ の 使用… webjars 6. 1. 2 メッセージプロパティ 6. 1. 3 多 言語 化 6. 2 バインド 6. 2. 1 バインド 6. 2. 2 エラーメッセージ の 編集 6. 3 バリデーション 6. 3. 1 バリデーション の 実装 6. 3. 2 エラーメッセージ の 編集 6. 3. 3 バリデーション の 実行 順序 の 設定 

## 7 章 画面 レイアウト 7. 1 画面 レイアウト の 実装 
## 8 章 MyBatis 
8. 1 MyBatis の 概要 8. 2 MyBatis 基本 編 8. 2. 1 insert 8. 2. 2 select… 複数 件 8. 2. 3 select… 1 件 8. 2. 4 update・delete 8. 3 MyBatis 応用 編 8. 3. 1 動的 SQL 8. 3. 2 ネスト し た オブジェクト への マッピング 8. 3. 3 テーブル 結合( 一対多) 8. 4 トランザクション 8. 4. 1 トランザクション の 種類 8. 4. 2 トランザクション の 実装 8. 4. 3 宣言 的 トランザクション の 設定 

## 9 章 AOP 
9. 1 AOP の 概要 9. 1. 1 AOP とは 9. 1. 2 AOP の 用語 9. 1. 3 AOP の 仕組み 9. 2 AOP の 実装 9. 2. 1 Pointcut の 指定 方法 9. 2. 2 Before・After の 実装 9. 2. 3 Around の 実装

## 10 章 エラー 処理 
10. 1 エラー 画面 の 作成 10. 1. 1 共通 エラー 画面 10. 1. 2 HTTP エラー 毎 の エラー 画面 10. 2 例外処理 の 実装 方法 10. 2. 1 @AfterThrowing アスペクト での 例外処理 10. 2. 2 コントローラー クラス 毎 の 例外処理 10. 2. 3 Web アプリケーション 全体 の 例外処理 

## 11 章 Spring セキュリティ 
11. 1 概要 11. 2 認証 11. 2. 1 直 リンク の 禁止 11. 2. 2 ログイン 処理 11. 2. 3 イン メモリ 認証 11. 2. 4 パスワード の 暗号化 11. 2. 5 ユーザー データ 認証 11. 2. 6 ログアウト 処理 11. 2. 7 CSRF 対策 11. 3 認可 11. 3. 1 URL の 認可 11. 3. 2 画面 表示 の 認可 

## 12 章 REST 12. 1 REST の 概要 12. 1. 1 REST とは 12. 1. 2 なぜ REST を 使う のか？ 12. 2 REST の 実装 12. 2. 1 更新・削除 12. 2. 2 登録＆ バリデーション 12. 2. 3 検索 

## 13 章 Spring Data JPA
13. 1 概要 13. 2 基本 編 13. 2. 1 CRUD 13. 2. 2 任意 の SQL 13. 3 応用 編 13. 3. 1 動的 SQL 13. 3. 2 テーブル 結合( 多 対 一) 13. 3. 3 テーブル 結合( 一対多)
