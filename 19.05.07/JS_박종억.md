17장 이벤트 다루기
========
<pre>
클라이언트 측 프로그램은 비동기식 이벤트 주도 프로그래밍 모델을 사용한다.

이벤트 타깃  -- Window, Document, Element 객체 등
이벤트 핸들러, 이벤트 리스너

애플리케이션은 웹브라우저에 이벤트 다입과 이벤트 타깃을 지정하여 이벤트 핸들러 함수를 등록한다.

이벤트 객체 -- 특정 이벤트와 연관된 객체로, 해당 이벤트의 상세 내용을 담고 있다.

이벤트 객체의 구성은? type 프로퍼티, target 프로퍼티 가 존재

이벤트 전파 == 이벤트가 발생했을 때, 브라우저가 이벤트 핸들러를 실행시킬 객체를 결정하는 과정이다.
-->  이벤트가 발생한 요소에서 시작하여, 이벤트가 문서 트리를 타고 올라가는 현상을 '버블링' 이라 한다.

이벤트 캡처링 == 실제 타깃 객체에 이벤트가 전달되기 전에 상위 컨테이너 요소에 정의된 이벤트 핸들러가
                           이를 가로 막거나 잡아낼 수 있다.


몇가지 이벤트는 '기본동작' 을 가지고 있다.

이벤트 '취소(canceling)' 도 있다.
</pre>


### * 17.1 이벤트 타입

### * 17.1.1 오래된 이벤트 타임
<pre>
폼 관련 이벤트
	* submit
	* reset
	* focus
	* blur
	* change
	* focusin
	* focusout
	* input

창 관련 이벤트
  * load - 브라우저에 모든 자료가 띄어진 후
	* DOMContentLoaded
	* readystatechange
	* unload
	* beforeunload
	* resize
	* scroll

마우스 관련 이벤트
	* clientX
	* clientY
	* button
	* which
	* altKey
	* ctrlKey
	* metaKey
	* shiftKey
	* detail
	* click
	* dbclick
	* contextmenu - 오른쪽 마우스 클릭
	* mouseover
	* mouseout
	* relatedTarget - 마우스 이동 과정과 관련된 다른 요소를 나타낸다.
	* mouseenter
	* mouseleave
	* mousewheel
	* DOMMouseScroll

키보드 관련 이벤트

	* keycode
	* altkey
	* ctrlKey
	* metaKey
	* shiftKey
	* keydown
	* keyup
	* keypress

</pre>

### * 17.1.2 DOM 관련 이벤트
<pre>
KeyboardEvent - keypress 대체
textinput - keypress 대체
</pre>

### * 17.1.3 HTML5 관련 이벤트
<pre>
<audio>
<video>

invalid 이벤트 - 유효성 검사에 실패한 폼요소에서 발생
offline 이벤트
online 이벤트
message 이벤트
loadstart 이벤트
progress 이벤트
loadend 이벤트
storage 이벤트
</pre>

### * 17.2 이벤트 핸들러 등록하기
<pre>
1.이벤트 타깃이 되는 객체나 문서 요소에 프로퍼티를 지정하는 방법
	* 자바스크립트 코드에서 이벤트 핸들러 프로퍼티를 지정
	* HTML에서 문서 요소에 프로퍼티와 상응하는 속성을 지정
  
2.객체나 요소의 메서드에 이벤트 핸들러를 전달하는 방법
	* addEventListener( )
	* attachEvent( )
</pre>

### * 17.2.1 이벤트 핸들러 프로퍼티로 핸들러 지정하기
<pre>

가장 간단한 방법은? 원하는 이벤트 핸들러 함수를 이벤트 타깃의 프로피티에 지정하는 것---> 모두 소문자로 쓴다.
window.onload = function( ) {
    // <form> 요소 찾기
    var elt = document.getElementById("shipping_address");
    // 폼이 전송되기 전에 실행될 이벤트 핸들러를 등록한다.
    elt.onsubmit = function( ) { return validate(this); }
}
===> 이것의 단점은 한가지 밖에 등록이 안된다.
여러 이벤트를 등록할려면 addEventListener( ) 를 사용해라
</pre>
### * 17.2.2 이벤트 핸들러 속성으로 핸들러 지정하기
<pre>
< button onclick="alert('Thank you');">Click Here</button>
</pre>

### * 17.2.3 addEventListener( )
<pre>
세개의 전달인자가 있다.
첫째는 등록할 이벤트명, 둘째는 이벤트 타입이 발생할 때 실행할 함수,
셋째는 불리언값  --> 일반적으로 false, 만약 true를 넘기면 , 이벤트 캡처링 방식을 사용
false 면 버블링으로
예 )
<button id="mybutton">Click me</button>
<script>
       var b = document.getElementById("mybutton");
       b.onclick = function( ) { alert("Thanks for clicking me!"); };
        b.addEventListener("click", function( ){ alert("Thanks again!); }, false};
</script>

removeEventListener --> 함수 제거
</pre>

### * 17.3 이벤트 핸들러 호출

### * 17.3.1 이벤트 핸들러의 전달인자

### * 17.3.2 이벤트 핸들러 컨텍스트
<pre>
이벤트 핸들러 안에서 this 는 이벤트 타깃이 된다.
</pre>
### * 17.3.3 이벤트 핸들러의 유효범위
### * 17.3.4 핸들러의 반환 값
### * 17.3.5 호출 순서
### * 17.3.6 이벤트 전파
<pre>
버블링
캡처링
</pre>

### * 17.3.7 이벤트 취소
<pre>
preventDefault( ) 
window.event returnValue 프로퍼티를 false 로 지정
예)
function cancelHandler(event) {
    var event = event || window.event;
    if( event.preventDefault ) event.preventDefault( );
    if( event.returnValue ) event.returnValue = false;
    return false;
}
</pre>

### * 17.4 문서 로딩 관련 이벤트
<pre>
568 페이지 참고
</pre>

### * 17.5 마우스 관련 이벤트 
<pre>
569p 참고
</pre>

### * 17.6 마우스 휠 관련 이벤트
### * 17.7 드래그 앤 드롭 관련 이벤트
<pre>
580p 참고
</pre>

### * 17.8 텍스트 관련 이벤트
<pre>
586p 참고
</pre>

### * 17.9 키보드 관련 메서드
<pre>
590p 참고
</pre>
