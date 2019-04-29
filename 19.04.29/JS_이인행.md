### 상속
- 객체 o에서 프로퍼티 x를 찾는다.
- 만약 객체 o가 프로퍼티 x를 갖고 있지 않으면, o의 프로토타입 객체에서 x를 찾는다.
- 만약 프로토타입 객체에 고유 프로퍼티 x가 없다면, 해당 프로토타입 객체가 또 다른 프로토타입을 갖고 있는지 검사한다.
- 이후 또 다른 프로토타입을 갖고 있는 경우 그 또 다른 프로토타입 객체에서 프로퍼티 x를 찾는다.
- 해당 작업은 프로퍼티 x를 찾거나 null인 객체가 발견될 때까지 계속된다.
- 프로퍼티를 설정하면 객체에 프로퍼티가 만들어지거나 그 값이 설정 되며, 프로토타입 체인은 결코 변경되지 않는다.
```js
    var unitcircle = { z:5, r:1};
    var c = Object.create(unitcircle);
    c.x = 1; c.y = 2; c.r = 10;
    console.log(c.z); // 5
    console.log(unitcircle.r); // 1
```

### 프로퍼티 삭제
- delete 연산자를 이용한다.
- 프로퍼티의 값을 지우는 것이 아니라 프로퍼티를 지우는 것이다.
- 상속받은 프로퍼티가 아닌 고유 프로퍼티만 지울 수 있다.
- 상속받은 프로퍼티를 지우기 위해서는 해당 프로퍼티가 정의된 프로토타입 객체에서 지워야 한다.
- 삭제에 성공하면 해당 프로토타입 객체를 상속한 모든 객체가 영향을 받는다.
- delete 연산자는 올바른 표현식이 아니거나 아무 효력을 낼 수 없는 경우에도 true를 반환한다.
- 엄격 모드에서는 delete 연산자를 명시적으로 사용해야 한다.
```js
    this.x = 1;
    delete x; // 엄격 모드에서 SyntaxError
    delete this.x; // 잘 동작한다.
```

### 프로퍼티 검사
- in 연산자 : 프로퍼티가 존재하면 true를 반환
```js
    var foo = { bar: 1};
    console.log("bar" in foo); // true
```
- hasOwnProperty()
  - 프로퍼티가 객체에 존재하는지 확인
  - 상속받은 프로퍼티의 경우에는 false를 반환
```js
    var foo = { bar: 1};
    console.log(foo.hasOwnProperty("bar")); // true
    console.log(foo.hasOwnProperty("toString")); // false
```
- propertyIsEnumerable()
  - 객체에 주어진 이름의 고유 프로퍼티가 존재하는지 확인한다.
  - 상속받은 프로퍼티의 경우에는 false를 반환
  - 열거할 수 있는 프로퍼티인 경우에만 true를 반환한다.
```js
    var foo = { bar: 1};
    var child = Object.create(foo);
    console.log(child.propertyIsEnumerable("bar")); // false
    console.log(child.propertyIsEnumerable("toString")); // false
```
- **!==** 연산자 사용
```js
    var foo = { bar: 1};
    console.log(foo.x !== undefined); // false
    console.log(foo.bar !== undefined); // true
```

### 프로퍼티 열거
- for/in 루프를 사용한다.
```js
    var foo = {bar1:1, bar2:2, bar3:3};
    for(p in foo)
        console.log(p); // bar1 bar2 bar3

    for(p in foo){
        if (!foo.hasOwnProperty(p))
            continue; // 상속받은 프로퍼티는 생략한다.
    }
    for(p in foo){
        if(typeof foo[p] === "function")
            continue; // 해당 프로퍼티가 메서드면 생략한다.
    }
```
- Object.keys() 를 사용한다.
  - 인자로 들어가는 오브젝트의 프로퍼티를 배열 형태로 반환한다.
```js
    var foo = {bar1:1, bar2:2, bar3:3};
    console.log(Object.keys(foo)); // ["bar1", "bar2", "bar3"]
```
- Object.getOwnPropertyNames() 를 사용한다.
  - 인자로 들어가는 오브젝트의 프로퍼티를 배열 형태로 반환한다.
```js
    var foo = {bar1:1, bar2:2, bar3:3};
    console.log(Object.getOwnPropertyNames(foo));  // ["bar1", "bar2", "bar3"]
```

### 접근자 프로퍼티 Getter와 Setter
- 프로퍼티가 getter 메서드만 가지고 있다면 읽기 전용 프로퍼티가 된다.
- 프로퍼티가 setter 메서드만 가지고 있다면 쓰기 전용 프로퍼티가 된다.
  - 데이터 프로퍼티로는 쓰기 전용 프로퍼티를 만들 수 없다.
  - 읽기를 시도하면 항상 undefined가 반환된다.
```js
    var foo = {
        // 읽기, 쓰기 속성을 가진 일반적인 데이터 프로퍼티
        bar1:1,
        bar2:2,
        
        // bar 메서드는 읽기 쓰기가 가능한 접근자 프로퍼티
        get bar(){
            return this.bar1 + this.bar2;
        },
        set bar12(newBar){
            this.bar1 = newBar;
        },
        
        // getBar 메서는 읽기 전용 접근자 프로퍼티
        get getBar(){
            return this.bar1 + this.bar2;
        }
    };

    console.log(foo.bar12); // 3
    foo.bar12 = 10;
    console.log(foo.bar12); // 12
    console.log(foo.getBar); // 12
    foo.getBar = 100;
    console.log(foo.getBar); // 12
```

### 프로퍼티 속성
- writable : 프로퍼티 값의 변경 가능 여부를 결정한다.
  - 해당 속성이 false일 경우 일반적으로 값이 변경되지 않는다.
  - 엄격 모드일 경우에는 TypeError 예외가 발생한다.
- enumerable : 프로퍼티가 열거될 수 있는지 여부를 결정한다.
- configurable : configurable과 writable 속성과 enumerable 속성 값의 변경 가능 여부를 결정한다.
- 해당 속성 여부를 확인하고 싶을 경우 Object.getOwnPropertyDescriptor()를 이용한다.
```js
    console.log(Object.getOwnPropertyDescriptor({x: 1}, "x"));
    // {value: 1, writable: true, enumerable: true, configurable: true}
```
- 프로퍼티의 속성을 추가하거나 변경하기 위해서는 Object.defineProperty()를 사용한다.
  - 여러개를 한꺼번에 할 경우 defineProperties를 사용한다.
  - 4개의 속성을 모두 적어줄 필요는 없다.
```js
    var foo2 = {};
    
    Object.defineProperty(foo2, "bar", {
        value: 1,
        writable:true,
        enumerable: false,
        configurable: true
    });


    var foo3 = Object.defineProperties({},{
        x: {value:1, writable:true, enumerable:true, configurable:true},
        y: {value:1, writable:true, enumerable:true, configurable:true},
        r:{
            get: function(){
                return Math.sqrt(this.x*this.x + this.y*this.y)
            },
            enumerable:true,
            configurable:true
        }
    });
```
- 속성의 규칙은 다음과 같다.
  - extensible 하지 않은 객체는, 기존의 고유 프로퍼티를 수정할 수는 있지만 새 프로퍼티를 추가할 수는 없다.
  - 프로퍼티의 configurable 속성 값이 false면, configurable 속성 값뿐 아니라 enumerable 속성 값도 바꿀 수 없다.
  - 접근자 프로퍼티의 configurable 속성 값이 false면, getter/setter 메서드를 변경할 수도 없고, 데이터 프로퍼티로 바꿀 수도 없다.
  - 데이터 프로퍼티의 configurable 속성 값이 false면, 데이터 프로퍼티를 접근자 프로퍼티로 바꿀 수 없다.
  - 데이터 프로퍼티의 configurable 속성 값이 false면, 기존의 writable 속성을 false에서 true로 바꿀 수 없다. 하지만 true에서 false로 바꾸는 것은 가능하다.
  - 데이터 프로퍼티의 configurable 속성 값과 writable 속성 값이 false면, 프로퍼티 값을 바꿀 수 없다.
  - 프로퍼티의 configurable 속성 값이 true고, writable 속성 값이 false인 경우 프로퍼티의 값을 바꿀 수 있다.

### 상속 관계 확인
- isPrototypeOf()를 사용한다.
```js
    var foo = {bar: 1};
    var foo2 = Object.create(foo);
    console.log(foo.isPrototypeOf(foo2)); // true
    console.log(Object.prototype.isPrototypeOf(foo)); // true
```

### 프로퍼티의 확장 방지
- Object.seal()을 사용한다.
  - 객체를 확장할 수 없게 만들며, 객체가 가진 모든 고유 프로퍼티를 설정 불가능하게 만든다.
  - 즉, 새로운 프로퍼티를 추가할 수 없고, 기존 프로퍼티의 설정을 바꾸거나 지울 수도 없다.
  - 하지만 writable 속성이 true인 기존 프로퍼티의 값은 변경할 수 있다.
  - Object.seal()을 이용해 한번 봉인된 객체는 다시 해제할 수 없다.
  - Object.isSealed()를 이용하여 검사할 수 있다.
- Object.freeze()를 사용한다.
  - 객체를 확장할 수 없게 만들고 프로퍼티 설정을 바꿀 수 없게 바꾼다.
  - 객체가 가진 고유 프로퍼티를 전부 읽기 전용으로 만든다.
  - 만약 setter 메서드를 구현해 놓은 상태라면 setter 메서드를 이용해 값을 변경할 수 있다.
  - Object.isFrozen() 메서드를 사용해 확인할 수 있다.
- 두 메서드 모두 프로토타입 객체에는 영향을 미치지 않는다.
  - 즉, 객체를 철저히 잠그고 싶다면, 객체의 프로토타입 체인까지 모두 잠궈야 한다.
