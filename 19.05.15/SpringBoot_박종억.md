Spring Boot 란?
===============
```
	* Spring 프로젝트가 제공하는 다양한 라이브러리와 프레임워크로 빨리게 사용할 수 있게 하는 프레임워크이다.
	* Dropwizard의 Spring 버전 같은 것이다.
	* 빌드하면 단일 jar 파일이 생긴다.
	* Web 어플리케이션의 경우, 내장 Tomcat을 시작 (Jetty와 Undertow로 전환 가능).
	* Web 응용 프로그램이 아니라 보통의 Java 프로그램으로도 동작하게 할 수 있다.
	* Maven이나 Gradle 같은 빌드 도구를 사용한다. (Ant로는 불가능하다)
	* 사용하려는 컨포넌트를 종속적으로 추가하면 결합에 필요한 설정이 자동으로 이루어진다.
```

### * 서버 포트 번호 변경하기application.properties
```
server.port=1598어플리케이션 실행

$ gradle bootRun
(생략)
2017-08-17 22:30:13.211 INFO 14485 --- [ main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 1598 (http)
(생략)설명
	* server.port으로 포트 번호를 지정할 수 있다.
	* application.properties에 대한 설명은 여기.
	* 그 밖에도 다음과 같은 변경이 가능하다.
	* server.address : 서버 주소 (localhost로 하면 로컬에서만 액세스 할 수 없게 된다.)
	* server.sessionTimeout : 세션 타임아웃 시간.
```

### * @Controller와 @RestController의 차이
```
먼저 @Controller와 @RestController의 차이를 설명한다.
Spring MVC에서 컨트롤러 클래스에 어노테이션으로 @Controller 또는 @RestController을 붙인다.

@Controller는 주로 Web 페이지의 컨트롤러에서 사용한다.
Web 페이지용 컨트롤러는 JSP나 템플릿 엔진 View로 전환 응답의 HTML을 생성하기 때문에 기본적으로 메소드의 반환 값은 View 전환 대상을 지정하는 데 사용한다.

@RestController는 Json이나 XML 등을 반환 WebAPI 용 컨트롤러로 사용한다.
이것은 View로 전환하지 않기 때문에 메소드의 반환 값은 응답(response)의 내용(content)이 된다.
@RestController을 붙인 컨트롤러 메소드에서는 @ResponseBody를 사용하지 않고도 반환 값이 응답 내용이 된다.
* 메소드의 인수에 @RequestParam 어노테이션를 부여하면 쿼리 매개 변수를 얻을 수 있다.
* 인수의 형태가 Map이라면 쿼리 파라미터 정보를 Map 형태로 얻을 수 있다.
* 하나의 매개 변수에 여러 값이 설정되어있는 경우, Spring에서 제공하는 MultiValueMap로 받을 수 있다.
* @RequestHeader로 헤더 정보를 얻을 수 있다.
* @RequestBody에서 요청 바디를 얻을 수있다.
* 메소드를 @ResponseStatus에 어노테이션을 부여하고 value에 상태 코드를 지정하면, 그 응답의 상태 코드를 지정할 수 있다.
* 아무것도 지정하지 않으면 200 OK가 반환된다.
```

### * 포워드
```
반환 값으로 지정하여 이동처로 "forward:"를 붙이면 다른 컨트롤러 메소드로 이동한다.리다이렉트리다이렉트하려면 "redirect:"를 붙인다.
컨트롤러에서 직접 텍스트 콘텐츠를 반환한다.
메소드에 @ResponseBody를 붙이면 반환 값으로 직접 응답 내용을 반환할 수있다.
```
