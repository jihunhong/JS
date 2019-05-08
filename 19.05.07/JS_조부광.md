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
  <br/>
  ![](./image/document01(ch14).png)

#### 8. 다중 창과 프레임

    + 한 탭은 다른 탭과 단절된 독립적인 window객체 갖는다.
    + 하나의 탭에서 다른 탭이 존재하는지 알 수 없고,
    다른 탭들의 Window 객체나 문서 내용을 조작할 수 없다.
    + 그러나, 모든 창이 그런건 아니다.
    하나의 탭에서 스크립트로 새창 혹은 새 탭을 열 수 있고,
    그런 경우 원래의 창과 다른 창, 다른 창의 문서와 상호작용 할 수 있다.

###### 창 열고 닫기
- open(인자1, 인자2, 인자3, 인자4) :  새 웹브라우저 창 혹은 탭을 열 수 있다.
  ```
   - 인자 1 : 새 창에 표시될 문서의 url  (생략되면 about:blank)
   - 인자 2 : 창의 이름을 가리키는 문자열 (생략되면 \_blank)
      ※ 하나의 창이 다른 창 내에 중첩되면 예약된 이름인 "_top"으로 최상위 창을,
      "_parent"로 직계 부모 창을 사용할 수 있다.  
   - 인자 3 : 새로 열릴 창의 크기와 기능 속성 목록을 콤마로 구분한 문자열  (비표준)
   - 인자 4 : boolean값 (두번째 인자에 저정한 이름의 창이 이미 존재하는 상황에서 유용)
      - true : 현재 창의 브라우징 히스토리를 인자1 url로 교체
      - false : 새 브라우징 히스토리 생성
  ```
  ```js
  var w = window.open(
              "smallwin.html" ,
              "smallwin",
              "width=400, height=350, status=yes, resizable=yes");
  ```    
- open() 메서드의 반환 값은 해당 url을 가리키는 window 객체
  ```js
  var w = window.open();
  w.alert('테스트');
  w.location = "https://naver.com"
  w.close();
  ```
- close() : 창(혹은 탭) 닫기.
  Document객체의 close()와 구분되도록 명시적으로 window.close() 라고 쓴다.
  - 해당 자바스크립트 코드가 실행되고 있는 창 내에서만 자동으로 닫기를 허용하고, 최상위 창이나 탭을 닫으려 하면 작동하지 않는다.
  - 창이 닫힌 후에도 Window객체는 존재하며, closed 프로퍼티 값이 true, document는 null 이 된다.

###### 프레임 간의 관계
- open() 메서드로 새롭게 생선된 Window 객체는
  원본 창 객체를 가리키는 opener 프로퍼티를 가지고 있다. 혹은 parent 프로퍼티로도 상위 객체 접근 가능.
  이런 식으로 서로 참조 가능, 상대방의 메서드를 호출할 수도 있다.

  ```js
  프레임에서 프레임의 Window객체를 참조할때 : parent
  parent.history.back();
  중첩에 상관없이 최상위 Window객체를 참조할 때 : top
  top.history.back();
  (최상위 일 때는 자기 자신을 가리킴)
  ```

- 하위 프레임 참조하기
   ```js
   //자식 프레임 참조
   <iframe id="f1"></iframe>
   var ifEle = document.getElementById("f1");
   var chileFrame = ifEle.contentWindow; // 해당 프레임의 Window객체를 가리킴
   //자식 프레임 목록인 frames 프로퍼티
   frames[0] // 첫 번째 자식 프레임
   frames[1].framse[2] // 두 번째 자식 프레임 내부에 존재하는 세 번째 자식 프레임
   // <iframe>에 이름을 지정했다면 이름으로도 찾을 수 있다.
   <iframe name="f1"></iframe>
   frames.f1
   ```


###### 창들과 상호작용하는 자바스크립트
  _위에 설명한 바와 같이 프레임들이 서로 참조할 수 있다면, 상호작용도 가능하다_
  웹 페이지에 A와 B라는 이름의 <iframe> 요소가 존재 할 때
  ```js
    //프레임A
    var i = 3;
    //window객체의 전역변수임
    window.i;  // 3
    //프레임B
    parent.A.i = 4; //A에 있는 변수의 값을 바꿀 수 있다.
    function f(){
      console.log('this is f()');
    }
    //프레임A
    parent.B.f(); //A에서 B의 함수 호출 가능
  ```

- 각 창에는 생성자와 prototype 객체의 독립적인 복사본이 존재한다.
  ex) 각 창에는 각자의 String() 생성자와 String.prototype 객체를 갖고 있다.
  따라서, 현재 창에서 문자열을 조작하는 새로운 메소드를 정의하고 String.prototype 객체에 할당했다고 해도, 다른 창에서는 그 메소드를 사용할 수 없다.
