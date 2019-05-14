### CH21. 미디어와 그래픽 스크립팅

#### 1. 이미지 스크립팅
>이미지 롤오버나 이와 유사한 동작이 유용하려면 즉각 반응해야 한다.
즉, 필요한 이미지가 브라우저 캐시에 '이미' 존재할 필요가 있다. 이미지를 강제로 캐시하기 위해, Image() 생성자를 사용하여 보이지 않는 화면 영역에 이미지 객체를 만들 수 있다. 동일한 url을 화면 상에서 사용하면, 네트워크를 통하지 않고 브라우저의 캐시에서 불러올 수 있다.

```html
<script>(new Image()).src="image_rollover.png";</script>
    <img src="image.png"
    onmouseover="this.src='image_rollover.png'"
    onmouseout="this.src='image.png'" width="300" height="200">
```

#### 2. 오디오와 비디오 스크립팅

HTML5를 지원하는 브라우저는 플래시 플러그인 없이도 아래와 같이 소리나 영상을 포함 시킬 수 있다.
```js
<audio src="test.mp3"/>
<video src="news.mov" width=320 height=240/>
```
 하지만, 실제로는 브라우저 제조사들이 오디오,비디오 표준 코덱을 모두 지원하는 데 동의하지 않았으므로 <source>문서 요소를 사용하여 미디어 출처를 지정해야만 한다.

 ```js
<audio id="music">
  <source src="music.mp3" type="audio/mpeg">
  <source src="music.ogg" type='audio/ogg; codec="vorvis"'>
</audio>
 ```

 - controls 속성
 : controls 속성이 주어지거나 해당 자바스크립트 프로퍼티가 true로 설정되면, 재생과 중단 버튼, 음량 제어 등의 재생 컨트롤들을 출력

_2-1.타입 선택과 로딩_
- canPlayType() 메서드 : 미디어의 MIME타입을 전달하면 재생할 수 없을 경우 빈 문자열을, 재생이 가능하다면 "maybe" 또는 "probably"를 반환한다.

_2-2. 미디어 재생 장치 제어하기_
- paly() , pause() 메서드 : 재생을 시작하고 중단
- currentTime : 미디어가 걸너뛰어야 할 시간 값을 초 단위로 지정한다.
- volume : 0과 1(최대음량) 사이의 숫자 값으로 재생 음량을 지정한다.
- muted : true이면 음소거한다.
- playbackRate : 미디어의 속도를 지정한다. 1.0이면 표준속도, 1보다 클수록 빨라지고 0rhk 1사이의 값이면 느려진다.
- loop : 미디어 반복적 재생
- preload 프로퍼티 : 미디어 내용을 사용자가 재생하기 전에 미리 불러올리, 얼마나 불러올지 지정 (미지정시 보통 메타 데이터를 로드함)
- autoplay : 미디어가 충분히 다운로드 되면 자동으로 재생을 시작해야 하는지 지정

_2-3. 미디어 상태 가져오기_

미디어와 플레이어의 현재 상태를 알려주는 프로퍼티들
- paused : 플레이어가 일시정지 되면 true
- seeking: 플레이어가 새로운 재생 위치로 건너뛰면 ture
- ended : 마지막에 도달하여 재생이 중된되면 ture
- duration : 재생시간 (초)
- initalTime : 미디어의 시작 시간(초) - 재생시간이 정해진 미디어는 보통 0

TimeRanges객체 : 해당 범위를 숫자로 나타내는 length와 시작과 종료 시간을 초 단위로 반환하는 start() , end() 메서드가 있음
- played : 재생된 범위를 돌아다니거나 그 범위를 반환
- buffered : 버퍼링된 영영억 돌아다니거나 그 범위를 반환
- seekable : 탐색 가능한 영역을 돌아다니거나 그 범위를 반환

=> 얼마나 재생되었고 버퍼링 되었는지에 따라 currentTime과 duration을 나타내는 진행 상태바를 구현할 때 이 프로퍼티들을 사용할 수 있다.

```js
//미디어가 시작부터 버퍼링 되었을 때 몇 퍼센트나 버퍼링 되었는지
var percent_loaded = Math.floor(song.buffered.end(0) / song.duration * 100);
```

- readyState : 미디어 데이터가 얼마나 로드되었는지
- networkStat : 미디어 문서 요소가 네트워크를 사용 중인지
- error : 에러가 없다면 null, 있다면 오류 코드를 가리키는 상수를 정의

_2-4. 미디어 이벤트 p757참고_  

#### 3. SVG : Scalable Vector Graphics
>SVG는 그래픽을 위한 XML 문법이다. vector는 픽셀 값을 모은 gif, jpeg, png 같은 비트맵 이미지와는 근본적으로 다른 이미지를 나타낸다.

- 임의의 곡선, 텍스트, 애니메이션을 포함한 기본 도형 뿐 아니라, 동작을 추가하고 정보를 표현하기 위한 자바스크립트와 CSS 스타일시트까지 포함할 수 있다.

- 스크립트로 이미지를 만들 수 있다. SVG도 XML문법의 일종이므로, XHTML문서에 내장할 수 있다. html5에서는 네임스페이스 정의나 태그 접두사 없이 html파일에 직접 SVG 마크업을 넣을 수 있다.

- SVG는 XML 문법이므로 SVG그래픽을 그리는 일이란 결국, 적절한 XML 요소를 생성하는 DOM을 사용하는 것과 같다.

- p760~765 예제 참고



#### 4.\<canvas> 그래픽

> \<canvas> 문서 요소와 SVG 사이의 가장 중요한 차이점은, 캔버스는 메서드 호출을 통해 그림을 생성하고, SVG는 XML문서 요소 트리를 만들어 그림을 생성한다는 점이다.

- svg 장점 : 기술된 xml 문서에서 문서 요소를 제거함으로써 손쉽게 수정 가능.
  \<canvas>로 생성한 동일한 그래픽 문서 요소를 지우기 위해서는, 종종 그림을 지우고 처음부터 다시 그려야 한다.
- \<canvas> 요소 장점 : 캔버스 드로잉 API는 자바스크립트 기반이며 SVG 문법과는 달리 상대적으로 크기가 작다. (4부 Cancas, CancasRenderingContext2D 참고)


_4.1 직선 그리기와 폴리곤 채우기_
- 컨버스에 직선을 그리고 직선으로 이어진 영역을 채우기 위해서는 경로를 정의해야 한다.
1. 새 경로는 beginPath() 메서드를 사용해서 시작하고,
2. 새 하위 경로는 moveTo()메서드를 사용해서 시작.
3. lineTo()를 호출하여 새 지점으로 직선을 연결
4. 두 선분을 눈에 보이게 그리려면 stroke() 메서드 호출
5. 해당 선분으로 정의된 영역을 채우려면 fill() 호출
+ closePath() : 경로의 종료점을 시작점과 연결
- 경로를 종료하고 다른 경로를 시작하고 싶을 때는, 꼭 beginPath()를 호출해야 한다.

_4.2 그래픽 속성_
fill()과 strote()에서 사용할 색상과 stroke()로 그릴 선의 두께를 지정하는 그래픽 속성. 주의할 점은 이 속성이 fill()과 stroke()의 전달 인자가 아니라, 캔버스의 전역 그래픽 상태 값의 일부라는 사실이다. => 그리기 명령과 그래픽 상태 값을 분리
- 그래픽 속성 api p772 참고
- save() : 현재 그래픽 상태를 상태 저장 스택으로 밀어 넣음
- restore() : 스택에서 꺼내 와서 가장 최근 저장 상태로 복구

_4.3 캔버스 면적과 좌표_
기본 캔버스 좌표계에서는 캔버스 좌측 상단 모서리(0,0)을 기준으로 X좌표를 증가시키면 오른쪽으로 이동하고, Y 좌표를 증가시키면 화면의 하단으로 이동

_4.4 좌표계 변형_
좌표계는 4.3에서 설명한 것이 기본이지만 기반의 상하좌우 이동 및 축 회전 등도 가능
- setTransform()
- translate()
- scale()

위 메소드를 재귀적으로 사용해 그리면 눈송이 프랙탈을 그릴 수 있다.

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory&fname=http%3A%2F%2Fcfile21.uf.tistory.com%2Fimage%2F210F3A43541FBC9107323C)

_4.5 커브 그리기와 채우기_
- arc() : 하위 경로에 호를 추가
- artTo()
- bezierCurveTo()
- quadraticCurveTo()

_4.6 사각형_
- rect() : 지정한 사각형을 현재 경로의 하위 경로에 추가

_4.7 색상, 투명도, 그레이디언트, 패턴_

- 반복된 배경이미지마 선형, 원형 그레이디언트 색상으로 선을 그리고 면을 칠하기 위해서는 CanvasPattern 이나 CanvasGradient 객체를 사용
- 반투명으로 만들려면 globalAlpha 속성을 사용


_4.8 선 그리기의 속성_
- lineWidth
- lineCap : 선의 끝 모양
- lineJoin : 두 선이 만나는 교차점의 모양 지정
- miterLimit

_4.9 텍스트_
- fillText(text, x좌표, y좌표, 최대폭)
- strokeText(text, x좌표, y좌표,  최대폭) : 글자별로 외곽선을 그림
4번째 인자는 선택적이다.
- measureText()

_4.10 클리핑_
- clip() : 클리핑 영역을 정의 , clip()으로 영역을 잘래낸 다음부터 영역 바깥쪽에는 그려지지 않는다.
_4.11 그림자_
그핌자 처리를 위한 그래픽 속성이 4개가 있다.
- shadowColor : 그림자 색 지정
- shadowOffsetX ,shadowOffsetY : 그림자의 X,Y오프셋 지정 (기본값0)
- shadowBlur : 그림자의 경계선을 뿌옇게 만든다.(기본값0) 값이 커질수록 뿌옇게 변한다.

_4.12 이미지_

캔버스 api는 백터 그래픽(경로, 선 등) 뿐만 아니라 비트맵 이미지도 지원한다.
- drawImage() : 캔버스에 원본 이미지의 픽셀을 복제, 필요하다면 이미지 픽셀의 크기를 조절하고 회전시킬 수 있다
- 전달인자를 3개, 5개, 9개로 실행할 수 있다.
- 전달인자 3개인 경우
  - 이미지 전체가 캔버스로 복제
  - arg1 : 원본 이미지(<img> , Image()생성자로 만든 객체 , \<canvas>, \<video> 요소 가능)
  - arg2, arg3 : 이미지 좌측 상단 끝의 캠퍼스 내 X,Y 좌표
- 전달인자 5개인 경우
  - 캔버스에서 이미지를 그릴 목표 영역을 정의
  - arg4, arg5 : width, height
  - 이미지를 그릴 좌측 상단 끝은 (x,y) , 우측하단은 (x+width, y+height)
- 전달인자 9개인 경우
  - 원본영역과 목표영역을 모두 지정해서, 원본 이미지 전체가 아닌 원본 영역에서만 픽셀을 복제

- toDataURL() 메서드를 이용해서 캔버스 내용을 이미지로 추출할 수도 있다. 캔버스의 내용을 문자열로 인코딩한 png 이미지를 반환함(보안을 위해 원본의 'origin-clean'이 true여야 한다)





_4.13 합성_
불투명 요소를 그릴 때는 이미 있는 픽셀들이 교체될 뿐이지만, 반투명 요소를 그리면 새로운 픽셀이 이전 픽셀과 합쳐진다. 이렇게 반투명인 새로운 원본 픽셀과 이전 대상 픽셀의 결합 과정을 '합성' 이라고 한다.
- 합성의 종류 지정 : globalCompositeOperation
- source-over
- destination-onmouseover
- copy

_4.14 픽셀 조작_
이미지 파일 조작을 구현할 때 유용
- getImageData()
- createImageDate()
- putImageData()

_4.15 충돌 감지_
- isPointInPath() : 지정한 좌표가 현재 경로 안쪽에 들어 있는지를 알아냄 (boolean값 리턴)
- getImageData() : 마우스 좌표 아래의 픽셀이 칠해져 있는지 확인



_4.16 캔버스 예제: Sparklines_
