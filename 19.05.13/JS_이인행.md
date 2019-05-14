# HTML5 API

### HTML5
- HTML �԰��� �Ϻκ����� ���ߵǰ� �԰�ȭ�� �� ���ø����̼� ����� ��ü������ ���´� ���
- �� ����� �� �� ���������� ���´� ���� '���� �� �÷���'�̴�.

### Geolocation API
- �ڹٽ�ũ��Ʈ ���α׷��� ������� ���� ��ġ�� ���������� ��û�� �� �ֵ��� ���ش�.
- �߿��� ���� ������ ���õǾ� �����Ƿ�, ������������ ������� ����� �޵��� �ϰ� �ִ�.
- navigator.geolocation.getCurrentPosition()
  - ������� ���� ��ġ�� ��û�Ѵ�.
- navigator.geolocation.watchPosition()
  - ���� ��ġ�� ��û�ϴ� ���� ����������, ���������� Ȯ���Ͽ� ������� ��ġ�� ����� ������ ������ �ݹ� �Լ��� ȣ���Ѵ�.
- navigator.geolocation.clearWatch()
  - ������� ��ġ ������ �����ϴ� �۾��� �ߴ��Ѵ�. �� �޼����� �������ڴ� watchPosition()�� ȣ���� ���� ��ȯ���� ���� ���̾�� �Ѵ�.
- pos.coords.latitude : ������ ���� ���� ������ ����
- pos.coords.longitude : �׸���ġ õ������ ���� ���� ������ �浵
- pos.coords.accuracy : ���� ������ ��Ȯ��
```js
    function getmap(){
        if(!navigator.geolocation) throw "��ġ ������ �������� �ʽ��ϴ�.";
        
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
            /* url�� API key ���� �߰��� �־�� ��.
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
- ����, �浵, ���� ������ ��Ȯ���� �������� ���۸ʿ� �˻�
  - www.google.com/maps/@37.4923615,127.02928809999999,1531m
- �ش� ��ġ�� �̿��� ���۸�, ���̹��� ���� API�� ����Ͽ� ������ ǥ���� �� �ִ�.
- Google ���� �÷������� API Key �ޱ�
  - https://nearplace.com/blog/how-to-generate-google-map-api-key-for-free/

### �����丮 ����
- pushState() : ����¡ �����丮�� ���¸� �߰��Ѵ�.
  - ù ��° ���� : ������ ���� ���¸� �����ϴµ� �ʿ��� ������ ������ ��ü
  - �� ��° ���� : �ֽ� ���������� �ش� ���� ���õǹǷ� ��ĭ���� �д�.
  - �� ��° ���� : �ΰ������� ���� ������ ��ġ�� ����ϴ� URL
- **���⼭ ������ ���� URL�� ����Ǵ� ���̰� ������ ��û�� �Ǵ°��� �ƴϴ�!**
```js
history.pushState({ data: 'pushpush' }, '', '/pushpush');
// �ڷΰ��� ��ư�� Ȱ��ȭ
```
- replaceState() : pushState()�� ���ڰ� �����ϰ�, ����¡ �����丮�� �� ���¸� �߰��ϴ� ��� ���� �����丮�� ���¸� ��ü�ϴ� ������ �Ѵ�.
```js
history.replaceState({ data: 'replace' }, '', '/replace');
```
- ��ư Ŭ���� URL�� 3��° ���ڷ� �ٲ��, ù��° ������ ������Ʈ Ÿ���� �����Ͱ� �߰��ȴ�.
- �� �������� �ڷΰ��� Ȥ�� �����ΰ��⸦ ������ popstate �̺�Ʈ�� ȣ��ȴ�.
  - �̶� history.state�� �̿��� �� �޼��忡�� ù��°�� �߰��� ������Ʈ Ÿ���� ���� ���� ���� �� �ִ�.
```js
  window.addEventListener('popstate', function () {
    console.log('popstate', history.state);
  });
```
- ���� ��û�� �Ǵ°͵� �ƴѵ� ��� ����ϳ�?
  - Ajax�� ����� ��� �������� ��ε� ���� �ʱ� ������ �ڷΰ��� ��ư�� ���� ������ ó���� �Ұ���
  - popstate �̺�Ʈ���� Ajax ��û ó��
  - Ajax + pushState�� �̿��� ��� : pjax�� ���� ����
  - https://github.com/defunkt/jquery-pjax

### �� ��Ŀ
- Ŭ���̾�Ʈ �� �ڹٽ�ũ��Ʈ�� Ư¡ �� �ϳ��� �̱� ������� ó���ȴٴ� ���̴�.
- �̷��� �̱� ������� ���� �������� ����Ǵ� �Լ��� �̺�Ʈ ��ȯ�� ��ü���� ���������� ������� �Է¿� �������� �ʰ� ���� ���̴�.
- �� ��Ŀ�� Ŭ���̾�Ʈ �� �ڹٽ�ũ��Ʈ�� ���� �����带 �����ϰ� �������.
- �� ��Ŀ�� Window�� Document ��ü�� ������ �Ұ����ϱ� ������ DOM�� ���������� �����ϴ� ���� ������ �Ұ���������, �������� API�� ����� �� ������, �̺�Ʈ ������ ������Ű�ų� �������� �������� ������ �ʰ� �Լ��� ��ð� ������ �� �ְ� �Ǿ���.
- �� ��Ŀ�� ���� ������ʹ� �񵿱��� �޽��� ��� ��Ÿ� ������ �������� ���� ȯ�� �ȿ� �����Ѵ�.
- Worker ��ü �����
  - Worker() �����ڿ� ��Ŀ�� ����Ǵ� �ڹٽ�ũ��Ʈ �ڵ� URL�� �����Ѵ�.
  - �ش� js ������ �������� ���ο� �ڹٽ�ũ��Ʈ ���� ȯ�濡�� �����ȴ�.
```js
var loader = new Worker("utils/loader.js");
```
- Worker ��ü�� �ѹ� �����ϸ� postMessage()�� Ȱ���Ͽ� ��Ŀ�� �����͸� ������ �� �ִ�.
  - ���� �����忡�� ������ �����͸� ��Ŀ �����ڿ��� ������ js�� �޾Ƽ� ó���Ѵ�.
```js
loader.postMessage("file.txt");
```
- Worker ��ü�� message �̺�Ʈ�� �����ϸ� ��Ŀ�κ����� �޽����� ���޹��� �� �ִ�.
```js
worker.onmessage = function(e){
	var message = e.data;	// �̺�Ʈ�κ��� �޽����� �����´�.
```
- ��Ŀ�� ���ܸ� �߻��������� ���������� ó������ �ʾҴٸ� onerror�� ���� �޴´�.
```js
worker.onerror = function(e){
	console.log(e.filename + "������ " + e.lineno + "���� " + e.message + "���� �߻�");
```
- terminate() �޼���� ��Ŀ �����带 ������ �����Ѵ�.
```js
worker.terminate();
```
- �ڵ带 �ۼ��ϱ� ���� importScripts�� �̿��� js ���ϵ��� �̸� �ҷ��� �� �ִ�.
  - �ش� �޼���� ���� ������� �����ϹǷ� ������ ��� ��ũ��Ʈ�� �ε�ǰ� ����Ǳ� �������� ��ȯ���� �ʴ´�.
  - �ش� �޼��尡 ��ȯ�Ǹ� �ݹ� �Ǵ� �̺�Ʈ �ڵ鷯�� �ʿ� ���� js���� ��� ����� �� �ִ�.
```js
importScripts("collections/Set.js", "collections/Map.js", "utils/base64.js");
```
- �� ��Ŀ ���� (html �κ�)
  - Worker ��ü�� �����ϸ�, �����ڿ� js�� �Ű������� �ִ´�.
  - postMessage�� �̿��� �ش� js�� �̹����� ���� ������ �ѱ��.
  - js���� ������ ó���� ������ worker.onmessage�� �̿��� �����͸� �޴´�.
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
- �� ��Ŀ ���� (js �κ�)
  - Worker ��ü���� postMessage�� �Ѱ��� �����͸� e.data�� �޴´�.
  - e.data�� �̿��� smear �޼��带 ó���Ѵ�.
  - onmessage���� postMessage�� �̿��� ó���� �Ϸ��� �����͸� �ٽ� �����Ѵ�.
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

### Ÿ�� �迭
- �ڹٽ�ũ��Ʈ�� �迭�� ���ҷ� ��� ���� �� �� �ְ�, �����̴�.
- ������ ����ð��� �޸𸮸� �� ȿ�������� ����ϱ� ���� Ÿ�� �迭�� �����Ѵ�.
- Ÿ�� �迭�� ���Ҵ� ��� �����̸�, ���̰� �������̰�, �迭�� ������ ������ ���ҵ��� 0���� �ʱ�ȭ�ȴ�.
- �׷����̳� �������� ����� ���� ���� �۾��� �� Ÿ�� �迭�� ���� �ӵ��� �ξ� ������.
- ����� �� �ִ� �����ڴ� ������ ����.
  - Int8Array() : ��ȣ �ִ� ����Ʈ
  - Uint8Array() : ��ȣ ���� ����Ʈ
  - Int16Array() : ��ȣ �ִ� ���� 16��Ʈ ����
  - Uint16Array() : ��ȣ ���� ���� 16��Ʈ ����
  - Int32Array() : ��ȣ �ִ� 32��Ʈ ����
  - Uint32Array() : ��ȣ ���� 32��Ʈ ����
  - Float32Array() : 32��Ʈ �ε� �Ҽ��� ��
  - Float64Array() : 64��Ʈ �ε� �Ҽ��� ��
```js
    var bytes = new Uint8Array(1024);
    for(var i=0; i<bytes.length; i++)
        bytes[i] = i & 0xFF;
    var copy = new Uint8Array(bytes);
    var ints = new Int32Array([0,1,2,3]);
    
    var matrix = new Float64Array(9); // 3x3 ���
    var 3dPoint = new Int16Array(3); // 3D ���� ����
    var rgba = new Uint8Array(4); // 4����Ʈ RGBA �ȼ� ��
    var sudoku = new Uint8Array(81); // 9x9 ������ ����
```

### Blob
- �Ϸ��� �����͸� ó���ϰų� ���� �����ϴ� ��ü
- �밳 ����Ʈ�� ũ�⸦ �˾Ƴ��ų�, �ش� MIME Ÿ���� �������� ��û�ϸ�, �����͸� ���� Blob���� �߰� ������ ���� �۾��� ���ȴ�.
- ��, ������ ��ü��⺸�ٴ� �����͸� ���������� �����ϱ� ���� ��ü�� ���̴�.
- Blob �����
  - new Blob�� �̿��� �����ڸ� �ִ´�.
  - ù ��° ���ڴ� �������� �迭, �� ��° ���ڴ� �ɼ�
```js
    var htmlCode = ['< !doctype html>','Hello, World!'];
    var htmlBlob = new Blob(htmlCode, {type:'text/html'});
```
- blob ������Ƽ
  - size�� type�� �ִ�.
```js
    console.log(htmlBlob.size); // 29
    console.log(htmlBlob.type); // text/html
```
- blob URL
  - blob ��ü�� ����� �ش� ��ü�� ������ URL�� �ο��� �� �ִ�.
  - URL.createObjectURL�� �Ķ���ͷ� blob ��ü�� �����Ѵ�.
```js
console.log(URL.createObjectURL(htmlBlob));
// blob:http://127.0.0.1:64427/65a35443-262c-4e92-9fb1-96bde43a8523
```
- blob�� �Ҵ��� URL�� �������� ����ó�� �ٷ� �� �ִ�.
  - �Ʒ��� ������ blob�� URL�� �Ҵ��� �� �����۸�ũ�� �ش� ������ �ٿ�ε� �ϴ� �����̴�.
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
- File���� slice �޼���
  - File���� slice �޼���� blob ��ü�� ��ȯ�Ѵ�.
  - ������ �Ŵ��� �����Ϳ� ���� slice�� �̿��� ������ �Ϻκ��� ���� �� �ִ�.
  - Blob�� ������ �о���̱� ���� �񵿱� ó���� FileReader�� �Բ� ����ϸ� ȿ�����̴�.
  - �Ʒ��� ������ ������ ù 4����Ʈ�� �о� �鿩�� ������ Ÿ���� �˾Ƴ��� ����̴�.
```js
    function typefile(file){
        var slice = file.slice(0,4); // blob ��ü ��ȯ
        var reader = new FileReader();
        reader.readAsArrayBuffer(slice); // ������ ������ �о� ����.
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