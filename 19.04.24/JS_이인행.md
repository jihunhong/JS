# 객체
- 숫자, 문자열, 불리언, null, undefined 를 제외한 모든 다른 값들
- 변형 가능한 속성들의 집합
- 이름과 값이 있는 속성들을 포함하는 컨테이너

### 객체 리터럴
- 아무 것도 없거나 하나 이상의 이름/값 쌍들을 둘러싸는 중괄호
```js
var flight = {
    airline: "Oceanic",
    departure: {
        time: "2019-04-24",
        city: "Korea"
    },
    arrival: {
        time: "2019-04-25",
        city: "Los Angeles"
    }
};
```

### 속성값 읽기
- 대괄호로 읽을 수 있다.
- 마침표로 읽을 수 있다.
```js
console.log(flight["departure"]["city"]); // Korea
console.log(flight.departure.city); // Korea
console.log(flight["departure"][city]); // error
```
- 객체에 존재하지 않는 속성을 읽을 경우 **undefined**를 반환한다.

### 속성값 갱신
- 속성 이름이 이미 객체 안에 존재하면 해당 속성의 값만 교체한다.
- 속성이 객체 내에 존재하지 않는 경우 해당 속성을 객체에 추가한다.

### 프로토타입
- 객체 리터럴로 생성되는 모든 객체는 자바스크립트의 표준 객체인 Object의 속성인 prototype 객체에 연결 된다.
- 위임
  - 객체에 있는 특정 속성의 값을 읽으려고 하는데 해당 속성이 객체에 없는 경우 이 속성을 프로토타입 객체에서 찾으려고 한다.
  - 이러한 시도는 프로토타입 체인의 가장 마지막에 있는 Object.property까지 계속해서 이어진다.
  - 찾으려는 속성이 프로토타입 체인 어디에도 존재하지 않는 경우 undefined를 반환
  - 이러한 체인은 객체를 변경하더라도 객체의 프로토타입에는 영향을 미치지 않는다.
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

### 삭제
- delete 연산자를 이용해 객체의 속성을 삭제할 수 있다.
- 프로토타입 체인이 있는 경우 프로토타입의 속성이 나타난다.
```js
    var foo = {
        fooProp : "This is foo"
    };
    var foo2 = Object.create(foo);
    
    foo2.fooProp = "Change foo prop";
    
    delete foo2.fooProp;
    console.log(foo2.fooProp); // This is foo
```

### 최소한의 전역변수 사용
- 하나의 전역변수만을 만들고, 다른 전역변수를 위한 컨테이너로 사용한다.
```js
    var MYAPP = {};
    
    MYAPP.userInfo = {
        firstName : "Lee",
        lastName : "InHaeng"
    };
    
    MYAPP.flight = {
        departure: {
            time: "2019-04-24",
            city: "Korea"
        },
        arrival: {
            time: "2019-04-25",
            city: "Los Angeles"
        }
    };
```

# 함수

### 메소드 호출 패턴
```js
    var myObj = {
        value: 0,
        increment: function(inc){
            this.value += typeof inc === 'number' ? inc : 1;
        }
    };
    myObj.increment(2);
    console.log(myObj.value); // 2
```

### 생성자 호출 패턴
- new 라는 **전치 연산자**를 사용
- 이러한 생성자 함수를 사용하는 스타일은 권장 사항이 아니다(?)
```js
    var foo = function(stringParam){
        this.param = stringParam;
    }
    var myFoo = new foo("this is param");
    console.log(myFoo.param); // this is param
```

### 인수 배열
- arguments 키워드를 사용
- arguments는 length라는 속성은 갖고 있지만, 배열이 가지는 다른 속성들은 없기 때문에 배열이 아니다.
```js
    var sum = function(){
        var i, sum = 0;
        for(i=0; i<arguments.length; i++){
            sum += arguments[i];
        }
        return sum;
    }
    console.log(sum(1,2,3,4,5,6)); // 21
```

### 함수 위임
- 객체 타입에서의 위임은 **Object.property** 까지 체인이 이어진다.
- 함수에서의 위임은 **Function.property** 까지 체인이 이어진다.
- 즉, Function.property에 함수를 정의해 놓으면 다른 함수들에도 기본적으로 해당 함수가 존재하게 된다.
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

### 클로저
- 내부 함수에서 자신을 포함하고 있는 외부 함수의 매개변수와 변수들에 접근한다.
```js
    var myObj = function(){
        var value = 0;
        
        return {
            increment : function(inc){
                value += typeof inc === 'number' ? inc : 1;
            },
            getValue : function(){
                return value;
            }
        };
    }();
    
    myObj.increment(3);
    console.log(myObj.getValue()); // 3
    myObj.value = 5; // 접근 불가능, 즉 은닉 기능이 될 수 있음.
    console.log(myObj.getValue()); // 3
```
- IIFE
  - 즉시실행함수 표현식(IIFE, Immediately-Invoked Function Expression)을 의미
  - 글로벌 스코프를 오염시키지 않기 위해서 사용
  - 대표적인 라이브러리들은 IIFE 패턴을 사용해서 충돌을 방지하고 있다.
  - 표현은 다음과 같다.
```js
(function() {
    // IIFE 바디
})();
```
- 클로저와 IIFE 예제
```js
    var foo = function(){
        var i;
        for(i=0; i<5; i++){
            setTimeout(function(){
                console.log(i);
            }, 100);
        }
    }
    foo(); // 5가 5번 출력
```
```js
    var foo = function(){
        var i;
        for(i=0; i<5; i++){
            (function(j){
                setTimeout(function(){
                    console.log(j);
                }, 100);
            })(i);
        }
    }
    foo(); // 0 1 2 3 4가 출력
```
- 클로저 release (메모리 누수 방지)
```js
    var myFoo = foo();
    myFoo = null;
```

### 커링
- 함수와 인수를 결합하여 새로운 함수를 만들 수 있다.
- 인자를 여러개 받는 함수를 분리하여, 인자를 하나만 받는 함수의 체인을 만드는 방법
```js
        Function.prototype.curry = function() {
            var slice = Array.prototype.slice;
            var args = slice.apply(arguments);
            var that = this;
            return function() {
                return that.apply(null, args.concat(slice.apply(arguments)));
            };
        }

        function converter(toUnit, factor, offset, input) {
            offset = offset || 0;
            return [((offset + input) * factor).toFixed(2), toUnit].join(" ");
        }
        var milesToKm = converter.curry('km', 1.60936, undefined);
        var poundsToKg = converter.curry('kg', 0.45460, undefined);
        var farenheitToCelsius = converter.curry('degrees C', 0.5556, -32);
        
        console.log(milesToKm(10)); // returns "16.09 km"
        console.log(poundsToKg(2.5)); // returns "1.14 kg"
        console.log(farenheitToCelsius(98)); // returns "36.67 degrees C"
```
- 해당 예제에서 converter라는 함수는 4개의 매개 변수를 받지만, 커링을 이용하여 매개변수를 하나만 받고 체인을 이용하여 결과를 얻을 수 있다.

### 메모리제이션
- 이전에 연산한 결과가 있는데 이를 다시 연산하는 작업을 피하기 위해 사용
```js
// 재귀함수를 이용한 피보나치 계산
    var fibonacci = function(n){
        return n<2 ? n : fibonacci(n-1) + fibonacci(n-2);
    }
    for(var i=0; i<=10; i++){
        console.log(fibonacci(i));
    }
```
- 위의 예제는 잘 동작하지만 총 453번의 계산 과정을 거친다.
- 이러한 작업량을 줄이기 위해 이전에 연산한 결과가 있다면 연산을 수행하지 않고 바로 결과를 반환하도록 코드를 작성한다.
```js
    var fibonacci = function(){
        var memo = [0,1];
        var fib = function(n){
            var result = memo[n];
            if(typeof result !== 'number'){
                result = fib(n-1) + fib(n-2);
                memo[n] = result;
            }
            return result;
        };
        return fib;
    }();
    for(var i=0; i<=10; i++){
        console.log(fibonacci(i));
    }
```
- 이러한 메모리제이션 사용은 29번의 연산만을 수행하여 효율적이다.