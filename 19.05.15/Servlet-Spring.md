# 0515 SERVLET
* 포워딩 종류
![포워딩종류 정리](https://i.imgur.com/cOYaU29.png)
-----
* request get메서드 정리
![request_get메서드정리](https://i.imgur.com/Nl2yuqm.png)
-----
* redirect 와 forward의 차이
![Redirect-forward차이](https://i.imgur.com/wJhByw1.png)
-----
* 자바 웹프로그래밍은 서블릿 프로그래밍이다.

* 톰캣이 CGLib를 사용해서 클래스파일의 메서드, 어노테이션을 다 스캔해서
  정보를 뽑아오고
```java
@WebServlet(value = "/*", initParams = @WebInitParam(name="encoding", value="utf-8"))
아래의 웹.xml에 있는 내용과 동일
<filter>
  <filter-name>EncodingFilter</filter-name>
  <filter-class>com.cafe24.web.filter.EncodingFilter</filter-class>
  <init-param>
    <param-name>encoding</param-name>
    <param-value>utf-8</param-value>
  </init-param>
</filter>
<filter-mapping>
  <filter-name>EncodingFilter</filter-name>
  <url-pattern>/\*</url-pattern>
</filter-mapping>
```

* web.xml은 없어도 크래스에 어노테이션으로 다 대체할수있따.
  사실 없어도 되는거임.

* 평과과제는 제이블로그

* Application > Session > request > page (오래 살아있는 범위)
  session은 sesionid가 없어지면 내려간다.

  context범위안에서 해야됨 el은 변수랑 아무상관없음

* jsp페이지에서 서블릿을통해 request나 session을 가져올때
  변수가 겹치게만들면안되지만 겹치게되면 request가 session보다 먼저이다.
  부득이하게 겹칠경우에 ${session.scope.vo.no} 이런식으로 가져오면
  세션값으로 가져올수있음

* function이라는 jstl 라이브러리에 들어가있다.
  ```html
  ${list.size} --> list의 사이즈 메서드를 가져오는게 아니라 getsize하는거임
  ```

* jstl의 core,format,function기능을 쓰려면 스크립트 상단에
```html
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fn"%>
```
을 써줘야한다.
그리고 java의 if문은
  ```html
  <c:if test="">

  </c:if>
  ```
   이거와 같고
   if else문은
   ```html
   <c:choose>
   <c:when test='${param.c == "red"}'>
      <h1 style='color: #f00'>Hello World</h1>
    </c:when>
    <c:when test='${param.c == "green"}'>
      <h1 style='color: #0f0'>Hello World</h1>
    </c:when>
    <c:when test='${param.c == "blue"}'>
      <h1 style='color: #00f'>Hello World</h1>
    </c:when>
    <c:otherwise>
    <h1 style='color: #000'>Hello World</h1>
    </c:otherwise>
  </c:choose>
  ```
  이거하고 같다

* <%=request.getContextPath()%> -> el에서는 ${pageContext.servletContext.contextPath}

* 백엔드니 프론트엔드니 하나만 다 잘하는건 헛소리다. 실무에서 일하는 사람들은 두개다 잘하는데 직무떄문에 그거 하는거임

-------------

# 스프링이란

* 자바 엔터프라이즈 개발(비즈니스)를 편하게 해주는 오픈소스 `경량급 애플리케이션 프레임워크`

* sPRING MVC는 웹개발을 편하게 해주는 방식
  SPRING 은 mvc개발을 편하게 컨테이너를 관리해주는 느낌

* servlet은 톰캣에서 지원하는 라이브러리를 통해서 만들어야되기떄문에 톰캣에 의존적이고 톰캣에서만 개발할 수 있음.

* 경량급이라는 의미는 EJB컨테이너에 비해 가볍다는 소리이다.
  이 컨테이너가 관리하는 BEAN들을 만들때 EJB컨테이너와 비교해보면 컨테이너 자체고 가볍지만
   SPRING 컨테이너안에 들어가있는 빈들이 POJO기반의 엔터프라이즈 서비스를 구현할 수 있었다.
  이 것때문에 경량급이라고한다

* **주요전략**
  * POJO를 이용한 가볍고 비침투적 개발
    ```java
    GuestbookServlet extends HttpServlet
    //내가 만드려는것은 게스트북서블릿인데 톰캣의 HttpServlet을 상속하고 오버로드한걸 써야하고
    //이런걸 기술침투라고 한다.
    //침투적이면 JDBC로쓰다가 다른 드라이버로 쓰려고 한다면 다시 처음부터 구성해야한다.
    ```
  * DI와 인터페이스 지향을 통한 느슨한 결합도

---------
  # 스프링의 구동과정

  * ApplicationContext ac = new WebXmlApplicationContext(cofigPath); -> 컨테이너가 만들어지는 코드

  * spring-servlet.xml
    * <context:annotation-config /> 어노테이션을 해놓은 곳을 찾겠다 (@Controller)
