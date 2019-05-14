### 자바스크립트 소개
- 대부분의 웹사이트에서 사용하고 있는 언어이다.
- 동적이며 타입을 명시할 필요가 없는 인터프리터 언어이다.
- 객체지향과 함수형 프로그래밍을 모두 표현할 수 있다.

### 어휘 구조
- 자바스크립트는 Unicode 문자 집합을 사용해 작성된다.
- 대소문자를 구분한다.
- HTML은 대소문자를 구별하지 않는다.

### 엄격 모드에서 사용할 수 없는 예약어
- let, static, arguments, eval, 8진수 숫자

### 데이터 타입
- 원시 타입 : 숫자, 문자열, 불리언, null, undefined
- 원시 타입 외에는 모두 객체 타입이다.
- 객체는 이름과 값을 갖는 프로퍼티의 집합이다.
  - Date 클래스 : 날짜를 표현하는 객체를 정의
  - RegExp 클래스 : 정규 표현식을 표현하는 객체를 정의
  - Error 클래스 : 문법과 런타임 에러를 표현하는 객체를 정의
  - Array, Function, Object 클래스 등

### 특이한 타입
- Infinity : overflow 대신 표현 되는 값으로 매우 큰 값
  - isFinite() : 인자가 NaN, Infinity, -Infinity 일 경우 false를 반환
- NaN : 숫자가 아님을 의미
  - 무한대를 무한대로 나누는 경우
  - 음수 값에 루트를 씌우는 경우
  - 숫자가 아닌 피연산자로 산술 연산을 시도하는 경우
  - if로 체크가 불가능하고, isNaN() 이라는 함수를 사용
- undefined 
  - 변수만 선언하고 값을 지정하지 않은 상태
  - 존재하지 않는 객체 프로퍼티나 배열의 원소 값에 접근하려고 할 때 얻는 값
- null
  - 변수에 null 값이 들어간 상태
  - 숫자나 문자열이 "값이 없음"을 나타내기도 한다.
- null과 undefined는 if 조건을 이용하여 체크 가능하며 ```==```을 사용할 경우 false를 반환한다.
  - null과 undefined를 구분하기 위해서는 ```===```를 사용하면 된다.

### 역슬래시 문자(\)
- 역슬래시 문자를 이용하여 여러줄로 리터럴을 작성할 수 있다. (+와 같은 효과)
- 작은 따옴표 안에서 작은 따옴표를 표현하기 위해 사용한다. (이스케이프 문자열)

### 래퍼 객체
- 문자열의 프로퍼티를 참조하려고 할 때, 자바스크립트는 new String()을 호출한 것처럼 문자열 값을 임시 객체로 변환한다.
- 프로퍼티 참조가 해제되면 새로 생성된 임시 객체는 메모리에서 회수된다.
```js
    var foo = "test";
    foo.myProp = 4;
    var foo2 = foo.myProp;
    console.log(foo2); // undefined
```
- 즉, 문자열과 숫자, 불리언의 경우에는 프로퍼티가 읽기 전용이며 이 값들에 새로운 프로퍼티를 정의할 수 없다는 점에 주의해야 한다.

### 호이스팅
- 어떤 함수 안에서 선언된 모든 변수는 그 함수 전체에 걸쳐 유효하다는 의미
- 이는 변수가 미처 선언되기 전에도 유효하다는 뜻이다.
```js
    var scope = "global";
    function foo(){
        console.log(scope); // undefined
        var scope = "local";
        console.log(scope); // local
    }
    foo(); // var scope가 foo 함수의 제일 위로 끌어올려지는(hoisting) 것과 같은 현상

    var scope = "global";
    function foo(){
        console.log(scope); // global
        //var scope = "local";
        console.log(scope); // global
    }
    foo();
```

### 유효범위 체인
- 어떠한 프로퍼티를 얻을 때 가장 먼저 해당 객체의 프로퍼티를 검색하고, 만약 존재하지 않는다면 체인에 있는 다음 객체에서 프로퍼티를 찾는다. 이러한 식으로 계속해서 연결된 체인을 검색하며 마지막까지 찾지 못한다면 undefined를 반환한다.
- 객체 타입의 경우 Object()까지 검색하며, 함수의 경우 Function()까지 검색한다.
- Object에서의 체인
```js
    var foo = {
        fooProp : "This is foo"
    };
    var foo2 = Object.create(foo);

    console.log(foo2.fooProp); // This is foo
    foo2.fooProp = "Change foo prop";
    console.log(foo2.fooProp); // Change foo prop
    console.log(foo.fooProp); // This is foo
```
- Function에서의 체인
```js
    Function.prototype.sum = function(arr){
        var i, sum = 0;
        for(i=0; i<arr.length; i++){
            sum += arr[i];
        }
        return sum;
    }
    
    var foo = function(arr){
        this.arr = arr;
    }
    var array = [1,2,3,4,5];
    console.log(foo.sum(array)); // 15
```

### 가비지 컬렉션
- 객체를 어떻게 해제할지 걱정할 필요가 없다?
- 자바스크립트의 메모리 누수
- 전역 변수
  - 선언되지 않은 변수가 사용되는 경우
  - 함수 내에 있지 않은 this 변수를 사용하는 경우
```js
function foo(arg) {
    bar = "some text";
} // 아래의 함수와 같은 의미가 된다.

function foo(arg) {
    window.bar = "some text";
}

function foo() {
    this.var1 = "potential accidental global";
}
// 다른 함수 내에 있지 않은 foo를 호출하면 this는 글로벌 객체(window)를 가리킴
foo();
```
- 클로져
- DOM에서 벗어난 요소 참조
```js
    var elements = {
        btn: document.getElementById('button'),
        img: document.getElementById('image')
    };

    function foo() {
        elements.img.src = 'image1.jpg';
    }

    function removeImage() {
        document.body.removeChild(document.getElementById('image'));
        //button 요소는 아직도 메모리 상에 있고 가비지컬렉터가 가져갈 수 없음
    }
```
