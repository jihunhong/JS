# 다양한 연산자

### 동치와 부등치 연산자
- == 연산자 : ~와 같은 값으로 볼 수 있다.(is equal to)
  - 데이터의 값이 같은 경우
- === 연산자 : ~와 일치한다. (is strictly equal to)
  - 값도 같으며, 타입도 같다. (비교시 자동 타입 변환 X)
  - 비교하려는 두 값이 모두 같은 객체나 배열 또는 함수를 참조하고 있으면 true
  - 두 값이 서로 다른 객체를 참조할 경우 비록 두 객체의 프로퍼티가 일치하더라도 false

### in 연산자
- 좌변의 피연산자로 문자열(또는 문자열로 변환될 수 있는 것)을 받는다.
- 우변의 피연산자로 객체나 배열을 받는다.
- 좌변 값이 우변 객체의 프로퍼티 이름에 해당할 경우 true를 반환
```js
    var point = { x:1, y:1 };
    console.log("x" in point); // true
    console.log("z" in point); // false
    console.log("toString" in point); // true
```
- toString의 경우 상속된 프로퍼티이기 때문에 true를 반환한다.

### instanceof 연산자
- 좌변의 피연산자로 객체를 받는다.
- 우변의 피연산자로 객체 클래스의 이름을 식별자로 받는다.
- 좌변에 오는 객체가 우변 클래스의 인스턴스일 경우 연산 결과를 true로 반환
```js
    var d = new Date();
    console.log(d instanceof Date); // true
    console.log(d instanceof Object); // true
    console.log(d instanceof Number); // false
    var a = [1,2,3];
    console.log(a instanceof Array); // true
    console.log(a instanceof Object); // true
    console.log(a instanceof RegExp); // false
```
- **모든 객체는 Object의 인스턴스이다.**
- instanceof 연산자는 판단할 때 **상위 클래스**들을 고려한다.
```js
bar instanceof foo
```
- 해당 예제에서 foo.prototype에서 bar의 프로토타입 체인에 그 값이 나타나는지 검색한다.
- 만약 발견되면 bar는 foo의 인스턴스(또는 foo의 하위 클래스의 인스턴스)가 되고, true를 반환한다.
- bar의 프로토타입 체인에서 foo.prototype 값을 찾을 수 없다면 false를 반환한다.

### typeof 연산자
- 단일 피연산자 앞에 위치하는 단항 연산자로, 피연산자에는 어떤 타입이든 올 수 있다.
- typeof는 instanceof처럼 하위 인스턴스인지의 확인이 아니라 타입을 확인하기 위해 사용한다.
- 삼항 연산자나 switch문과 함께 사용하면 효율적일 수 있다.
  - case 판별 시에는 **=== 연산자**가 수행 된다.
```js
typeof { foo : bar } === "object" // true

    switch(typeof x){
        case 'number':
            break;
        case 'string':
            break;
        default:
    }
```

### delete 연산자
- 단항 연산자로 피연산자로 지정된 객체 프로퍼티, 배열 원소 또는 변수의 삭제를 시도한다.
- 배열의 원소를 삭제하면 배열에 빈 자리가 생기고, 배열의 길이 자체는 달라지지 않는다.
- 몇몇 내장 코어 프로퍼티, var로 선언한 변수, function으로 정의한 함수와 매개변수는 삭제할 수 없다.

# 표현문

### for/in
- 객체가 가진 프로퍼티들을 쉽게 순회할 수 있다.
  - 접근시 대괄호를 사용하며, "."을 사용해 접근하는 것은 불가능하다.
  - 자바스크립트 코어에 정의된 내장 프로퍼티와 메소드는 접근하지 않는다.
```js
    foo = {
        bar1: 'hi1',
        bar2: 'hi2'
    };
    for(var f in foo)
        console.log(foo[f]); // hi1, hi2 출력
```

### 레이블
- 어떤 문장에라도 그 앞에 식별자 이름과 콜론을 넣음으로써 레이블을 붙일 수 있다.
- 해당 레이블을 다른 곳에서 참고할 수 있다.
```js
    myLabel: for(var i=0; i<5; i++){
        for(var j=0; j<5; j++){
            console.log(j); // 1 2 3 만 출력 후 종료
            if(j===3)
                break myLabel;
        }
    }
```

### debugger
- 디버거가 실행 중일 때 코드의 중단점과 같은 동작을 한다.
- 자바스크립트 코드의 실행을 잠시 멈추고 디버거 프로그램을 사용해 변수의 값을 출력할 수 있고, 호출 스택 등을 살펴볼 수 있게 된다.

### use strict
- 스크립트의 시작 부분이나 함수 몸체의 시작 부분에만 올 수 있으며, 반드시 첫 문장일 필요는 없다.
- 코드들이 엄격 모드를 따르게 한다.
  - with문을 사용할 수 없다.
  - 모든 변수는 반드시 선언되어야 한다.
  - 함수는 메서드로 호출된 것이 아닌, 함수로 호출된 함수의 this 값은 undefined다.
  - delete 다음에 변수나 함수 또는 함수 전달인자와 같은 식별자가 올 경우 예외가 발생한다.
  - 객체 리터럴에 같은 이름의 프로퍼티가 중복될 경우 문법 에러가 발생한다.
  - 8진수 리터럴을 사용할 수 없다.
  - eval을 사용할 경우 다양한 제약조건이 걸린다.

# 객체

### 객체 용어
- 네이티브 객체
  - ECMAScript 명세에 정의된 객체 또는 그 객체의 클래스
  - Array, Function, Date, 정규 표현식 등
- 호스트 객체
  - 자바스크립트 인터프리터가 내장된 호스트 환경에 정의된 객체
  - HTMLElement 등이 있으며, Function 객체 같이 호스트 객체는 네이티브 객체일 수도 있다.
- 사용자 정의 객체
  - 자바스크립트 코드의 실행으로 생성된 객체
- 고유 프로퍼티
  - 객체에 직접 정의된 프로퍼티
- 상속받은 프로퍼티
  - 객체의 프로토타입 객체가 정의한 프로퍼티

### 프로토타입
- 객체 리터럴로 생성된 모든 객체는 프로토타입 객체가 같으며, Object.prototype으로 참조할 수 있다.
- Object, Array, Date 모두 Object.prototype의 프로퍼티들을 상속 받는다.
- 예를 들어 Date 객체는 Date.prototype과 Object.prototype으로부터 프로퍼티를 상속받게 된다.

### 객체 리터럴로 생성
- 중괄호 안에 이름과 값을 쌍점으로 구분한 순서 쌍을 쉼표로 연결한 리스트
```js
    var foo = {
        bar1: 'this is bar1',
        bar2: {
            bar3: 'this is bar3',
            bar4: 'this is bar4'
        },
        bar5: 'this is bar5'
    };
```

### new를 사용해 생성
- 객체를 만들고, 초기화 하며, new 키워드 다음에는 반드시 함수 호출문이 와야 한다.
```js
var o = new Object();
```

### Object.create()를 사용해 생성
- 첫 번째 인자에 프로토타입 객체를 넣는다.
```js
var o1 = Object.create({x:1, y:2});
var o2 = Object.create(Object.prototype); // new Object()와 같은 객체이다.
```
