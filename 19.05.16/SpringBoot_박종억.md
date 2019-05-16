# Thymeleaf는?
```
* "타임 리프"라고 읽는다.
* 텍스트, HTML, XML, Javascript, CSS 그리고 텍스트를 생성할 수 있는 템플릿 엔진이다.
* 순수 HTML로 템플릿을 작성할 수 있다.
* Spring Boot에서 사용이 권장되고 있다. (Spring Boot에서는 JSP를 추천하지 않는다.)

의존관계 추가build.gradle

dependencies {
compile 'org.springframework.boot:spring-boot-starter-web'
+ compile 'org.springframework.boot:spring-boot-starter-thymeleaf'
compile 'org.webjars:jquery-ui:1.11.4'
}
Thymeleaf을 사용할 수 있도록 의존관계을 추가한다.컨트롤러의 구현

@Controller@RequestMapping("/hello")
public class HelloController {

@RequestMapping(method=RequestMethod.GET)
public String hello() {
return "hello";
}
}
* 템플릿을 반환하는 경우 클래스에 @RestController 대신에 @Controller 어노테이션을 부여한다.
* 메소드의 반환 값으로 표시하는 템플릿의 경로를 지정한다.
* 템플릿 파일은 클래스 경로에 templates 패키지 아래에 위치한다.
* 컨트롤러 메소드가 리턴 한 문자열이 templates 패키지에서 상대 경로가 된다 (확장자는 생략 가능).

<dl>
<dt>id</dt>
<dd th:text="${myData.id}"></dd>

<dt>value</dt>
<dd th:text="${myData.value}"></dd>
</dl>

* 컨트롤러 메소드에서 Model을 인수받을 수 있도록 한다.
* 이 Model의 addAttribute () 메소드를 사용하여 화면에 출력하고 싶은 정보를 설정한다.
* 화면 측에서 먼저 Thymeleaf에 대한 네임 스페이스를 정의 (xmlns : th)
* th : text 속성에 지정된 값을 텍스트로 출력한다.
* th : text의 값은 $ {...}와 같이 EL 표현식스러워 출력 값을 지정한다.

반복출력
<dl th:each="hoge : ${hogeList}">
<dt>id</dt>
<dd th:text="${hoge.id}"></dd>

<dt>value</dt>
<dd th:text="${hoge.value}"></dd>
</dl>
```
# 외부 데이터 베이스 이용MySQL 테이블
```
로컬 MySQL을 이용한다.test_table
id  value
1   hoge
2   fuga
3   piyo

코드 작성build.gradle

dependencies {
compile 'org.springframework.boot:spring-boot-starter-jdbc'
compile 'mysql:mysql-connector-java:5.1.35'
}application.properties

spring.datasource.url=jdbc:mysql://localhost/test
spring.datasource.username=test
spring.datasource.password=test
spring.datasource.driver-class-name=com.mysql.jdbc.Driversrc/main/java/sample/springboot/Main.java

package sample.springboot;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplicationpublic class Main {

public static void main(String[] args) {
try (ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args)) {
Main m = ctx.getBean(Main.class);
m.method();
}
}

@Autowired
private JdbcTemplate jdbc;

public void method() {
List<Map<String, Object>> list = this.jdbc.queryForList("SELECT * FROM TEST_TABLE");
list.forEach(System.out::println);
}
}실행 결과

{id=1, value=hoge}
{id=2, value=fuga}
{id=3, value=piyo}
* application.properties 연결 설정을 추가하여, 외부 DB에 접속할 수있다.
```
