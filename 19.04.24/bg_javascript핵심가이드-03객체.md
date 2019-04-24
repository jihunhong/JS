#자바스크립트핵심가이드
## 03객체 


* **속성값 읽기**
	
	대괄호[''] or 마침표(.)표기법
    
	 속성 이름이 유효한 자바스크립트 이름이고 예약어가 아닐 경우에는 마침표(.) 표기법을 대신 사용할 수 있습니다.  
	마침표 표기법은 보다 간단하고 읽기가 편하기 때문에 보통 더 선호합니다. (P41)
	객체에 존재하지 않는 속성을 읽으려고 하면 undefined를 반환합니다. || 연산자를 사용하여 다음과 같이 기본값을 지정할 수 있습니다.

	```
	var middle = stooge["middle-name"] || "(none)";
	var status = flight.status || "unknown";
	```
	


* **참조**

	객체는 참조 방식으로 전달됩니다. 결코 복사되지 않습니다.
    ```
    var x = stooge;
    x.nickname = 'Curly';
    var nick = stooge.nickname;
    	 //x와 stooge가 모두 같은 객체를 참조하기 때문에,
         //변수 nick의 값은 'Curly'
    ```
   
    
* **프로토타입(Prototype)**

	모든 객체는 속성을 상속하는 프로토타입 객체에 연결돼 있습니다. 객체 리터럴로 생성되는 모든 객체는 자바스크립트의 표준 객체인 Object의 속성인 prototype(Object.prototype) 객체에 연결됩니다.
    
    객체를 생성할 때는 해당 객체의 프로토타입이 될 객체를 선택할 수 있습니다. 
    
    ```
    if (typeof Object.create !== 'function) {
    	Object.create = function (o) {
        	var F = function () {};
            F.prototype = o;
            return new F();
        }
    }
    var another_stooge = Object.create(stooge);
    ```
    
	프로토타입 연결은 값의 갱신에 영향을 받지 않습니다. 즉 객체를 변경하더라도 객체의 포로토 타입에는 영향을 미치지 않습니다. 
    
    프로토타입 연결은 오로지 객체의 속성을 읽을 때만 사용합니다. 객체에 있는 특정 속성의 값을 읽으려고 하는데 해당 속성이 객체에 없는 경우 자바스크립트는 이 속성을 프로토타입 객체에서 찾으려고 합니다. 이러한 시도는 프로토타입 체인의 가장 마지막에 있는 Object.prototype까지 계속해서 이어집니다. 만약 찾으려는 속성이 프로토타입 체인 어디에도 존재하지 않는 경우 undefined를 반환합니다. 이러한 일련의 내부 동작을 ==위임== 이라고 합니다.
    
    프로토타입 관계는 동적 관계 입니다. 만약 프로토타입에 새로운 속성이 추가되면, 해당 프로토타입을 근간으로 하는 객체들에는 즉각적으로 이 속성이 나타납니다.
    
    ```
    stooge.profession = 'actor';
    another_stooge.profession // 'actor'
    ```
    
* **삭제**

	delete 연산자 사용
    해당 속성이 객체에 있을 경우 삭제를 하며 프로토타입 연결 상에 있는 객체들은 접근하지 않습니다.
    
    객체에서 특정 속성을 삭제했는데 같은 이름의 속성이 프로토타입 체인에 있는 경우 프로토타입의 속성이 나타납니다.
    
    ```
    another_stooge.nickname // 'Moe'
    
    delete another_stooge.nickname;
    
    another_stooge.nickname // 'Curly'
    ```





------------

