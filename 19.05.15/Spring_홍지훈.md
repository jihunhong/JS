# 📖 Spring Basic


<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [📖 Spring Basic](#spring-basic)
	* [🍃  Spring](#spring)
		* [🤾 주요 전략](#주요-전략)
		* [📦 Container](#container)
	* [🔮 POJO](#pojo)
	* [📢 🔍DispatcherServlet](#dispatcherservlet)
		* [💨 동작 방식](#동작-방식)

<!-- /code_chunk_output -->
<!--toc-->

## 🍃  Spring
> POJO 특정 클래스를 상속하거나 인터페이스를 구현하지 않는 평범한 자바 클래스(느슨한 결합)
를 이용하며 단순하지만 EJB에서 제공하는 고급 기술을 제공한다.

✔️ EJB의 무거웠던 단점을 보완해 나온 Spring은 경량급 프레임워크로 RELEASE되었다.

### 🤾 주요 전략
	1. POJO를 이용한 가볍고(lightweight) 비침투적(non-invasive) 개발

	2. DI와 인터페이스 지향을 통한 느슨한 결합도(loose coupling)

	3. Aspect와 공통 규약을 통한 선언적(declarative) 프로그래밍

	4. Aspect와 템플릿(template)을 통한 반복적이고 상투적인(boilerplate) 코드 제거

### 📦 Container
> 객체관리를 주로 수행하는 그릇으로 빈의 생성과 관계, 사용, 생명 주기등을 관장하는곳.
> 프로젝트내의 수많은 객체들을 생성하기위한 new, getter/setter 들은 객체들끼리 서로 참조되고있다.
이러한 상태를 의존성이 높은 결합도를 띈다라고 표현하는데, 결국 객체지향 프로그래밍에 상반되는 형태를 띄는것이다.
이러한 높은 의존성을 낮추기위해 사용하는것이 **`Spring Container`** 이다.

## 🔮 POJO
> Plain Old Java Object, 객체지향 프로그래밍의 본 목적에 맞게 사용하기위한 특정 환경에
종속되지 않는 간결한 클래스를 말한다.


- Inversion Of Container
 	- 객체의 생성과 사용하는 행위를 담당하는 주체가 Container로 넘어가는것을 말한다.

	<br>

- Dependency Injection
 	- Container 외부에서 생성된 객체를 setter 혹은 생성자를 이용해 특정상황에서 객체끼리 의존하는 상황일때 IoC컨테이너에서 생성된 객체를 넘겨주어 사용 할 수 있게하는것.

	<br>

- PSA (Portable Service Abstraction)
	- 환경의 변화와 관계없이 일관된 방식의 기술로의 접근 환경을 제공하려는 추상화 구조

	<br>

- AOP (Aspect Oriented Programming )
	- 기존에 OOP에서 바라보던 관점을 다르게하여 부가기능적인 측면으로 보았을때 공통된 요소를 추출해 내 재사용하는것. (로깅, 트랜잭션, 보안)



## 📢 🔍DispatcherServlet
> `Servlet Container` (tomcat)에서 들어오는 모든 요청을 프레젠테이션 계층의 제일 앞에둬서
중앙집중식으로 처리해주는 Front Controller.

### 💨 동작 방식
![99F8E4335A06F70930](https://i.imgur.com/oDq5qlG.png)
