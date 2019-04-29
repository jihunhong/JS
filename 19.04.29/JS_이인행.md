### ���
- ��ü o���� ������Ƽ x�� ã�´�.
- ���� ��ü o�� ������Ƽ x�� ���� ���� ������, o�� ������Ÿ�� ��ü���� x�� ã�´�.
- ���� ������Ÿ�� ��ü�� ���� ������Ƽ x�� ���ٸ�, �ش� ������Ÿ�� ��ü�� �� �ٸ� ������Ÿ���� ���� �ִ��� �˻��Ѵ�.
- ���� �� �ٸ� ������Ÿ���� ���� �ִ� ��� �� �� �ٸ� ������Ÿ�� ��ü���� ������Ƽ x�� ã�´�.
- �ش� �۾��� ������Ƽ x�� ã�ų� null�� ��ü�� �߰ߵ� ������ ��ӵȴ�.
- ������Ƽ�� �����ϸ� ��ü�� ������Ƽ�� ��������ų� �� ���� ���� �Ǹ�, ������Ÿ�� ü���� ���� ������� �ʴ´�.
```js
    var unitcircle = { z:5, r:1};
    var c = Object.create(unitcircle);
    c.x = 1; c.y = 2; c.r = 10;
    console.log(c.z); // 5
    console.log(unitcircle.r); // 1
```

### ������Ƽ ����
- delete �����ڸ� �̿��Ѵ�.
- ������Ƽ�� ���� ����� ���� �ƴ϶� ������Ƽ�� ����� ���̴�.
- ��ӹ��� ������Ƽ�� �ƴ� ���� ������Ƽ�� ���� �� �ִ�.
- ��ӹ��� ������Ƽ�� ����� ���ؼ��� �ش� ������Ƽ�� ���ǵ� ������Ÿ�� ��ü���� ������ �Ѵ�.
- ������ �����ϸ� �ش� ������Ÿ�� ��ü�� ����� ��� ��ü�� ������ �޴´�.
- delete �����ڴ� �ùٸ� ǥ������ �ƴϰų� �ƹ� ȿ���� �� �� ���� ��쿡�� true�� ��ȯ�Ѵ�.
- ���� ��忡���� delete �����ڸ� ��������� ����ؾ� �Ѵ�.
```js
    this.x = 1;
    delete x; // ���� ��忡�� SyntaxError
    delete this.x; // �� �����Ѵ�.
```

### ������Ƽ �˻�
- in ������ : ������Ƽ�� �����ϸ� true�� ��ȯ
```js
    var foo = { bar: 1};
    console.log("bar" in foo); // true
```
- hasOwnProperty()
  - ������Ƽ�� ��ü�� �����ϴ��� Ȯ��
  - ��ӹ��� ������Ƽ�� ��쿡�� false�� ��ȯ
```js
    var foo = { bar: 1};
    console.log(foo.hasOwnProperty("bar")); // true
    console.log(foo.hasOwnProperty("toString")); // false
```
- propertyIsEnumerable()
  - ��ü�� �־��� �̸��� ���� ������Ƽ�� �����ϴ��� Ȯ���Ѵ�.
  - ��ӹ��� ������Ƽ�� ��쿡�� false�� ��ȯ
  - ������ �� �ִ� ������Ƽ�� ��쿡�� true�� ��ȯ�Ѵ�.
```js
    var foo = { bar: 1};
    var child = Object.create(foo);
    console.log(child.propertyIsEnumerable("bar")); // false
    console.log(child.propertyIsEnumerable("toString")); // false
```
- **!==** ������ ���
```js
    var foo = { bar: 1};
    console.log(foo.x !== undefined); // false
    console.log(foo.bar !== undefined); // true
```

### ������Ƽ ����
- for/in ������ ����Ѵ�.
```js
    var foo = {bar1:1, bar2:2, bar3:3};
    for(p in foo)
        console.log(p); // bar1 bar2 bar3

    for(p in foo){
        if (!foo.hasOwnProperty(p))
            continue; // ��ӹ��� ������Ƽ�� �����Ѵ�.
    }
    for(p in foo){
        if(typeof foo[p] === "function")
            continue; // �ش� ������Ƽ�� �޼���� �����Ѵ�.
    }
```
- Object.keys() �� ����Ѵ�.
  - ���ڷ� ���� ������Ʈ�� ������Ƽ�� �迭 ���·� ��ȯ�Ѵ�.
```js
    var foo = {bar1:1, bar2:2, bar3:3};
    console.log(Object.keys(foo)); // ["bar1", "bar2", "bar3"]
```
- Object.getOwnPropertyNames() �� ����Ѵ�.
  - ���ڷ� ���� ������Ʈ�� ������Ƽ�� �迭 ���·� ��ȯ�Ѵ�.
```js
    var foo = {bar1:1, bar2:2, bar3:3};
    console.log(Object.getOwnPropertyNames(foo));  // ["bar1", "bar2", "bar3"]
```

### ������ ������Ƽ Getter�� Setter
- ������Ƽ�� getter �޼��常 ������ �ִٸ� �б� ���� ������Ƽ�� �ȴ�.
- ������Ƽ�� setter �޼��常 ������ �ִٸ� ���� ���� ������Ƽ�� �ȴ�.
  - ������ ������Ƽ�δ� ���� ���� ������Ƽ�� ���� �� ����.
  - �б⸦ �õ��ϸ� �׻� undefined�� ��ȯ�ȴ�.
```js
    var foo = {
        // �б�, ���� �Ӽ��� ���� �Ϲ����� ������ ������Ƽ
        bar1:1,
        bar2:2,
        
        // bar �޼���� �б� ���Ⱑ ������ ������ ������Ƽ
        get bar(){
            return this.bar1 + this.bar2;
        },
        set bar12(newBar){
            this.bar1 = newBar;
        },
        
        // getBar �޼��� �б� ���� ������ ������Ƽ
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

### ������Ƽ �Ӽ�
- writable : ������Ƽ ���� ���� ���� ���θ� �����Ѵ�.
  - �ش� �Ӽ��� false�� ��� �Ϲ������� ���� ������� �ʴ´�.
  - ���� ����� ��쿡�� TypeError ���ܰ� �߻��Ѵ�.
- enumerable : ������Ƽ�� ���ŵ� �� �ִ��� ���θ� �����Ѵ�.
- configurable : configurable�� writable �Ӽ��� enumerable �Ӽ� ���� ���� ���� ���θ� �����Ѵ�.
- �ش� �Ӽ� ���θ� Ȯ���ϰ� ���� ��� Object.getOwnPropertyDescriptor()�� �̿��Ѵ�.
```js
    console.log(Object.getOwnPropertyDescriptor({x: 1}, "x"));
    // {value: 1, writable: true, enumerable: true, configurable: true}
```
- ������Ƽ�� �Ӽ��� �߰��ϰų� �����ϱ� ���ؼ��� Object.defineProperty()�� ����Ѵ�.
  - �������� �Ѳ����� �� ��� defineProperties�� ����Ѵ�.
  - 4���� �Ӽ��� ��� ������ �ʿ�� ����.
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
- �Ӽ��� ��Ģ�� ������ ����.
  - extensible ���� ���� ��ü��, ������ ���� ������Ƽ�� ������ ���� ������ �� ������Ƽ�� �߰��� ���� ����.
  - ������Ƽ�� configurable �Ӽ� ���� false��, configurable �Ӽ� ���� �ƴ϶� enumerable �Ӽ� ���� �ٲ� �� ����.
  - ������ ������Ƽ�� configurable �Ӽ� ���� false��, getter/setter �޼��带 ������ ���� ����, ������ ������Ƽ�� �ٲ� ���� ����.
  - ������ ������Ƽ�� configurable �Ӽ� ���� false��, ������ ������Ƽ�� ������ ������Ƽ�� �ٲ� �� ����.
  - ������ ������Ƽ�� configurable �Ӽ� ���� false��, ������ writable �Ӽ��� false���� true�� �ٲ� �� ����. ������ true���� false�� �ٲٴ� ���� �����ϴ�.
  - ������ ������Ƽ�� configurable �Ӽ� ���� writable �Ӽ� ���� false��, ������Ƽ ���� �ٲ� �� ����.
  - ������Ƽ�� configurable �Ӽ� ���� true��, writable �Ӽ� ���� false�� ��� ������Ƽ�� ���� �ٲ� �� �ִ�.

### ��� ���� Ȯ��
- isPrototypeOf()�� ����Ѵ�.
```js
    var foo = {bar: 1};
    var foo2 = Object.create(foo);
    console.log(foo.isPrototypeOf(foo2)); // true
    console.log(Object.prototype.isPrototypeOf(foo)); // true
```

### ������Ƽ�� Ȯ�� ����
- Object.seal()�� ����Ѵ�.
  - ��ü�� Ȯ���� �� ���� �����, ��ü�� ���� ��� ���� ������Ƽ�� ���� �Ұ����ϰ� �����.
  - ��, ���ο� ������Ƽ�� �߰��� �� ����, ���� ������Ƽ�� ������ �ٲٰų� ���� ���� ����.
  - ������ writable �Ӽ��� true�� ���� ������Ƽ�� ���� ������ �� �ִ�.
  - Object.seal()�� �̿��� �ѹ� ���ε� ��ü�� �ٽ� ������ �� ����.
  - Object.isSealed()�� �̿��Ͽ� �˻��� �� �ִ�.
- Object.freeze()�� ����Ѵ�.
  - ��ü�� Ȯ���� �� ���� ����� ������Ƽ ������ �ٲ� �� ���� �ٲ۴�.
  - ��ü�� ���� ���� ������Ƽ�� ���� �б� �������� �����.
  - ���� setter �޼��带 ������ ���� ���¶�� setter �޼��带 �̿��� ���� ������ �� �ִ�.
  - Object.isFrozen() �޼��带 ����� Ȯ���� �� �ִ�.
- �� �޼��� ��� ������Ÿ�� ��ü���� ������ ��ġ�� �ʴ´�.
  - ��, ��ü�� ö���� ��װ� �ʹٸ�, ��ü�� ������Ÿ�� ü�α��� ��� ��ž� �Ѵ�.
