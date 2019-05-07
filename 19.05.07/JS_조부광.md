### &#128570; Window 객체 (CHAPTER.14)

  ___window 객체?___

    클라이언트 측 자바스크립트 프로그램의 전역객체

    window객체의 프로퍼티와 메서드에 대해서 알아보자.


#### 1. 타이머

###### setTimeout() , setInterval()
지정한 시간이 흐른 다음 호출할 함수를 등록한다.
(중요한 전역 함수여서 window객체의 메서드로 정의 됨. 특정 목정을 가진 함수일 뿐 실제 브라우저 창과는 아무 관련 없음)
- setTimeout(함수명) : 일정 시간이 흐른 후 실행할 함수 지정 ( 밀리초 )
- setInterval(함수명, 시간) : 지정한 밀리초 주기마다 반복적으로 함수 호출
  ```
  setInterval(updateClock, 60000); 60초 마다 updateClock() 호출
  ```
  반환값( 타이머ID )을 clearTimeout(), clearInterval() 메서드의 인자로 사용하면 계획된 함수의 실행 취소 가능
  <br/>
  ![](./image/timer01(ch14).png)
  <br/>
    - IE 제외 다른 브라우저 및 HTML5 명세에서는 세번째 인자를 전달할 수 있고, 타입 상관없이 실행할 함수에 전달됨.
    - 0밀리초로 setTimeout()을 해도, 즉시 실행되지 않는다.
      대신, 먼저 예약된 이벤트 핸들러가 실행된 다음 '가능한 빨리' 호출 되도록 큐에 저장됨.

#### 2. Location과 Navigation

- Window 객체의 location 프로퍼티는 현재 창에 표시된 문서의 URL을
  나타내는 Location 객체와 연결되어 있음.
- Document 객체의 location 프로퍼티도 동일한 Location 객체와 연결되어 있음.
- 이 객체에는 해당 창에 새 문서를 불러들이는 메서드도 존재함.

```js
window.location === document.location // 항상 ture
```

###### URL 파싱

- Location 객체의 href 프로퍼티에는 완전한 url 문자열이 저장되어 있음.
  location을 사용하면 내부적으로 toString() 메서드를 호출하므로
  location.href 보다 그냥 location을 사용하여 url 문자열을 얻을 수 있다.
  <br/>
- Location 객체의 프로퍼티 중 URL의 다양한 특성을 나타내는 property  : URL분해 프로퍼티
  ```
  protocol, host, hostname, port, pathname, search, hash
  ```    
    - search : url 일부 중 ? 로 시작되는 부분 반환
https://www.google.com/search?client=firefox-b-d&ei=52XRXJb5KKOmmAXF0oTYCg&q=mysql&oq=mysql&gs_l=psy-ab.3..0i131j0j0i131j0l7.1405.2323..2513...0.0..0.119.539.0j5......0....1..gws-wiz.Tpz30gaJoR8
![](./image/location01(ch14).png)

  - hash : url 의 '부분식별자' 반환 (#마크의 뒷부분 - 요소의 id)


###### 새문서 불러오기 : 지정한 URL에 해당되는 분서를 보여주는 메서드들
`Location객체의 assign() , replace(), reload()`
  - replace() : 새 문서를 불러오기 전 히스토리에서 현재 문서 제거. assign보다 많이 사용

`location 프로퍼티 직접 지정`
  - location = "https://www.oreilly.com" //절대경로
  - location = "pate2.html" //상대경로
  - location = "#top"
  //문서상에 top이라는 id를 가진 요소가 없다면 분서의 최상단으로 이동

`url분해 프로퍼티도 쓰기 가능`
  - https://search.naver.com/search.naver 에서
  location.search = "?where=nexearch&query=어비스+뜻&sm=top_lve&ie=utf8"
  하면 '어비스 뜻' 검색 결과 페이지로 이동

#### 3. 브라우징 히스토리
***History 객체 ?***
    브라우징 히스토리를 문서와 문서 상태 목록으로 저장
- history.length : 브라우징 히스토리 목록 개수 (url 접근은 보안이유로 허용X)  
- history.back : 뒤로가기
- histoy.forward : 앞으로 가기
- history.go(n) : 앞으로( 양수 인자 ) 혹은 뒤로( 음수 인자 ) 몇 단계 씩 이동할지 인자로 받음

_SPA(single page application) 는 새 문서를 불러오지 않고도 내용을 동적으로 변경할 수 있는데 해당 기능을 위해 html5는 두 가지 기술을 표준화 했다. => 22.2절에서 다룸_

#### 4.브라우저와 화면정보
_브라우저가 어떤 환경에서 실행되고 있는지
 데스크톱 내의 어느 위치에 표시되고 있는지에 대한 정보_

###### Navigator 객체
    Window객체의 navigator프로퍼티는
    브라우저 제조사와 버전정보 포함하고 있는 Navigator 객체를 가리킴
    ( IE - clientInformation )
    실행 중인 브러우저에 관한 4개의 프로퍼티를 제공   
- appName : 브라우저 전체 이름
- appVersion : 제조사와 버전정보
- userAgent : 브라우저가 user-agent HTTP 헤더로 보낸 문자열 ( 보통 appVersion 내용 포함 )
- platform : 브라우저를 실행하고 있는 운영체제 및 하드웨어

![](./image/navigator01(ch14).png)

    제조사 및 버전정보 관련 외 프로퍼티,메소드
- onLine : 브라우저의 네트워크 접속 여부
- geolocation : 지리적 위치 정보를 확인하는 api 를 정의
- javaEnabled : 자바 애플릿을 실행할 수 있는지 반환 (비표준)
- cookiesEnabled : 영구적인 쿠키 저장 가능 여부반환 (비표준)

###### Screen 객체
    사용자의 디스플레이와 사용 가능한 색상 수에 관련된 정보 제공
- width, height : 디스플레이 픽셀 크기
- availWidth, availHeight : 실세 사용 가능 영역의 디스플레이 크기 (상태표시줄 제외)
- colorDepth : 화면의 픽셀당 표시 가능 피트 수

#### 5. 대화상자
- alert() : 메세지 보여주고 확인 기다림
- confirm() : 메세지 보여주고 확이이나 취소 버튼 누를 때까지 대기한 다음 boolean값 반환
- prompt() : 메세지 보여주고 사용자의 입력을 받은 다음 해당 문자열 반환

※ 사용하긴 쉽지만, 사용하지 않는 편이 더 좋은 소프트웨어 설계라고 한다.
- showModalDialog() : HTML로 구성된 내용을 포함한 형식의 대화상자 출력, 전달인자 넘김 및 값 반환도 가능


#### 6. 오류처리
___window.onerror(함수)___
처리되지 않은 예외가 발생했을 때 호출스택까지의 모든 경로와
브라우서의 콘솔에 출력되는 오류메세지가 전달되는 이벤트 핸들러.
( 세개의 인자를 받는다 : 메세지, url, line )

#### 7. Window 프로퍼티의 문서요소
    HTML 문서상의 어떤 요소에 id 속성을 부여했고
    Window 객체에 같은 이름의 프로퍼티가 이미 존재하지 않는다면,
    이 요소의 id속성을 이름으로 하고 해당 요소를 나타내는 HTMLElement 객체를
    값으로 가지는 포로퍼티가 window객체에 제공된다.

#### 8. 다중 창과 프레임
