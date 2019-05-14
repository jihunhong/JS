## 1.1 자바스크립트 코어
***


**함수도 객체의 프로퍼티로 사용할 수 있다.**
이때 함수는 객체의 메서드가 된다.
```js
var a = [];
a.push(1,2,3); // push 메소드
a.reverse(); // reverse 메소드
```

**클래스 예제**
Point 클래스의 인스턴스 객체는 r() 메서드를 갖는데, 이 메서드는 좌표의 원점으로부터의 거리를 계산한다.
```js
function Point(x,y) {
  this.x = x;
  this.y = y;
}

var p = new Point(1,1);

Point.prototype.r = function() {
  return Math.sqrt(
    this.x * this.x +
    this.y * this.y
  );
}

//Point 객체 p는 메서드 r()을 상속받는다.
p.r();
```
<br>


## 2.어휘구조
---
#### 2.1 문자 집합

- **대소문자를 구분**하는 언어(HTML은 대소문자를 구분하지 않는다.)

- **공백 문자** : 일반적인 공백 문자 뿐 아니라, 탭, 수직 탭, 폼 피드, 줄바꿈 없는 공백, 바이트 순서 표식, 유니코드 카테고리 Z에 포함된 문자들도 공백으로 인식한다.

- **줄바꿈 문자** :라인피드, 캐리지 리턴, 줄바꿈 문자, 문단 구분자 문자를 줄바꿈 문자로 인식한다.

- **유니코드** 를 사용할 수 있도록 16비트 유니코드 글자를 표현할 수 있는 일련의 **6자리 ASCII 문자열 시퀀스** 를 정의하고 있다.


- 자바스크립트 인터프리터가 해석한 소스코드는 이미 정규화 과정을 거쳤다고 가정하므로, 식별자나 문자열 또는 정규 표현식에 대한 **정규화** 는 더 이상 발생하지 않는다.





**Q.유니코드란?**
>유니코드(Unicode)는 전 세계의 모든 문자를 컴퓨터에서 일관되게 표현하고 다룰 수 있도록 설계된 산업 표준이며, 유니코드 협회(Unicode Consortium)가 제정한다.

#### 2.4 식별자와 예약어

- **식별자의 시작은 알파벳 , 밑줄(_), 달러($)**
이어지는 문자들은 알파벳, 숫자, 밑줄, 달러
<br>

- 유니코드 문자 집합에 속한 문자와 숫자를 포함 시킬 수도 있으므로 변수 이름을 비영어권 언어로 작성하거나, 수학기호를 사용할 수도 있다.

* **예약어**

  ```
  break     delete   function    return  typeof
  case      do       if          switch  var
  catch     else     in          this    void
  continue  false    instanceof  throw   while
  debugger  finally  new         true    with
  default   for      null        try
  ```
  **ECMAScript 5 에서 추가된 예약어**
  ```
  class  const  enum  export  extends  import  super
  ```
  **엄격모드에서 사용 불가**
  ```
  implements  let      private    public  yield
  interface   package  protected  static
  arguments eval
  ```

* 두 문장이 각각 다른 줄에 작성되어 있을 경우, 첫 행의 세미콜론은 생략이 가능함
  ```js
  a = 3
  b = 4;
  ```
