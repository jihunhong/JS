# ��ü
- ����, ���ڿ�, �Ҹ���, null, undefined �� ������ ��� �ٸ� ����
- ���� ������ �Ӽ����� ����
- �̸��� ���� �ִ� �Ӽ����� �����ϴ� �����̳�

### ��ü ���ͷ�
- �ƹ� �͵� ���ų� �ϳ� �̻��� �̸�/�� �ֵ��� �ѷ��δ� �߰�ȣ
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

### �Ӽ��� �б�
- ���ȣ�� ���� �� �ִ�.
- ��ħǥ�� ���� �� �ִ�.
```js
console.log(flight["departure"]["city"]); // Korea
console.log(flight.departure.city); // Korea
console.log(flight["departure"][city]); // error
```
- ��ü�� �������� �ʴ� �Ӽ��� ���� ��� **undefined**�� ��ȯ�Ѵ�.

### �Ӽ��� ����
- �Ӽ� �̸��� �̹� ��ü �ȿ� �����ϸ� �ش� �Ӽ��� ���� ��ü�Ѵ�.
- �Ӽ��� ��ü ���� �������� �ʴ� ��� �ش� �Ӽ��� ��ü�� �߰��Ѵ�.

### ������Ÿ��
- ��ü ���ͷ��� �����Ǵ� ��� ��ü�� �ڹٽ�ũ��Ʈ�� ǥ�� ��ü�� Object�� �Ӽ��� prototype ��ü�� ���� �ȴ�.
- ����
  - ��ü�� �ִ� Ư�� �Ӽ��� ���� �������� �ϴµ� �ش� �Ӽ��� ��ü�� ���� ��� �� �Ӽ��� ������Ÿ�� ��ü���� ã������ �Ѵ�.
  - �̷��� �õ��� ������Ÿ�� ü���� ���� �������� �ִ� Object.property���� ����ؼ� �̾�����.
  - ã������ �Ӽ��� ������Ÿ�� ü�� ��𿡵� �������� �ʴ� ��� undefined�� ��ȯ
  - �̷��� ü���� ��ü�� �����ϴ��� ��ü�� ������Ÿ�Կ��� ������ ��ġ�� �ʴ´�.
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

### ����
- delete �����ڸ� �̿��� ��ü�� �Ӽ��� ������ �� �ִ�.
- ������Ÿ�� ü���� �ִ� ��� ������Ÿ���� �Ӽ��� ��Ÿ����.
```js
    var foo = {
        fooProp : "This is foo"
    };
    var foo2 = Object.create(foo);
    
    foo2.fooProp = "Change foo prop";
    
    delete foo2.fooProp;
    console.log(foo2.fooProp); // This is foo
```

### �ּ����� �������� ���
- �ϳ��� ������������ �����, �ٸ� ���������� ���� �����̳ʷ� ����Ѵ�.
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

# �Լ�

### �޼ҵ� ȣ�� ����
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

### ������ ȣ�� ����
- new ��� **��ġ ������**�� ���
- �̷��� ������ �Լ��� ����ϴ� ��Ÿ���� ���� ������ �ƴϴ�(?)
```js
    var foo = function(stringParam){
        this.param = stringParam;
    }
    var myFoo = new foo("this is param");
    console.log(myFoo.param); // this is param
```

### �μ� �迭
- arguments Ű���带 ���
- arguments�� length��� �Ӽ��� ���� ������, �迭�� ������ �ٸ� �Ӽ����� ���� ������ �迭�� �ƴϴ�.
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

### �Լ� ����
- ��ü Ÿ�Կ����� ������ **Object.property** ���� ü���� �̾�����.
- �Լ������� ������ **Function.property** ���� ü���� �̾�����.
- ��, Function.property�� �Լ��� ������ ������ �ٸ� �Լ��鿡�� �⺻������ �ش� �Լ��� �����ϰ� �ȴ�.
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

### Ŭ����
- ���� �Լ����� �ڽ��� �����ϰ� �ִ� �ܺ� �Լ��� �Ű������� �����鿡 �����Ѵ�.
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
    myObj.value = 5; // ���� �Ұ���, �� ���� ����� �� �� ����.
    console.log(myObj.getValue()); // 3
```
- IIFE
  - ��ý����Լ� ǥ����(IIFE, Immediately-Invoked Function Expression)�� �ǹ�
  - �۷ι� �������� ������Ű�� �ʱ� ���ؼ� ���
  - ��ǥ���� ���̺귯������ IIFE ������ ����ؼ� �浹�� �����ϰ� �ִ�.
  - ǥ���� ������ ����.
```js
(function() {
    // IIFE �ٵ�
})();
```
- Ŭ������ IIFE ����
```js
    var foo = function(){
        var i;
        for(i=0; i<5; i++){
            setTimeout(function(){
                console.log(i);
            }, 100);
        }
    }
    foo(); // 5�� 5�� ���
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
    foo(); // 0 1 2 3 4�� ���
```
- Ŭ���� release (�޸� ���� ����)
```js
    var myFoo = foo();
    myFoo = null;
```

### Ŀ��
- �Լ��� �μ��� �����Ͽ� ���ο� �Լ��� ���� �� �ִ�.
- ���ڸ� ������ �޴� �Լ��� �и��Ͽ�, ���ڸ� �ϳ��� �޴� �Լ��� ü���� ����� ���
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
- �ش� �������� converter��� �Լ��� 4���� �Ű� ������ ������, Ŀ���� �̿��Ͽ� �Ű������� �ϳ��� �ް� ü���� �̿��Ͽ� ����� ���� �� �ִ�.

### �޸����̼�
- ������ ������ ����� �ִµ� �̸� �ٽ� �����ϴ� �۾��� ���ϱ� ���� ���
```js
// ����Լ��� �̿��� �Ǻ���ġ ���
    var fibonacci = function(n){
        return n<2 ? n : fibonacci(n-1) + fibonacci(n-2);
    }
    for(var i=0; i<=10; i++){
        console.log(fibonacci(i));
    }
```
- ���� ������ �� ���������� �� 453���� ��� ������ ��ģ��.
- �̷��� �۾����� ���̱� ���� ������ ������ ����� �ִٸ� ������ �������� �ʰ� �ٷ� ����� ��ȯ�ϵ��� �ڵ带 �ۼ��Ѵ�.
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
- �̷��� �޸����̼� ����� 29���� ���길�� �����Ͽ� ȿ�����̴�.