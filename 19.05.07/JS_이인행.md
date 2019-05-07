# CSS (캐스케이팅 스타일 시트)
- HTML 문서의 시각적인 표현을 명세하는 표준
- 글꼴과 색상, 여백, 들여쓰기, 테두리, 문서 요소의 위치 등을 지정할 수 있도록 고안되었다.
- 자바스크립트로도 조작할 수 있기 때문에, 클라이언트 측에서 매우 중요하다.

### 개요
- style 속성으로 글꼴과 색상, 여백, 테두리, 배경 이미지, 문자 정렬, 요소 크기, 요소 위치 등에 대한 프로퍼티를 정의한다.
```css
.foo{
	margin-left: 10%; /* 페이지 넓이의 10% 만큼 왼쪽 여백을 둔다. */
	font-weight: bold; /* 폰트를 굵게 한다. */
	text-indent: .5in; /* 0.5인치 만큼 들여쓴다 */
	font-size: 12 pt; /* 글꼴 크기를 12포인트로 지정한다. */
}
```
- 스타일 적용을 위해서는 css 파일을 따로 만들어 불러오는 방법과, 개별 HTML 요소의 style 속성을 설정하는 방법이 있다.
```js
<p style="margin: 20px; border: solid red 2px;">
	이 문단은 여백을 더 많이 가지고,
	빨간 테두리로 싸여 있다.
```

### 단축 프로퍼티
- 스타일을 공통적인 대상에 적용하기 위해, 단축형 스타일 프로퍼티를 사용하면 여러 가지 스타일 값을 조합할 수 있다.
- margin-left, margin-right 식으로 하나하나 지정할 수 있지만, 단축 프로퍼티를 사용하면 한 줄로 설정할 수 있다.
- padding, margin은 top, right, bottom, left 순으로 진행된다.
```css
.foo{
	margin: 1px 2px 3px 4px;
	padding: 10px; /*네 면 모두 패딩이 10px씩 적용된다.*/
```
- margin과 padding의 차이는 다음과 같다.
![123](https://user-images.githubusercontent.com/20277647/57292505-d2aaf700-70fc-11e9-848d-5321c70939ff.PNG)

### 비표준 프로퍼티
- 브라우저마다 비표준 프로퍼티를 위해 접두사를 붙인다.
- 이러한 비표준 프로퍼티는 미래의 표준 속성 개발 목적으로 사용하기도 한다.
- 파이어폭스는 -moz-, 크롬은 -webkit-, IE는 -ms-를 사용한다.
```css
.foo{
	border-radius: 10px; /*최신 브라우저를 지원*/
	-moz-border-radius: 10px; /* 파이어폭스 3.x 버전을 지원*/
	-webkit-border-radius: 10px; /*사파리 3.2와 4를 지원*/
```

### 주요 CSS 프로퍼티
- position: 요소에 적용할 위치 타입을 지정한다.
  - static: 기본값으로 top, left 등의 프로퍼티로 위치를 변경할 수 없다.
  - absolute: 해당 요소를 감싸고 있는 요소를 기준으로 상대 위치를 지정할 수 있다. document 요소를 기준으로 위치가 지정되며, 다른 모든 요소로부터 위치가 독립적이고, 다른 static 요소에 영향을 받지 않는다.
  - fixed: 요소의 위치를 브라우저 창을 기준으로 지정할 수 있다. 문서를 스크롤해도 항상 고정된 위치에 나타난다.
  - relative: 정상적인 문서 배치에서 요소에 할당된 공간을 그대로 두고, 해당 공간을 기준으로 지정된 위치 값만큼을 '밀어내어' 요소를 출력한다.
- top, left: 요소의 왼쪽과 위쪽 가장자리의 위치를 지정한다.
- bottom, right: 요소의 오른쪽과 아래쪽 가장자리의 위치를 지정한다.
- width, height: 요소의 크기를 넓이와 높이로 지정한다.
- z-index: 요소의 위치가 3차원적으로 다른 요소와 겹쳤을 때 적용할 우선순위를 지정한다.
  - 기본 값은 0이며, 양수나 음수 값을 지정할 수 있다.
  - z-index 값이 같은 요소가 겹치면, 마지막에 배치된 요소가 더 위에 그려진다.
  - position이 static으로 지정된 요소들은 항상 겹쳐지지 않게 배치되므로, 평소에는 신경쓸 필요 없다.
- display: 요소가 보이는 방식을 지정한다.
  - block으로 지정시 요소가 보이게 된다.(디폴트)
  - none으로 지정시 요소가 보이지 않고, 공간도 존재하지 않게 된다.
- visibility: 요소의 가시성을 지정한다.
  - visible로 지정시 요소가 보이게 된다.
  - hidden으로 지정시 요소가 보이지 않지만, 공간은 차지하는 상태가 된다.
- overflow: 요소의 크기가 주어진 공간보다 클 때 처리할 방식을 지정한다.
  - visible: 내용이 요소 영역을 넘쳐 바깥쪽에도 그려진다. 기본 값이다.
  - hidden: 요소 영역에서 넘치는 부분은 잘려나가 보이지 않는다. 즉, 크기와 위치 속성에 지정된 영역을 벗어나서 그려지는 내용은 없다.
  - scroll: 요소에 영구적인 수평, 수직 스크롤바가 존재한다.
  - auto: 요소의 내용이 크기보다 클 때만 스크롤바가 나타나게 한다.
- margin, padding: 요소와 관련된 공간을 정의한다.
- border: 요소와 관련된 테두리를 정의한다.
  - border-bottom 등으로 위치를 지정한다.
  - border-top-width와 border-left-color 같이 한쪽 면에 대한 테두리의 굵기 또는 스타일, 색상 등을 지정할 수 있다.
  - border-top-right-radius: 50px; 처럼 모서리를 둥글게 만들 수 있다.
  - 단축 프로퍼티 사용시 굵기, 스타일, 색상 순으로 작성한다.
- background: 색상이나 이미지를 요소의 배경으로 지정한다.
  - background-color를 사용하여 요소의 배경색을 지정할 수 있다.
  - background-image를 사용하여 배경 이미지를 지정할 수 있다.
  - 배경색이나 이미지를 지정하지 않으면, 요소의 배경은 보통 투명하게 지정된다.
- opacity: 요소의 불투명도를 지정한다.

### 인라인 스타일 스크립팅
- 자바스크립트로 조작하기 위해서는 요소를 얻고, 하이푼 부분의 속성을 대문자로 바꿔서 사용한다.
- ex) font-size ---> fontSize, background-color ---> backgroundColor
- 예를 들어 ```요소.style.backgroundColor = "#ffffff";``` 식으로 지정할 수 있다.
- 이러한 인라인 스타일은 주로 요소의 스타일을 설정할 때만 사용하는 편이다.
- 경우에 따라서는 요소의 인라인 스타일을 하나의 문자열로 설정하거나 가져오는 것이 더 쉬울 수도 있다.
```js
// 문자열 s를 요소 e의 style 프로퍼티로 설정한다.
e.setAttribute("style",s);
// 요소 e의 인라인 스타일을 문자열로 가져온다.
e.getAttribute("style");
```

### CSS 클래스 스크립팅
- 어떠한 요소의 클래스를 추가, 삭제, 포함 여부를 자바스크립트로 조종할 수 있다.
- classList는 읽기 전용 프로퍼티 이지만, add remove등을 이용해 동적으로 조종할 수 있다.
  - add : 요소에 해당 클래스를 추가한다.
  - remove : 요소에 해당 클래스를 삭제한다.
  - contains : 해당 클래스가 있는지 여부를 확인한다.
  - toggle : 두 번째 인자의 true, false에 따라 클래스를 추가한다.
```js
    <span class="test">
        그림자 보임
    </span>

    var el = document.getElementsByClassName("test")[0];
    
    el.classList.add('foo1','foo2','foo3'); // test foo1 foo2 foo3
    el.classList.remove('test'); // foo1 foo2 foo3
    console.log(el.classList.contains('foo1')); // true
    el.classList.toggle( 'foo4', true ); // foo1 foo2 foo3 foo4
    el.classList.toggle( 'foo5', false ); // foo1 foo2 foo3 foo4
```

### 참고할만한 페이지
- CSS 프레임워크
  - https://anymod.com/mods
  - https://getbootstrap.com/docs/4.3/examples/
- CSS 애니메이션 관련
  - https://jqueryui.com/
  - https://github.com/juliangarnier/anime
  - http://imagehover.io/#download
  - https://kushagragour.in/lab/hint/
- CSS 예제, 사용법, 호환 브라우저 및 버전 등 모음
  - https://caniuse.com/