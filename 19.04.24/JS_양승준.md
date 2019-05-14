**자바스크립트(JAVA SCRIPT) 용어 정리 및 함수 간략 정리**
- HTML 페이지에서 사용자 반응을 처리하는데 사용한다. (클라이언트 단 처리)
- 자바스크립트는 컴파일 하지 않고 한 줄씩 읽어가며 실행한다 ↔ 자바의 경우 컴파일 후 클랙스 파일을 실행
- 처리 속도가 빠르다. 하지만 실행 처리 완료를 고려하지 않고 계속 내려감 (순서 고려하여 프로그래밍을 하는 게 중요!)
- 디버깅을 위해 chrome에서 'F12' 개발자 모드 사용.

- HTML5에서는 script  태그에 type 속성을 적지 않음.

** 식별자 만들 때의 규칙**
- 생성자 함수의 이름을 대문자로 시작
- 변수와 인스턴스, 함수, 메서드의 이름은 항상 소문자로 시작
- 식별자가 여러 단어로 이루어지면 각 단어 첫 글자는 대문자

- 문자열은 " "와 ' ' 모두 사용 가능 (but ' ' 사용 권장)

**이스케이프 문자 : 특수한 기능을 수행하는 문자**
```
"This is \"String\"";
'안녕 \n 하세요'  // 줄바꿈
```

**문자열 비교 : 국어사전의 앞쪽에 있을수록 값이 작음**
- ex) '가방' > '하마' > false

**자료형 확인 시 typeof() 연산자 사용**
- ex) typeof(273) → Number

**undefined 자료형**
- 존재하지 않는 것
- 변수를 선언했지만 초기화하지 않았을 때


**일치 연산자( === , !+= )**
- 자료형 비교
- ex) 0 == false → true
  0 === false → false

- 어떤 종류의 자료형도 배열이 될 수 있다.
var array = [273, 'String', true, ...];

- for in 반복문
for(var i in Array){
   ...
} // Array의 0번째 요소부터 차례로 변수 i에 할당

**익명함수(이름이 없는 함수)**
```js
var func = function(){};  // 호출 시 func();
```

**선언적 함수**
```js
function func(){};
```

**가변 인자 함수 : 매개 변수의 개수가 변할 수 있는 함수**
* arguments 객체를 사용할 가변 인자 함수.
// arguments 1. 자바스크립트 내부 변수의 기본으로 제공 2. 매개변수의 배열
```js
function sumAll(){
   var output = 0;
   for(var i = 0; i < arguments.length; i++){
      output += arguments[i];
   }
   return output;
}
```
//호출
alert(sumAll(1, 2, 3, 4, 5);

- 콜백함수
ⓐ 매개변수로 전달하는 함수
ⓑ 어떤 이벤트가 발생한 후 수행될 함수
function func(callback){
   callback();
}  // func 함수 호출 시 callback 함수 호출 됨

- 함수의 매개변수와 리턴 값은 선택적 사용

- 숫자 변환 함수
Number() 함수는 숫자로 바꿀 수 없으면 NaN로 변환
ParseInt(String)
ParseFloat(String)   // 변환할 수 있는 부분까지 모두 숫자로 변환
ex) parseInt('1000원') → 1000
Number('1000원') → NaN

```js
var person = { // 객체 생성 방법1
   name : '순남2'
   eat : function(food){}
   // eat() 메서드
}  // 호출은 person.eat() 으로 가능
```
* 용어 정리
요소 : 배열 내부에 있는 값
속성 : 객체 내부에 있는 값
매서드 : 객체의 속성 중 함수 자료형인 속성

- 객체의 속성 출력은 for in 문 사용

- 객체의 동적 속성 추가 / 제지 ( 객체 생성 방법 1)
```js
var student = {};  // 객체 생성
student.name = '순남2'; // delete student.name; 삭제
student job = '학생';
```

**생성자 함수**
```js
function Student(name, korean, math) { // Student의 S의 경우 대문자이다.
   this.name = name;
   this.korean = korean;
   this.math = math;
}
```

**객체 생성 방법 2**
```js
var student = new Student('순남2', 100, 100);
```

- new 키워드를 사용하지 않은 일반 함수는 window 객체를 공간으로 갖는다 (this → window 객체)

- 생성자 함수를 사용한 객체 배열 생성
```js
var student = [];
students.push(new Student('순남2', 100, 100);
```
* 일반함수에서는 this 사용 안 함(내장 객체는 건들면 X)
생성자 함수에서만 사용

**프로토 타입(생성자 함수로 생성한 객체가 공용으로 가지는 공간)**
- 자바 스크립트의 모든 함수는 변수 prototype을 갖는다.
- prototype은 객체이다.
** 함수 정의 시 발생하는 일**
- 해당 함수에 Construct(생성자) 자격 부여 (함수만 new 키워드를 사용할 수 있는 이유)
 해당 함수의 prototype object 생성 및 연결

