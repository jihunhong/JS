# MyBatis

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [MyBatis](#mybatis)
	* [DataSource](#datasource)
* [🌟 MyBatis에 실효성과 목적에 관해](#mybatis에-실효성과-목적에-관해)

<!-- /code_chunk_output -->

> Mapper 인터페이스를 통해 지원하며 별도의 XML파일에 쿼리를 지정하여
DAO에서의 DB접근 수단을 SqlSession 인터페이스를 구현한 클래스 객체를 DI 받아서 사용한다.

## DataSource
> DAO에서 Connection 객체를 매번 새로 생성하는것의 비효율성, 성능저하 때문에 사용하는 Bean.

- Pooling : Connection Pool에서 요청에 따라 꺼내고 돌려받아 저장

`✔️ Spring에서는 DataSource를 하나의 독립된 빈 객체로 만들길 권장하고 있다`

![캡처](https://i.imgur.com/GPMSjMn.png)

✔️ `📝configuration.xml`에 Mapper경로와 이름이 정의되어있고 지정한 Mapper.xml파일에 XML의 문법을 따르는
동적 형태의 쿼리문을 전달받아 DAO내에서 수월하게 사용하는것이다.

# 🌟 MyBatis에 실효성과 목적에 관해
MyBatis의 의의는 이것에 있다. 기존의 JDBC코드에서의 Connection객체의 반복적인 사용을 방지하는것과 `Service` `Controller` `DAO` 와 같은 3-Layer로 이루어진 스프링 구조를 객체지향의 목적성에 맞게 느슨한 의존성을 따르게하는것이다.
