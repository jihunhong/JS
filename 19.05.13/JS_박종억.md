JQuery
======================
### * 특징
```
안정적이다.
문서 요소를 찾기 쉽게 만들어 준다.
찾은 후에 내용 추가나 HTML속성과 CSS프로퍼티 수정, 이벤트 핸들러 정의, 애니메이션 적용 같은 작업을 할 수 있다.
Ajax 유틸리티와 일반적인 객체와 배열작업을 도울 범용 유틸리티 함수들을 가지고 있다.
```
### * <div> 요소를 질의하는 방법
```
예) var divs = $("div");

예) $("p.details").css("background-color", "yellow").show("fast");

예) $(".clicktohide").click(function( ) { $(this).slideUp("show") } );
 
```

### * 19.1.1 jQuery( ) 함수
```
호출 방법
1.css선택자를 전달인자로 넘겨서 $()를 호출하는 방법이 일반적이다.
2.Element, Document, Window 객체를 전달인자로 넘겨서 $()를 호출하는 방법이다.
3.HTML 텍스트 문자열을 전달인자로 넘겨서 $()를 호출하는 방법이다.
예) var img = $("<img/>",
                            {src:url,
                            css: {borderWidth:5},
                            click: handleClick
                            });
4.$()에 함수를 전달인자로 넘겨서 실행하는 방법이다.
jQuery안에 익명함수를 쓰는 것이 일반적이다.
예) jQuery(function() { 
    // jQuery 코드는 여기에 
});
```
### * 19.1.2 조회와 조회 결과
```
jQuery 객체는 배열과 유사해서 length 프로퍼티가 있다.
진짜 배열로 바꿀려면 toArray( ) 메서드 호출

// document.body 안에 있는 모든 <script> 요소를 찾는다.
var bodyscripts = $("script", document.body);
bodyscripts.selector // "script"
bodyscripts.contexts // document.body
bodyscripts.jquery // "1.4.2"

$()와 querySelectorAll( ) 과 유사하다.

// 모든 헤딩(heading) 태그를 찾아서 id를 담고 배열로 변환 후 정렬한다.
$(":header").map(function(){ return this.id; }).toArray( ).sort();
```
### * 19.2 jQuery에서 값 가져오고 지정하기

### * 19.2.1 HTML 속성을 가져오고 지정하기
```
$("form").attr("action");
```
### * 19.2.2 CSS 속성을 가져오고 지정하기
```
$("h1").css("font-weight");
```

### * 19.2.3 CSS 클래스 가져오고 지정하기
```
// HTML 요소에 CSS 클래스 추가하기
$("h1").addClass("hilite");

// " 제거하기
$("p").removeClass

// CSS 클래스 토글하기
$("tr:odd").toggleClass("oddrow"); // oddrow 클래스가 없으면 추가하고, 있으면 제거한다.
$("h1").toggleClass("hilite", true); // addClass( ) 처럼 작동한다.

// " css 클래스 확인하기
$("p").hasClass("first")     == is()와 같다.
```
### * 19.2.4 HTML 폼 요소의 값을 가져오고 지정하기
```
$("#surname").val( )   
$("input:radio[name=ship]:checked").val()
```
### * 19.2.5 HTML 요소의 내용 가져오고 지정하기
```
예 ) var title = $("head title").text()    //문서 제목 가져온다.
var headline = $("h1").html( ) // 첫번쨰 <h1> 태그이 html을 가져온다.
```
