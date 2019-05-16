# 상태 모니터링의 기초 지식
### 변화를 알아차리기 위한 요령
- 없어야 하는 변화가 있다 : 평소와 다른 움직임을 보이는 경우 그 이유를 확인 하도록 한다.
- 있어야 하는 변화가 없다
  - 특정 시간대에 부하가 증가해야 하는데 증가하지 않는 경우 이유를 확인한다.
  - 파형이 울퉁불퉁해야 하는데 일정한 값을 유지하고 있는 경우에도 이유를 확인한다.
- 양과 단위가 다르다
  - 세로축 단위에 주의하도록 한다
  - 예를들어 Mbps(bit per second)와 MB/s(byte per second)와 같이 단위의 차이가 날수도 있기 때문에 주의해서 관찰한다.
- Polling형 (Pull형)
  - 모니터링 툴 쪽에서 정보를 수집하기 위해 액세스하는 타입이다.
  - 데이터 취득 타이밍은 모니터링 툴 쪽에 의존하며 모니터링 툴 쪽의 처리 성능에 따라 취득 타이밍이 결정된다.
  - ex) Cacti
- Push형
  - 모니터링 툴 쪽으로 데이터를 송신하는 타입이다.
  - 데이터 취득 타이밍은 모니터링 툴쪽의 처리 성능에 의존하지 않고, 일정한 타이밍에 취득할 수 있다.
  - ex) GrowthForecast

# 상태 모니터링 데이터를 읽는 방법 - OS
- Linux OS용 Percona Linux Monitoring Template for Cacti의 그래프 읽기
### CPU 관련
- CPU User
  - 사용자 영역
  - PHP 등 프로그램 처리에 이용한 부분
- CPU System
  - 메모리 액세스 등 커널 영역에서의 CPU 이용률
  - 보통 CPU User의 10~20% 정도이지만, 일괄처리와 같은 대량의 메모리 액세스나 대량의 파일서버 액세스 등에 따라 증가하는 경우가 있다.
- CPU Nice : 운용계통의 일괄처리 등 nice 명령을 이용하여 우선순위를 조정한 특별한 처리에서 이용한 부분
- CPU lowait
  - I/O 대기의 CPU 이용률
  - 최대 성능이 높게 설정되어 있더라도 서버 구성에 따라 설정한 성능에 못미치게 나오는 기기 구성도 있으니 주의할 필요가 있다
  - 대부분의 서버에서 해당 부분이 보틀넥을 일으키는 요인이 되기 쉬우므로 통상적으로 거의 0% 또는 많아도 30% 정도로 유지하는 것이 좋다.
- CPU Irq, CPU Softirq
  - 인터럽트 처리의 CPU 이용률
  - 오래된 커널에서는 다수의 코어에 분산될 수 없기 때문에 보틀넥이 되는 경우가 있다.
- CPU Steal, CPU Guest : 가상머신이 가로챈, 이용한 만큼의 CPU 이용률

### Context Switches
- CPU에서 처리할 프로세스 등의 전환 횟수
- 프로세스의 병렬도가 높고, 단위 시간당 처리가 많은 시스템에서는 해당 값이 커진다.
- 스레드 모델이 아닌 프로세스 모델의 웹 서버나 애플리케이션 서버, 집적도가 높은 가상화 기반 서버 등에서 값이 커지기 쉽다.

### Forks
- 프로세스 생성 처리를 하는 Fork 실행 횟수를 나타낸다.
- 해당 그래프의 값이 항상 큰 경우에는 불필요한 프로세스를 생성하거나 외부 프로세스를 호출하고 있지는 않은지 또는 라이브러리 호출로 변경할 수는 없는지 검토하도록 한다.

### Interrupts
- 네트워크 송수신 등에 따른 인터럽트의 수
- 네트워크 송수신이 많으면 증가한다.
- Irq와 Softirq 등을 확인하여 문제가 없는지 확인한다.

### Memory
- Memcached : 캐시로 사용 중인 메모리 용량
- Membuffer : 버퍼로 사용 중인 메모리 용량
- Memshared : 공유 메모리로 사용 중인 메모리 용량
- Memfree : 전혀 사용하고 있지 않은 메모리 용량
  - Memcached가 점차 증가하여 Memfree가 0이 되는 경우가 자주 생기는데, 이것은 Linux 커널의 기본적이며 정상적인 동작이다.

### Swap Usage
- Swap은 전체 이용량이 아니라 Swap에 쓰거나 읽는 양에 주목하는 것이 좋다.
- Pswpout : Swap에 쓰기, 필요해지면 읽어 들인다.
- Pswpin : Swap에서 읽기, 확보되었으나 사용되지 않은 메모리가 Swap으로 옮겨진다.
  - 서버 성능 측면에서 생각해 봤을 때 Pswpout과 Pswpin이 자주 발생하는 것은 좋지 않은 상태이다.
  - Pswpin이 자주 발생하고 있다면 서버의 메모리 용량이 부족한 것이다.
  - 이때는 서버에 동작하고 있는 프로그램의 메모리 할당을 조절하거나 서버의 성능을 업그레이드 하는 것이 좋다.

### Disk Elapsed IO Time(ms)
- IO Time : I/O 시간
- IO Time Weighted : I/O의 총 소요 시간
  - IO Time에 비해 해당 값이 크면 디스크 I/O의 성능이 부족하다고 할 수 있다
  - 이때는 I/O 자체를 줄이거나 디스크 I/O의 성능을 높여 해당 값이 낮아지도록 튜닝한다.
- Disk IOPS
  - Io Ops : I/O 동작 횟수
- Disk Operations
  - Reads : 디스크 읽기의 요구 횟수
  - Writes : 디스크 쓰기의 요구 횟수
  - Reads + Writes = Io Ops
  - Reads Merged : 통합된 디스크 읽기의 요구 횟수
  - Writes Merged : 통합된 디스크 쓰기의 요구 횟수
- Disk Read/Write Time
  - Time Spent Reading : 읽기에 소요된 시간
  - Time Spent Writing : 쓰기에 소요된 시간
  - 앞에서의 DISK IOPS나 Disk Operations와 함께 확인하여 디스크 I/O의 성능이 부족한지에 대한 상황을 확인한다.
- DISK Read/Write Time per IO Request
  - 앞의 Reads, Writes, Time Spent Reading, Time Spent Writing으로부터 산출된다.
  - Read Time per IO : 1I/O당 읽기의 소요 시간
  - Write Time per IO : 1I/O당 쓰기의 소요 시간
  - 앞에서의 DISK IOPS나 Disk Operations와 함께 확인하여 디스크 I/O의 성능이 부족한지에 대한 상황을 확인한다.
- DISK Sectors Read/Written
  - Sectors Read : 읽은 섹터의 수
  - Sectors Written : 기록한 섹터의 수
  - DISK IOPS나 DISK Operations와 관계가 있는 지표이다.
- DISK Space
  - Available : 이용 가능한 디스크 용량
  - Used : 이용 중인 디스크 용량
  - Available - Used : 디스크의 여유 용량 (0이 되지 않도록 주의한다.)

### Network Connection States
- Time Wait, Syn Recv, Fin Wait1, Fin Wait2는 프로세스가 연결되어 있지 않은 상태이므로 괜찮다.
- Established, Close Wait 등은 프로세스가 연결된 상태이기 때문에 이 수치가 크다는 것은 서버 측의 병렬 처리의 수가 크다는 것이다.

### Network Errors
- Rxerrs : 수신 Error 패킷의 수
- Rxdrop : 수신 Drop 패킷의 수
- Rxfifo : 수신 FIFO 버퍼의 Error 수
- Rxframe : 프레임 크기를 초과한 패킷의 수
- Txerrs : 송신 Error 패킷의 수
- Txdrop : 송신 Drop 패킷의 수
- Txfifo : 송신 FIFO 버퍼의 Error 수
- Txcolls : 송신 시 충돌을 감지한 횟수
- Txcarrier : 송신 캐리어의 수
- 해당 항목들이 0이 되는 것이 좋지만, 큰 웹 서버일 경우 어느정도는 발생할 수 있다.
- 수치가 계속 증가하는 경우에는 네트워크 설정의 오류나 기기의 고장을 의심하는 것이 좋다.
- ICMP를 차단하고 있는 네트워크 기기의 성능 부족이나 고장 또는 네트워크 케이블의 품질 불안정 등을 생각해 볼 수 있다.

### Network Traffic
- Inbound : Inbound Traffic(수신)
- Outbound : Outbound Traffic(송신)
- 만약 수치가 상한에 다다른 경우에는 네트워크 대역이 부족한 것이다.

# 상태 모니터링 데이터를 읽는 방법 - MySQL

### MySQL Command Counters
- Questions : 스테이트먼트를 실행한 총횟수
- Com xxx : xxx 스테이트먼트를 실행한 횟수
  - 예를 들어, SELECT 문이 실행된 경우 Com Select의 카운트가 증가한다.
- 쿼리 캐시에 의해 수행된 경우 Questions의 카운트는 증가하지만, Com Select의 카운트에는 반영되지 않는다.

### MySQL Connections
- Max Connections : 설정상 최대 연결의 수
- Max Used Connections : 최대 동시 접속 연결의 수
- Aborted Clients : 접속되었으나 끊어진 연결의 수
  - 정상적으로 접속되었으나 wait_timeout이나 interactive_timeout 등에 의해 끊어진 연결의 수
- Aborted Connects : 접속되지 못한 연결의 수
  - 인증에러 등에 의해 접속되지 못한 연결의 수
- Threads Connected : 접속 중인 연결의 수
- Connections : 접속된 연결의 수
- 커넥션 풀을 사용하면 Threads Connected는 많아지고, Connections는 작아진다.

### MySQL Files and Tables
- Tables Cache : 테이블 캐시의 크기
- Open Tables : Open된 적이 있는 테이블의 수
- Open Files : Open된 적이 있는 파일의 수
- Opened Tables : 현재 Open되어 있는 테이블의 수
- Table Cache를 만족하고 있는지 확인하여 테이블 캐시의 튜닝에 이용한다.

### MySQL Handlers
- Handler Write : INSERT 요구 횟수
- Handler Update : UPDATE 요구 횟수
- Handler Delete : DELETE 요구 횟수
- Handler Read First : 첫 번째 엔트리가 인덱스로부터 읽힌 횟수
  - 해당 값이 많을 때는 전체를 검사하는 경우가 많아서일 수도 있으니 확인해 보는 것이 좋다.
- Handler Read Key : 키(인덱스)를 기반으로 한 읽기 횟수
  - 해당 값이 많은 것은 적절하게 인덱스가 부여되고 있다는 증거이므로 좋은 경향이다.
- Handler Read Next : 키 순서에서 다음 레코드 읽기 요구 횟수
- Handler Read Prev : 키 순서에서 이전 레코드 읽기 요구 횟수
- Handler Read Rnd : 고정 위치를 기반으로 한 레코드 읽기 요구 횟수
  - SQL 결과를 정렬하는 경우가 많을 때 증가한다.
  - 테이블 검사가 많거나, 인덱스 없는 JOIN이 많고, 인덱스가 적절히 부여되고 있지 않을 가능성이 있다.
- Handler Read Rnd Next : 데이터 파일에서 다음 레코드 읽기 요구 횟수
  - 해당 값이 많은 경우에도 테이블 검사가 많고, 인덱스가 적절히 부여되고 있지 않을 가능성이 있다. 

### MySQL Network Traffic
- Bytes Sent : MySQL에서 클라이언트로 송신한 데이터의 양
- Bytes Received : MySQL이 클라이언트로부터 수신한 데이터의 양

### MySQL Processlist
- State End : SQL 문의 ALTER TABLE, CREATE VIEW, DELETE, INSERT, SELECT, UPDATE에 대한 종료 처리 상태
  - 클린업 전 상태
- State Freeing Items : 클린업 다음 단계로 쿼리 캐시를 포함한 몇 개의 아이템을 해제하고 있는 상태
- State Init : ALTER TABLE, CREATE VIEW, DELETE, INSERT, SELECT, UPDATE의 실행 준비 중인 상태

### MySQL Threads
- Thread Cache Size : 스레드 캐시의 크기
- Threads Connected 접속 중인 스레드의 수
- Threads Running : 실행 상태인 스레드의 수
- Threads Created : 생성된 스레드의 수
  - 해당 값이 큰 값을 표시하고 있다면 Thread Cache Size를 증가 시킴으로써 처리를 효율화할 수도 있다.
  - Threads Cached: 캐시되어 있던 스레드의 수

### MySQL Select Types
- Select Full Join : 인덱스를 사용하지 않는 JOIN의 수
- Select Range Check : 인덱스가 없는 JOIN의 수
- 두 값이 0이 아닌 경우에는 인덱스를 다시 검토하는 것이 좋다.

### MySQL Table Locks
- Table Locks Immediate : 테이블 Lock을 즉시 실행한 횟수
- Table Locks Waited : 테이블 Lock을 실행할 때 대기가 발생한 횟수
  - 테이블 Lock이 발생하면 처리의 병렬도가 현저하게 떨어지기 때문에 해당 값이 0이 되도록 한다.
- Slow Queries : 슬로우 쿼리의 수
- Lock의 범위를 최대한 작게 한다.

# 상태 모니터링 데이터를 읽는 방법 - InnoDB

### InnoDB Buffer Pool Efficiency
- Pool Read Requests : 읽기 요구 횟수
- Pool Reads : 버퍼 풀의 내용을 이용하지 못한 읽기 횟수
  - 응답 성능을 유지하기 위해 기본적으로 해당 값이 0이 되도록 조정한다.

### InnoDB Current Lock Waits
- Innodb Lock Wait Secs : Lock 해제 대기 시
  - 데이터 확인 시점에서 실행 중인 트랜잭션의 Lock 해제 대기 시간의 합계
  - 값이 0을 유지할 수 있도록 SQL을 조정한다.

### InnoDB I/O
- File Reads : OS에서의 read I/O 실행 횟수
- File Writes : OS에서의 write I/O 실행 횟수
- Log Writes : 로그 write I/O 실행 횟수
- File Fsyncs : OS에서의 fsyncs 실행 횟수
- OS 레벨에서의 I/O 성능 조정은 DB 서버의 핵심이므로 제대로 파악해 두는 것이 좋다.
- 어떤 입출력이 많은지 정확하게 파악하고 대응하도록 한다.

### InnoDB I/O Pending
- Pending Aio Log los : insert buffer의 비동기 log에서의 대기 I/O 수
- Pending Aio Sync los : insert buffer의 비동기 sync에서의 대기 I/O 수
- Pending Buf Pool Flushes : buffer pool flush에서의 대기
- Pending Chkp Writes : checkpoint에서의 대기
- Pending Ibuf Aio Reads : insert buffer의 비동기 log Read에서의 대기
- Pending Log Flushes : log flush에서의 대기
- Pending Log Writes : log write에서의 대기
- Pending Normal Aio Reads : 일반적인 비동기 read I/O 에서의 대기
- Pending Normal Aio Writes : 일반적인 비동기 write I/O 에서의 대기
- 해당 항목의 모든 수치가 0이 아닌 경우, 디스크 I/O가 보틀넥이 되고 있을 가능성이 있다.

### InnoDB Lock Structures : 해제 대기 중인 lock structure의 수
- 값이 0을 유지하도록 SQL을 조정한다.
- 이 값은 트랜잭션의 수가 아니라 Lock Structure의 수이므로 잘못 이애하지 않도록 주의한다.

### InnoDB Row Lock Time : Row Lock에 소요된 총 시간(msec)
- SQL문의 SHOW GLOBAL STATUS로 얻어지는 Innodb_row_lock_time의 값이다.
- 이 값이 0 또는 매우 작은 값을 유지할 수 있도록 SQL을 조정한다.

### InnoDB Row Lock Waits : Row Lock에 필요한 대기 횟수
- SQL문의 SHOW GLOBAL STATUS로 얻어지는 Innodb_row_lock_waits의 값
- 이 값이 0 또는 매우 작은 값을 유지할 수 있도록 SQL을 조정한다.

### InnoDB Semaphores
- Spin Rounds : Spin lock을 위한 라운드 수
- Spin Waits : Spin lock에 필요한 대기 수
- Os Waits : OS lock에 필요한 대기 수
- OS Lock은 Spin Lock보다 처리가 무겁기 때문에 작게 유지하도록 한다.
- Spin Lock도 처리가 가볍긴 하지만 CPU를 사용하기 때문에 그리 크지 않도록 유지한다.

### InnoDB Transactions Active / Locked
- Active Transactions : 실행 중인 트랜잭션 중에서 ACTIVE인 수
- Locked Transactions : 실행 중인 트랜잭션 중에서 LOCKED인 수
  - 값이 0 또는 매우 작은 값을 유지할 수 있도록 SQL을 조정한다.
- Current Transactions : 실행 중인 트랜잭션의 수
- Read Views : 실행 중인 Read View의 수

### MyISAM Indexes
- Key Read Requests : 캐시에서 키 블록을 읽기 위한 요구 횟수
- Key Reads : 디스크에서 키 블록을 읽은 횟수
  - Key Reads가 거의 0이 되도록 조정하는 것이 좋다.
- Key Write Requests : 캐시에 키 블록을 쓰기 위한 요구 횟수
- Key Writes : 디스크에 키 블록을 쓴 횟수
- Cache miss 비율 = Key Reads / Key Read Requests

# 실시간 모니터링의 방법

### dstat 사용하기
- 옵션은 다음과 같다.
  - -t : 일시
  - -l : 평균 부하
  - -m : 메모리
  - -a : cdngy
  - -f : CPU, Disk, Network 등을 디바이스별로 표시
  - --output : 출력을 CSV 파일에도 저장
  - 1 : 1초마다 표시
- ex) 1시간 동안 1초 간격으로 데이터를 기록
  - 뒤에 3600은 횟수로 이것을 붙이지 않으면 계속 수행
```
dstat -tlaf --output stat.csv 1 3600
```
- ex) crontab으로 설정
```
# crontab
0 0 * * * dstat -tlaf --output /tmp/stat.`date +%%Y%%m%%d`.csv 1 86400 > /dev/null 2>&1
```

### top 사용하기
- 해당 명령을 실행하고 1 키를 눌러 CPU 코어별로 표시할 수 있다.
- S(Status)가 R(실행 중) 또는 D(Read/Write 중)인 경우에는 CPU를 매우 적극적으로 사용하고 있다는 것을 알 수 있다.
- 결과는 매초마다 갱신되며, 기본적으로 CPU 사용률이 높은 순서로 표시된다.
- 코어당 100%로 4코어 서버라면 최대 400%까지 올라간다.

### iostat 사용하기
- I/O와 관련해서 사용하면 좋다.
- 옵션은 다음과 같다.
  - -t : 일시를 표시
  - -x : 확장 상태를 표시
  - -n : NFS의 상태를 표시
  - 1 : 1초마다 표시
```
iostat -txn 1
```

### 트러블 대응에 사용하는 모니터링 툴
- tcpdump 사용하기
  - 네트워크의 문제를 파악하기 위해 데이터가 정확하게 도달하고 있는지를 확인할 수 있다.
  - ex) 네트워크 eth0에서 온 HTTP 액세스를 캡처할 경우
  - tcpdump -i eth0 -n port 80
  - ex) 로컬호스트와 MySQL의 통신을 캡처할 경우
  - tcpdump -i lo -n port 3306
- strace와 lsof 사용하기
  - ps 명령어를 이용해 pid를 확인한다.
  - ex) ps aufx | grep http[d] | head -3
  - strace를 이용해 동작 중인 프로세스의 시스템 콜을 확인할 수 있다.
  - ex) strace -f -p 5377
  - lsof를 사용하면 프로세스가 열려있는 파일을 확인할 수 있다. Linux에서는 디바이스도 파일처럼 취급하기 때문에 프로세스가 열려있는 디바이스나 라이브러리 등을 확인할 수 있다.
  - ex) lsof -p 5377