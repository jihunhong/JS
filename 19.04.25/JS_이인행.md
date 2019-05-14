### �ڹٽ�ũ��Ʈ �Ұ�
- ��κ��� ������Ʈ���� ����ϰ� �ִ� ����̴�.
- �����̸� Ÿ���� ����� �ʿ䰡 ���� ���������� ����̴�.
- ��ü����� �Լ��� ���α׷����� ��� ǥ���� �� �ִ�.

### ���� ����
- �ڹٽ�ũ��Ʈ�� Unicode ���� ������ ����� �ۼ��ȴ�.
- ��ҹ��ڸ� �����Ѵ�.
- HTML�� ��ҹ��ڸ� �������� �ʴ´�.

### ���� ��忡�� ����� �� ���� �����
- let, static, arguments, eval, 8���� ����

### ������ Ÿ��
- ���� Ÿ�� : ����, ���ڿ�, �Ҹ���, null, undefined
- ���� Ÿ�� �ܿ��� ��� ��ü Ÿ���̴�.
- ��ü�� �̸��� ���� ���� ������Ƽ�� �����̴�.
  - Date Ŭ���� : ��¥�� ǥ���ϴ� ��ü�� ����
  - RegExp Ŭ���� : ���� ǥ������ ǥ���ϴ� ��ü�� ����
  - Error Ŭ���� : ������ ��Ÿ�� ������ ǥ���ϴ� ��ü�� ����
  - Array, Function, Object Ŭ���� ��

### Ư���� Ÿ��
- Infinity : overflow ��� ǥ�� �Ǵ� ������ �ſ� ū ��
  - isFinite() : ���ڰ� NaN, Infinity, -Infinity �� ��� false�� ��ȯ
- NaN : ���ڰ� �ƴ��� �ǹ�
  - ���Ѵ븦 ���Ѵ�� ������ ���
  - ���� ���� ��Ʈ�� ����� ���
  - ���ڰ� �ƴ� �ǿ����ڷ� ��� ������ �õ��ϴ� ���
  - if�� üũ�� �Ұ����ϰ�, isNaN() �̶�� �Լ��� ���
- undefined 
  - ������ �����ϰ� ���� �������� ���� ����
  - �������� �ʴ� ��ü ������Ƽ�� �迭�� ���� ���� �����Ϸ��� �� �� ��� ��
- null
  - ������ null ���� �� ����
  - ���ڳ� ���ڿ��� "���� ����"�� ��Ÿ���⵵ �Ѵ�.
- null�� undefined�� if ������ �̿��Ͽ� üũ �����ϸ� ```==```�� ����� ��� false�� ��ȯ�Ѵ�.
  - null�� undefined�� �����ϱ� ���ؼ��� ```===```�� ����ϸ� �ȴ�.

### �������� ����(\)
- �������� ���ڸ� �̿��Ͽ� �����ٷ� ���ͷ��� �ۼ��� �� �ִ�. (+�� ���� ȿ��)
- ���� ����ǥ �ȿ��� ���� ����ǥ�� ǥ���ϱ� ���� ����Ѵ�. (�̽������� ���ڿ�)

### ���� ��ü
- ���ڿ��� ������Ƽ�� �����Ϸ��� �� ��, �ڹٽ�ũ��Ʈ�� new String()�� ȣ���� ��ó�� ���ڿ� ���� �ӽ� ��ü�� ��ȯ�Ѵ�.
- ������Ƽ ������ �����Ǹ� ���� ������ �ӽ� ��ü�� �޸𸮿��� ȸ���ȴ�.
```js
    var foo = "test";
    foo.myProp = 4;
    var foo2 = foo.myProp;
    console.log(foo2); // undefined
```
- ��, ���ڿ��� ����, �Ҹ����� ��쿡�� ������Ƽ�� �б� �����̸� �� ���鿡 ���ο� ������Ƽ�� ������ �� ���ٴ� ���� �����ؾ� �Ѵ�.

### ȣ�̽���
- � �Լ� �ȿ��� ����� ��� ������ �� �Լ� ��ü�� ���� ��ȿ�ϴٴ� �ǹ�
- �̴� ������ ��ó ����Ǳ� ������ ��ȿ�ϴٴ� ���̴�.
```js
    var scope = "global";
    function foo(){
        console.log(scope); // undefined
        var scope = "local";
        console.log(scope); // local
    }
    foo(); // var scope�� foo �Լ��� ���� ���� ����÷�����(hoisting) �Ͱ� ���� ����

    var scope = "global";
    function foo(){
        console.log(scope); // global
        //var scope = "local";
        console.log(scope); // global
    }
    foo();
```

### ��ȿ���� ü��
- ��� ������Ƽ�� ���� �� ���� ���� �ش� ��ü�� ������Ƽ�� �˻��ϰ�, ���� �������� �ʴ´ٸ� ü�ο� �ִ� ���� ��ü���� ������Ƽ�� ã�´�. �̷��� ������ ����ؼ� ����� ü���� �˻��ϸ� ���������� ã�� ���Ѵٸ� undefined�� ��ȯ�Ѵ�.
- ��ü Ÿ���� ��� Object()���� �˻��ϸ�, �Լ��� ��� Function()���� �˻��Ѵ�.
- Object������ ü��
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
- Function������ ü��
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

### ������ �÷���
- ��ü�� ��� �������� ������ �ʿ䰡 ����?
- �ڹٽ�ũ��Ʈ�� �޸� ����
- ���� ����
  - ������� ���� ������ ���Ǵ� ���
  - �Լ� ���� ���� ���� this ������ ����ϴ� ���
```js
function foo(arg) {
    bar = "some text";
} // �Ʒ��� �Լ��� ���� �ǹ̰� �ȴ�.

function foo(arg) {
    window.bar = "some text";
}

function foo() {
    this.var1 = "potential accidental global";
}
// �ٸ� �Լ� ���� ���� ���� foo�� ȣ���ϸ� this�� �۷ι� ��ü(window)�� ����Ŵ
foo();
```
- Ŭ����
- DOM���� ��� ��� ����
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
        //button ��Ҵ� ������ �޸� �� �ְ� �������÷��Ͱ� ������ �� ����
    }
```
