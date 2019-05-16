## Bean
`스프링이 제어권(IoC)을 가지고 직접 만들고 관계를 부여하는 오브젝트를 빈(Bean) 이라고 부른다`
- 오브젝트 단위의 애플리케이션 컴포넌트
- 스프링 빈은 스프링 컨테이너가 생성과 관계설정, 사용 등을 제어해 주는 제어의 역전이 적용된 오브젝트

## Application Context

_빈 팩토리 (BeanFactory) : 빈의 생성과 관계설정 같은 제어를 담당하는 IoC오브젝트_
__어플리케이션 컨택스트는 IoC방식을 따라 만들어진 일종의 빈 팩토리이며, 스프링이 제공하는 애플리케이션 지원 기능을 모두 포함해서 이야기 하는 것이다.__
 - 애플리케이션 전반에 걸쳐 모든 구성요소의 제어 작업을 담당하는 범용적인 IoC엔진
 - 애플리케이션 컨텍스트 = IoC 컨테이너 = 스프링 컨테이너 = 빈 팩토리
 - ApplicationContext 인터페이스를 구현한 클래스의 오브젝트

## 스프링이 제공하는 ApplicationContext 구현 클래스
__1. StaticApplicationContext__
  - 코드를 통해 빈 메타정보를 등록하기 위해 사용
  - 테스트 목적으로 사용하며, 실전에서는 사용되지 않는다.

__2. GenericApplicationContext__
  - 가장 일반적인 애플리케이션 컨텍스트의 구현 클래스.
  - 실전에서 사용될 수 있는 모든 기능을 갖추고 있다.
  - xml 파일과 같은 외부의 리소스에 있는 빈 설정 메타 정보를 읽어들여서 메타정보로 전환해서 사용한다.
  - XmlBeanDefinitionReader를 이용해 빈 설정정보를 읽어들인다.

__3. GenericXmlApplicationContext__
  - 코드에서 GenericXmlApplicationContext를 사용하는 경우, 번거롭게 XmlBeanDefinitionReader를 직접 만들지 말고， 이 두 개의 클래스가 결합된 GenericXmlApplicationContext를 사용하면 편리하다.  
  - GenericXmlApplicationContext는 XmlBeanDefinitionReader를 내장하고 있기 때문에， XML 파일을 읽어들이고 refresh()를 통해 초기화하는 것까지 한 줄로 끝낼 수 있다.

__4. WebApplicationContext__
- 스프링 애플리케이션에서 가장 많이 사용되는 애플리케이션 컨텍스트
- 웹 환경에서 사용할 때 펼요한 기능이 추가된 애플리케이션 컨텍스트다(스프링 애플리케이션은 대부분 서블릿 기반의 독립 웹 애플리케이션WAR으로 만들어짐)

---

## web.xml 기본설정

__1. DispatcherServlet__
DispatherServlet은 클라이언트의 요청을 전달받는 서블릿으로서, 컨트롤러나 뷰와 같은 스프링 MVC의 구성 요소를 이용하여 클라이언트에게 서비스를 제공

- DispatherServlet의 설정은 웹 어플리케이션의 /WEB-INF/web.xml 파일에 추가하며, 다음과 같이 서블릿과 매핑 정보를 추가하면 DispatherServlet 설정이 완료된다.
- 서블릿 이름 ‘spring’ 에 ‘-servlet.xml’ 라는 문자열을 부가함으로써 컨테이너 상에 로드된 스프링 설정 파일명이 결정된다.
```java
<servlet>
  <servlet-name>spring</servlet-name>
  <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
</servlet>

<servlet-mapping>
  <servlet-name>spring</servlet-name>
  <url-pattern>/</url-pattern>
</servlet-mapping>
```



__2. ContextLoaderListener__  
- 공통빈을 설정
- ContextLoaderListener 와 DispatcherServlet은 각각 WebApplicationContext 인스턴스를 생성하게 되는데 , ContextLoaderListener가 생성한 컨텍스트가 root컨텍스트가 되고, DispatcherServlet이 생성한 인스턴스는 root 컨텍스트를 부모로 사용하는 자식 컨텍스트가 된다.

  이때 자식 컨텍스트들은 root 컨텍스트가 제공하는 빈을 사용할 수 있기 때문에 ContextLoaderListener를 이용하여 공통빈을 설정하는 것이다.
(ContextConfigLocation을 명시하지 않으면, /WEB-INF/applicationContext.xml을 설정파일로 사용)

`ApplicationContext과 WebApplicationContext과의 관계`
![](https://t1.daumcdn.net/cfile/tistory/2309EC49548C4B8925)

__3. 설정 파일 순서__ 
 설정 파일을 복수로 사용할 때에는, 설정 파일을 읽는 순서가 중요하다. 설정 파일 A에서 참조하는 bean을 다른 설정 파일 B에 정의하고 있는 경우, A보다 먼저 B를 읽지 않으면 오류가 일어난다.


__4. 설정파일 한 개 이상 설정 하기__
```java

<context-param>

	<param-name>contextConfigLocation</param-name>

	<param-value>

		/WEB-INF/security.xml 

		/WEB-INF/applicationContext.xml

	</param-value>

</context-param>

<servlet>

     <servlet-name>dispather</servlet-name>

     <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>

    <init-param>

        <param-name>contextConfiguration</param-value>

        <param-value>	

         /WEB-INF/main.xml

         /WEB-INF/dds.xml

        </param-value>

    </init-param>

</servlet>

```
- contextConfigLocation 초기화 파라미터는 설정 파일 목록을 값으로 갖는데, 이때 각 설정 파일은 콤마(","), 공백 문자(""), 탭(\t), 줄 바꿈(\n), 세미클론(":")을 이용하여 구분한다.
