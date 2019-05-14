# HTML5 API

### HTML5
- HTML 규격의 일부분으로 개발되고 규격화된 웹 애플리케이션 기술을 총체적으로 일컫는 용어
- 이 기술을 좀 더 형식적으로 일컫는 용어는 '오픈 웹 플랫폼'이다.

### Geolocation API
- 자바스크립트 프로그램이 사용자의 실제 위치를 브라우저에게 요청할 수 있도록 해준다.
- 중요한 개인 정보와 관련되어 있으므로, 브라우저에서는 사용자의 허락을 받도록 하고 있다.
- navigator.geolocation.getCurrentPosition()
  - 사용자의 현재 위치를 요청한다.
- navigator.geolocation.watchPosition()
  - 현재 위치를 요청하는 것은 동일하지만, 지속적으로 확인하여 사용자의 위치가 변경될 때마다 지정된 콜백 함수를 호출한다.
- navigator.geolocation.clearWatch()
  - 사용자의 위치 정보를 수집하는 작업을 중단한다. 이 메서드의 전달인자는 watchPosition()을 호출한 다음 반환받은 숫자 값이어야 한다.
- pos.coords.latitude : 적도의 북쪽 기준 각도인 위도
- pos.coords.longitude : 그리니치 천문대의 동쪽 기준 각도인 경도
- pos.coords.accuracy : 미터 단위의 정확도
```js
    function getmap(){
        if(!navigator.geolocation) throw "위치 정보가 지원되지 않습니다.";
        
        var img = document.createElement("img");
        navigator.geolocation.getCurrentPosition(setMapURL);
        return img;
        
        function setMapURL(pos){

            var latitude = pos.coords.latitude;
            var longtitude = pos.coords.longitude;
            var accuracy = pos.coords.accuracy;

            console.log(latitude);
            console.log(longtitude);
            console.log(accuracy);
            /* url에 API key 값을 추가해 주어야 함.
            var url = "http://maps.google.com/maps/api/staticmap" +
                "?center=" + latitude + "," + longtitude + 
                "&size=640x640&sensor=false";

            var zoomlevel=20;
            if(accuracy > 80)
                zoomlevel -= Math.round(Math.log(accuracy/50)/Math.LN2);
            url += "&zoom=" + zoomlevel;

            img.src = url;
            */
        }
    }

    getmap();
```
- 위도, 경도, 미터 단위의 정확도를 바탕으로 구글맵에 검색
  - www.google.com/maps/@37.4923615,127.02928809999999,1531m
- 해당 위치를 이용해 구글맵, 네이버맵 등의 API를 사용하여 지도에 표시할 수 있다.
- Google 지도 플랫폼에서 API Key 받기
  - https://nearplace.com/blog/how-to-generate-google-map-api-key-for-free/

### 히스토리 관리
- pushState() : 브라우징 히스토리에 상태를 추가한다.
  - 첫 번째 인자 : 문서의 현재 상태를 복원하는데 필요한 정보를 포함한 객체
  - 두 번째 인자 : 최신 브라우저에서 해당 값은 무시되므로 빈칸으로 둔다.
  - 세 번째 인자 : 부가적으로 현태 상태의 위치를 출력하는 URL
- **여기서 주의할 점은 URL만 변경되는 것이고 실제로 요청이 되는것은 아니다!**
```js
history.pushState({ data: 'pushpush' }, '', '/pushpush');
// 뒤로가기 버튼이 활성화
```
- replaceState() : pushState()와 인자가 동일하고, 브라우징 히스토리에 새 상태를 추가하는 대신 현재 히스토리의 상태를 교체하는 역할을 한다.
```js
history.replaceState({ data: 'replace' }, '', '/replace');
```
- 버튼 클릭시 URL이 3번째 인자로 바뀌고, 첫번째 인자의 오브젝트 타입의 데이터가 추가된다.
- 웹 브라우저의 뒤로가기 혹은 앞으로가기를 누르면 popstate 이벤트가 호출된다.
  - 이때 history.state를 이용해 각 메서드에서 첫번째로 추가한 오브젝트 타입의 인자 값을 얻을 수 있다.
```js
  window.addEventListener('popstate', function () {
    console.log('popstate', history.state);
  });
```
- 실제 요청이 되는것도 아닌데 어디에 사용하나?
  - Ajax를 사용할 경우 페이지가 재로드 되지 않기 때문에 뒤로가기 버튼에 대한 적절한 처리가 불가능
  - popstate 이벤트에서 Ajax 요청 처리
  - Ajax + pushState를 이용한 기술 : pjax에 관한 예제
  - https://github.com/defunkt/jquery-pjax

### 웹 워커
- 클라이언트 측 자바스크립트의 특징 중 하나는 싱글 스레드로 처리된다는 점이다.
- 이러한 싱글 스레드로 인해 오랫동안 수행되는 함수는 이벤트 순환을 정체시켜 웹브라우저가 사용자의 입력에 응답하지 않게 만들 것이다.
- 웹 워커는 클라이언트 측 자바스크립트의 단일 스레드를 느슨하게 만들었다.
- 웹 워커는 Window나 Document 객체에 접근이 불가능하기 때문에 DOM을 병렬적으로 수정하는 것은 여전히 불가능하지만, 동기적인 API를 사용할 수 있으며, 이벤트 루프를 정지시키거나 브라우저를 먹통으로 만들지 않고도 함수를 장시간 실행할 수 있게 되었다.
- 웹 워커는 메인 스레드와는 비동기적 메시지 기반 통신만 가능한 자족적인 실행 환경 안에 존재한다.
- Worker 객체 만들기
  - Worker() 생성자에 워커가 실행되는 자바스크립트 코드 URL을 지정한다.
  - 해당 js 파일은 독립적인 새로운 자바스크립트 실행 환경에서 구동된다.
```js
var loader = new Worker("utils/loader.js");
```
- Worker 객체를 한번 생성하면 postMessage()를 활용하여 워커로 데이터를 전송할 수 있다.
  - 메인 스레드에서 전송한 데이터를 워커 생성자에서 설정한 js가 받아서 처리한다.
```js
loader.postMessage("file.txt");
```
- Worker 객체의 message 이벤트를 구독하면 워커로부터의 메시지를 전달받을 수 있다.
```js
worker.onmessage = function(e){
	var message = e.data;	// 이벤트로부터 메시지를 가져온다.
```
- 워커가 예외를 발생시켰으나 내부적으로 처리되지 않았다면 onerror를 통해 받는다.
```js
worker.onerror = function(e){
	console.log(e.filename + "파일의 " + e.lineno + "에서 " + e.message + "오류 발생");
```
- terminate() 메서드는 워커 스레드를 강제로 종료한다.
```js
worker.terminate();
```
- 코드를 작성하기 전에 importScripts를 이용해 js 파일들을 미리 불러올 수 있다.
  - 해당 메서드는 동기 방식으로 동작하므로 지정된 모든 스크립트가 로드되고 실행되기 전까지는 반환되지 않는다.
  - 해당 메서드가 반환되면 콜백 또는 이벤트 핸들러도 필요 없이 js들을 즉시 사용할 수 있다.
```js
importScripts("collections/Set.js", "collections/Map.js", "utils/base64.js");
```
- 웹 워커 예제 (html 부분)
  - Worker 객체를 생성하며, 생성자에 js를 매개변수로 넣는다.
  - postMessage를 이용해 해당 js에 이미지에 대한 정보를 넘긴다.
  - js에서 데이터 처리가 끝나면 worker.onmessage를 이용해 데이터를 받는다.
```js
// test.html
    <img id="demoImage" src="./demo.png" width="100" height="100"/>
<script>

    function smear(img){
        var canvas = document.createElement("canvas");
        canvas.width = img.width;
        canvas.height = img.height;
        
        var context = canvas.getContext("2d");
        context.drawImage(img, 0, 0);
        var pixels = context.getImageData(0, 0, img.width, img.height);
        
        var worker = new Worker("SmearWorker.js");
	
        worker.postMessage(pixels);
        
        worker.onmessage = function(e){
            var smeared_pixels = e.data;
            context.putImageData(smeared_pixels, 0, 0);
            img.src = canvas.toDataURL();
            worker.terminate();
        }
    }
    
    smear(document.getElementById("demoImage"));
    
</script>
```
- 웹 워커 예제 (js 부분)
  - Worker 객체에서 postMessage로 넘겨준 데이터를 e.data로 받는다.
  - e.data를 이용해 smear 메서드를 처리한다.
  - onmessage에서 postMessage를 이용해 처리를 완료한 데이터를 다시 전달한다.
```js
// SmearWorker.js
onmessage = function(e){
    postMessage(smear(e.data));
}

function smear(pixels){
    var data = pixels.data;
    var width = pixels.width;
    var height = pixels.height;
    var n=10, m=n-1;
    for(var row=0; row<height; row++){
        var i = row*width*4+4;
        for(var col=1; col<width; col++, i+=4){
            data[i] = (data[i]+data[i-4]*m)/n;
            data[i+1] = (data[i+1]+data[i-3]*m)/n;
            data[i+2] = (data[i+2]+data[i-2]*m)/n;
            data[i+3] = (data[i+3]+data[i-1]*m)/n;
        }
    }
    
    return pixels;
}
```

### 타입 배열
- 자바스크립트의 배열은 원소로 어떠한 값도 들어갈 수 있고, 동적이다.
- 하지만 실행시간과 메모리를 더 효율적으로 사용하기 위해 타입 배열도 지원한다.
- 타입 배열의 원소는 모두 숫자이며, 길이가 고정적이고, 배열이 생성된 시점에 원소들은 0으로 초기화된다.
- 그래픽이나 수학적인 계산을 위한 수를 작업할 때 타입 배열의 연산 속도가 훨씬 빠르다.
- 사용할 수 있는 생성자는 다음과 같다.
  - Int8Array() : 부호 있는 바이트
  - Uint8Array() : 부호 없는 바이트
  - Int16Array() : 부호 있는 작은 16비트 정수
  - Uint16Array() : 부호 없는 작은 16비트 정수
  - Int32Array() : 부호 있는 32비트 정수
  - Uint32Array() : 부호 없는 32비트 정수
  - Float32Array() : 32비트 부동 소수점 값
  - Float64Array() : 64비트 부동 소수점 값
```js
    var bytes = new Uint8Array(1024);
    for(var i=0; i<bytes.length; i++)
        bytes[i] = i & 0xFF;
    var copy = new Uint8Array(bytes);
    var ints = new Int32Array([0,1,2,3]);
    
    var matrix = new Float64Array(9); // 3x3 행렬
    var 3dPoint = new Int16Array(3); // 3D 공간 지점
    var rgba = new Uint8Array(4); // 4바이트 RGBA 픽셀 값
    var sudoku = new Uint8Array(81); // 9x9 스도쿠 보드
```

### Blob
- 일련의 데이터를 처리하거나 간접 참조하는 객체
- 대개 바이트의 크기를 알아내거나, 해당 MIME 타입이 무엇인지 요청하며, 데이터를 작은 Blob으로 잘게 나누는 등의 작업에 사용된다.
- 즉, 데이터 자체라기보다는 데이터를 간접적으로 접근하기 위한 객체인 것이다.
- Blob 만들기
  - new Blob을 이용해 생성자를 넣는다.
  - 첫 번째 인자는 데이터의 배열, 두 번째 인자는 옵션
```js
    var htmlCode = ['< !doctype html>','Hello, World!'];
    var htmlBlob = new Blob(htmlCode, {type:'text/html'});
```
- blob 프로퍼티
  - size와 type이 있다.
```js
    console.log(htmlBlob.size); // 29
    console.log(htmlBlob.type); // text/html
```
- blob URL
  - blob 객체를 만들면 해당 객체에 가상의 URL을 부여할 수 있다.
  - URL.createObjectURL의 파라미터로 blob 객체를 전달한다.
```js
console.log(URL.createObjectURL(htmlBlob));
// blob:http://127.0.0.1:64427/65a35443-262c-4e92-9fb1-96bde43a8523
```
- blob에 할당한 URL을 바탕으로 파일처럼 다룰 수 있다.
  - 아래의 예제는 blob에 URL을 할당한 후 하이퍼링크로 해당 파일을 다운로드 하는 예제이다.
```js
<a id="myButton" href="#">Download JSON</a>


    // dummy json data to save
    var data = { x: 42, s: "hello, world", d: new Date() }

    document.getElementById('myButton').onclick = function(event){

        var json = JSON.stringify(data),
            blob = new Blob([json], {type: "octet/stream"}),
            url = window.URL.createObjectURL(blob);

        this.href = url;
        this.target = '_blank';

        // target filename
        this.download = 'my-download.json';
    }
```
- File에서 slice 메서드
  - File에서 slice 메서드는 blob 객체를 반환한다.
  - 파일의 거대한 데이터에 대해 slice를 이용해 내용의 일부분을 읽을 수 있다.
  - Blob의 내용을 읽어들이기 위해 비동기 처리인 FileReader와 함께 사용하면 효과적이다.
  - 아래의 예제는 파일의 첫 4바이트만 읽어 들여서 파일의 타입을 알아내는 방법이다.
```js
    function typefile(file){
        var slice = file.slice(0,4); // blob 객체 반환
        var reader = new FileReader();
        reader.readAsArrayBuffer(slice); // 파일의 조각을 읽어 들임.
        reader.onload = function(e){
            var buffer = reader.result;
            var view = new DataView(buffer);
            var magic = view.getUint32(0, false);
            switch(magic){
                case 0x89504E47:
                    file.verified_type = "image/png";
                    break;
                case 0x47494638:
                    file.verified_type = "image/gif";
                    break;
                case 0x25504446:
                    file.verified_type = "application/pdf";
                    break;
                case 0x504b0304:
                    file.verified_type = "application/zip";
                    break;
            }
            console.log(file.name, file.verified_type);
        };
    }
```