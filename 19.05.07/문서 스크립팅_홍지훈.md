![enter image description here](https://cdn-images-1.medium.com/max/800/1*XkHY4KkKDnOdnwW0lIbqjg.png)
# 📖 자바 스크립트 완벽 가이드

## 15장 문서 스크립팅
> 모든 Window 객체에는 Document 객체를 참조하는 document 프로퍼티가 존재한다.
Document 객체는 DOM으로 알려진 문서 객체 모델 이라는 거대한 API의 일부이며 그중 문서 내용을 나타내고
조작하는 역할을 하는 중요한 객체다.

### DOM (Document Object Model)
> DOM은 HTML과 XML문서의 내용을 조작하고 나타내는 기반 API이다.
``` html
<html>

<head>

<title>Hello World!</title>

<meta name="description" content="I am learning to code."/>

</head>

<body>

<h2>Hi There!</h2>

<p>You are all right?</p>

<table>

<tr>

<td>Analytics Nerd</td>

</tr>

</table>

</body>

</html>
```
위 문서를 계층구조인 DOM으로 표현한다면 다음과 같다.

![enter image description here](https://www.optimizesmart.com/wp-content/uploads/2014/05/HTML-DOM-Tree.jpg)


![enter image description here](http://web.stanford.edu/class/cs98si/img/dom_types.png)
`Document` - HTML문서 혹은 XML 문서 그 자체를 뜻하는것
`Element`  - 그 문서의 요소

### 문서 요소 선택

#### id 로 요소 선택하기
```js
var section = document.getElementById("section");
```

<br>
#### Name 으로 요소 선택하기

> 원래 목적은 폼 요소에 이름을 부여하는것, 폼 데이터를 서버로 전송할때 사용.

	❗ id 와는 다르게 유일성을 보장하지 않는다.
```js
var section = document.getElementsByName("section");
```

#### Type으로 요소 선택하기
> 태그의 이름으로 요소를 선택하는것

```js
var span = document.getElementsByTagName("span"); //<span>태그 모두 선택
```
	✔️ list 형태로 반환되기 때문에 인덱스 표기와 같이 사용해서 요소를 선택하는 방법이 자주 쓰인다.

#### CSS Class로 요소 선택하기
```js
var warning = document.getElementsByClassName("warning"); // class속성에 warning이 있는 모든 요소를 찾는다.
```

#### CSS 선택자로 요소 선택하기

```
#nav     // id 값이 'nav'인 요소
div	 // <div> 태그
.warning // class 속성의 값이 warning인 요소
```

`querySelectorAll() `과 `querySelector()` 메서드의 인자로 사용한다.

### 문서 구조와 탐색
> 위의 문법을 사용하여 찾은 요소를 참조해 tree형태로 자식, 부모간의 요소로 이동하며 사용이 가능하다.

- `parentNode` : 객체의 부모
- `childNodes` : 객체의 모든 자식요소 (NodeList)형태로 반환
- `firstChild`/`lastChild` : 자식 노드중 첫번째와 마지막 노트를 의미한다.
- `nextSibling`, `previousSibling` : 한 노드의 이전 혹은 다음의 형제 노드 (문서에 나타난 순서대로 )
- `nodeType` : 노드의 종류 Document는 9 Element는 1 Text는 3 Comment는 8 DocumentFragment노드는 11
- `nodeValue` : Text노드나 Comment 노드의 텍스트 내용
- `nodeName` : 대문자로 바꾼 요소의 HTML 태그명

### 속성
> \<a>태그에 들어있는 href와 같이 속성값을 이용해 나타내는 객체의 프로퍼티값. 이름, 값으로 구성된다.

```js
var image = document.getElementById("myImage");
var imgurl = image.src;
image.id === "myimage";
```

### Non-HTML 속성 조회하고 지정하기
`getAttribute() / setAttribute()`

```js
var image = document.images[0];
var width = parseInt(image.getAttribute("WIDTH"));
image.setAttribute("class", "thumbnail");
```

### Dataset 속성
> HTML5에서 나온 방법으로, 'data-'라는 접두사로 시작하는 소문자 이름의 속성은 문법에 맞는 유효한 속성으로 여긴다. 따라서 이 방법은 HTML유효성을 해치지 않고 추가데이터를 첨부하는 표준 방법을 제공한다.
``` html
<span class="sparkline" data-ymin="0" data-ymax="10"></span>
```

- span 태그에서 담고있는 ymin과 ymax는 각각 js에서의 요소 선택으로 접근했을때의 key:value의 형태로 참조하는것이 가능해진것이다.
<br>
- dataset.x 프로퍼티는 data-x 속성의 값을 의미하는것이다.

❗ dataset 프로퍼티는 HTML요소의 data- 속성과 양방향 접근이 실시간으로 이루어진다는 점에 주의해야 한다

### 요소의 내용

#### HTML일때
> Element 객체의 innerHTML 프로퍼티는 해당 요소의 내용을 HTML 마크업 문자열로 반환한다.
> HTML5에서는 outerHTML이란 프로퍼티도 있다. (참조하는 객체를 포함한 마크업 문자를 반환)

#### 일반 텍스트일때
> textContent 프로퍼티가 이 작업을 위한 표준 프로퍼티로 사용된다.
```js
var para = document.getElementsByTagName("p")[0];
var text = para.textContent;
para.textContent = "Hello World"; // <p>의 텍스트를 수정.
```

✔️ textContent는 지정한 요소의 모든 자손노드중 Text노드를 **모두 뽑아내** 연결해준다.

### 노드의 생성, 삽입, 삭제

- 노드의 생성 (head에 새로운 \<script>를 append한다.)

```js
function loadasync(url){
	var head = document.getElementsByTagName("head")[0];
	var s = document.createElement("script"); //새로운 노드 객체 생성
	s.src = url;
	head.appendChild(s);	// head의 마지막 자식노드 자리에 삽입
}
```

- 노드의 삭제와 교체
`removeChild(n)` : 자식으로 가진 부모 노드에서 실행된다.
```js
n.parentNode.removeChild(n); // 메서드 전달 인자로 삭제할 자식 노드를 넘기고, 부모 노드의 메서드로 호출한다. 문서의 n노드를 삭제하는 코드.
```

```js
n.parentNode.replaceChild(document.createTextNode("[REDACTED]"), n);
// 자식 노드 하나를 삭제하고 새 노드로 교체. 첫번째 인자로 새노드를 생성후, n노드를 넘겨 교체.
```

### `DocumentFragment` 사용하기
> 다른 노드를 담는 임시 컨테이너 역할을 하는 특수 목적의 노드.

✔️ 다른 문서의 일부분이 아닌 Document 노드 처럼 홀로 존재하는 노드. => parentNode가 항상 null이다.
✔️ 하지만 Element처럼, appendChild()와 insertBefore(), replaceChild() 의 전달인자로 넘기면, 해당 객체가 아닌
객체의 자식이 문서에 삽입된다.
```js
function reverse(n) {
	var f = document.createDocumentFragment();

	while(n.lastChild) f.appendChild(n.lastChild);
	//f에 추가하면 n에서는 지워진다.
	//끝으로 
	n.appendChild(f);

}

## HTML 폼
> JS가 있기전인 웹 초창기 부터 존재하던 클라이언트의 중요한 방식.
![캡처](https://i.imgur.com/unvhCUO.png)

### FORM의 프로퍼티
#### `type`
	> 폼 요소의 타입을 구분하기 위한 읽기 전용 문자열. (input, textarea, select)

#### `form`
	> 현재 요소를 담고 있는 form 객체에 대한 읽기 전용 참조 프로퍼티.

#### `name`
	> HTML name 속성에 명시 되어있는 읽기 전용 문자열

#### `value`
> 폼 관련 요소에 포함되거나 출력된 값을 명시한 일고 쓰기가 가능한 문자열.

	- `Text/Textarea`         : 사용자가 입력한 텍스트
	- `<input type="button">` : 버튼에 출력된 텍스트
	- `radio / checkbox`      : value 프로퍼티 사용 불가

	✔️ 폼 전송과 폼관련 요소와 연결된 확정 데이터를 조작하는데 의의가 있는 프로퍼티들이다.

### 폼과 관련된 이벤트 핸들러
