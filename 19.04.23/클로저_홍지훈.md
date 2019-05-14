## 변수 선언의 3가지 방법.

`var`
변수의 선언, 값의 초기화.

`let`
블록 범위 **지역 변수** 선언, 값의 초기화.

`const`
블록 범위 읽기 전용 상수 선언.

### 클로저(closure)
- 내부함수가 외부함수의 context에 접근할 수 있는 것을 가르킨다.

```js

function outter(){
	var title = 'test';

	  function inner(){
        console.log(title);
    }
    inner();
}
outter();

```
위 예제에서 `내부함수(inner)`가 `외부함수(outter)`의 지역변수 `title`에 접근한것과 같다.

<hr>

- 외부함수의 지역변수를 사용하는 내부함수가 소멸될때까지 소멸되지 않는 특성을 말한다.

```js
function outter(){
    var title = 'test';  
    return function(){        
        alert(title);
    }
}
inner = outter();
inner();
```

outter의 호출결과인 익명함수의 결과가 변수 inner에 담긴후, `inner()`의 실행에서 `title`변수에 담긴 값이 출력되는 결과가 나온다. 이것은 outter()이 아닌 `inner()`의 호출 이후 소멸된다는것을 의미한다.

<hr>

```js
var arr = [];
for(var i=0; i<5; i++){
	arr[i] = function(){
			console.log(i);
		}

}
for(var index in arr){
	console.log(arr[index]());
}
```
위 예제는 클로저에서 자주 일어나는 실수로
모두 5가 리턴된다.

이것은 i가 외부함수의 변수가 아니기 때문에
아래와 같이

```js
var arr = []
for(var i=0; i<5; i++){
		arr[i] = function(id){
			return function(){
				return id;
			}
		}(i);

}
for(var index in arr){
	console.log(arr[index]());
}
```

내부함수가 외부함수의 지역변수 id를 참조해 리턴되는 형태를 띄기 때문에 0부터 5의 값을 리턴한다.
