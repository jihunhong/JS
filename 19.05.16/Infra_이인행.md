# ���� ����͸��� ���� ����
### ��ȭ�� �˾������� ���� ���
- ����� �ϴ� ��ȭ�� �ִ� : ��ҿ� �ٸ� �������� ���̴� ��� �� ������ Ȯ�� �ϵ��� �Ѵ�.
- �־�� �ϴ� ��ȭ�� ����
  - Ư�� �ð��뿡 ���ϰ� �����ؾ� �ϴµ� �������� �ʴ� ��� ������ Ȯ���Ѵ�.
  - ������ ���������ؾ� �ϴµ� ������ ���� �����ϰ� �ִ� ��쿡�� ������ Ȯ���Ѵ�.
- ��� ������ �ٸ���
  - ������ ������ �����ϵ��� �Ѵ�
  - ������� Mbps(bit per second)�� MB/s(byte per second)�� ���� ������ ���̰� ������ �ֱ� ������ �����ؼ� �����Ѵ�.
- Polling�� (Pull��)
  - ����͸� �� �ʿ��� ������ �����ϱ� ���� �׼����ϴ� Ÿ���̴�.
  - ������ ��� Ÿ�̹��� ����͸� �� �ʿ� �����ϸ� ����͸� �� ���� ó�� ���ɿ� ���� ��� Ÿ�̹��� �����ȴ�.
  - ex) Cacti
- Push��
  - ����͸� �� ������ �����͸� �۽��ϴ� Ÿ���̴�.
  - ������ ��� Ÿ�̹��� ����͸� ������ ó�� ���ɿ� �������� �ʰ�, ������ Ÿ�ֿ̹� ����� �� �ִ�.
  - ex) GrowthForecast

# ���� ����͸� �����͸� �д� ��� - OS
- Linux OS�� Percona Linux Monitoring Template for Cacti�� �׷��� �б�
### CPU ����
- CPU User
  - ����� ����
  - PHP �� ���α׷� ó���� �̿��� �κ�
- CPU System
  - �޸� �׼��� �� Ŀ�� ���������� CPU �̿��
  - ���� CPU User�� 10~20% ����������, �ϰ�ó���� ���� �뷮�� �޸� �׼����� �뷮�� ���ϼ��� �׼��� � ���� �����ϴ� ��찡 �ִ�.
- CPU Nice : �������� �ϰ�ó�� �� nice ����� �̿��Ͽ� �켱������ ������ Ư���� ó������ �̿��� �κ�
- CPU lowait
  - I/O ����� CPU �̿��
  - �ִ� ������ ���� �����Ǿ� �ִ��� ���� ������ ���� ������ ���ɿ� ����ġ�� ������ ��� ������ ������ ������ �ʿ䰡 �ִ�
  - ��κ��� �������� �ش� �κ��� ��Ʋ���� ����Ű�� ������ �Ǳ� ����Ƿ� ��������� ���� 0% �Ǵ� ���Ƶ� 30% ������ �����ϴ� ���� ����.
- CPU Irq, CPU Softirq
  - ���ͷ�Ʈ ó���� CPU �̿��
  - ������ Ŀ�ο����� �ټ��� �ھ �л�� �� ���� ������ ��Ʋ���� �Ǵ� ��찡 �ִ�.
- CPU Steal, CPU Guest : ����ӽ��� ����æ, �̿��� ��ŭ�� CPU �̿��

### Context Switches
- CPU���� ó���� ���μ��� ���� ��ȯ Ƚ��
- ���μ����� ���ĵ��� ����, ���� �ð��� ó���� ���� �ý��ۿ����� �ش� ���� Ŀ����.
- ������ ���� �ƴ� ���μ��� ���� �� ������ ���ø����̼� ����, �������� ���� ����ȭ ��� ���� ��� ���� Ŀ���� ����.

### Forks
- ���μ��� ���� ó���� �ϴ� Fork ���� Ƚ���� ��Ÿ����.
- �ش� �׷����� ���� �׻� ū ��쿡�� ���ʿ��� ���μ����� �����ϰų� �ܺ� ���μ����� ȣ���ϰ� ������ ������ �Ǵ� ���̺귯�� ȣ��� ������ ���� ������ �����ϵ��� �Ѵ�.

### Interrupts
- ��Ʈ��ũ �ۼ��� � ���� ���ͷ�Ʈ�� ��
- ��Ʈ��ũ �ۼ����� ������ �����Ѵ�.
- Irq�� Softirq ���� Ȯ���Ͽ� ������ ������ Ȯ���Ѵ�.

### Memory
- Memcached : ĳ�÷� ��� ���� �޸� �뷮
- Membuffer : ���۷� ��� ���� �޸� �뷮
- Memshared : ���� �޸𸮷� ��� ���� �޸� �뷮
- Memfree : ���� ����ϰ� ���� ���� �޸� �뷮
  - Memcached�� ���� �����Ͽ� Memfree�� 0�� �Ǵ� ��찡 ���� ����µ�, �̰��� Linux Ŀ���� �⺻���̸� �������� �����̴�.

### Swap Usage
- Swap�� ��ü �̿뷮�� �ƴ϶� Swap�� ���ų� �д� �翡 �ָ��ϴ� ���� ����.
- Pswpout : Swap�� ����, �ʿ������� �о� ���δ�.
- Pswpin : Swap���� �б�, Ȯ���Ǿ����� ������ ���� �޸𸮰� Swap���� �Ű�����.
  - ���� ���� ���鿡�� ������ ���� �� Pswpout�� Pswpin�� ���� �߻��ϴ� ���� ���� ���� �����̴�.
  - Pswpin�� ���� �߻��ϰ� �ִٸ� ������ �޸� �뷮�� ������ ���̴�.
  - �̶��� ������ �����ϰ� �ִ� ���α׷��� �޸� �Ҵ��� �����ϰų� ������ ������ ���׷��̵� �ϴ� ���� ����.

### Disk Elapsed IO Time(ms)
- IO Time : I/O �ð�
- IO Time Weighted : I/O�� �� �ҿ� �ð�
  - IO Time�� ���� �ش� ���� ũ�� ��ũ I/O�� ������ �����ϴٰ� �� �� �ִ�
  - �̶��� I/O ��ü�� ���̰ų� ��ũ I/O�� ������ ���� �ش� ���� ���������� Ʃ���Ѵ�.
- Disk IOPS
  - Io Ops : I/O ���� Ƚ��
- Disk Operations
  - Reads : ��ũ �б��� �䱸 Ƚ��
  - Writes : ��ũ ������ �䱸 Ƚ��
  - Reads + Writes = Io Ops
  - Reads Merged : ���յ� ��ũ �б��� �䱸 Ƚ��
  - Writes Merged : ���յ� ��ũ ������ �䱸 Ƚ��
- Disk Read/Write Time
  - Time Spent Reading : �б⿡ �ҿ�� �ð�
  - Time Spent Writing : ���⿡ �ҿ�� �ð�
  - �տ����� DISK IOPS�� Disk Operations�� �Բ� Ȯ���Ͽ� ��ũ I/O�� ������ ���������� ���� ��Ȳ�� Ȯ���Ѵ�.
- DISK Read/Write Time per IO Request
  - ���� Reads, Writes, Time Spent Reading, Time Spent Writing���κ��� ����ȴ�.
  - Read Time per IO : 1I/O�� �б��� �ҿ� �ð�
  - Write Time per IO : 1I/O�� ������ �ҿ� �ð�
  - �տ����� DISK IOPS�� Disk Operations�� �Բ� Ȯ���Ͽ� ��ũ I/O�� ������ ���������� ���� ��Ȳ�� Ȯ���Ѵ�.
- DISK Sectors Read/Written
  - Sectors Read : ���� ������ ��
  - Sectors Written : ����� ������ ��
  - DISK IOPS�� DISK Operations�� ���谡 �ִ� ��ǥ�̴�.
- DISK Space
  - Available : �̿� ������ ��ũ �뷮
  - Used : �̿� ���� ��ũ �뷮
  - Available - Used : ��ũ�� ���� �뷮 (0�� ���� �ʵ��� �����Ѵ�.)

### Network Connection States
- Time Wait, Syn Recv, Fin Wait1, Fin Wait2�� ���μ����� ����Ǿ� ���� ���� �����̹Ƿ� ������.
- Established, Close Wait ���� ���μ����� ����� �����̱� ������ �� ��ġ�� ũ�ٴ� ���� ���� ���� ���� ó���� ���� ũ�ٴ� ���̴�.

### Network Errors
- Rxerrs : ���� Error ��Ŷ�� ��
- Rxdrop : ���� Drop ��Ŷ�� ��
- Rxfifo : ���� FIFO ������ Error ��
- Rxframe : ������ ũ�⸦ �ʰ��� ��Ŷ�� ��
- Txerrs : �۽� Error ��Ŷ�� ��
- Txdrop : �۽� Drop ��Ŷ�� ��
- Txfifo : �۽� FIFO ������ Error ��
- Txcolls : �۽� �� �浹�� ������ Ƚ��
- Txcarrier : �۽� ĳ������ ��
- �ش� �׸���� 0�� �Ǵ� ���� ������, ū �� ������ ��� ��������� �߻��� �� �ִ�.
- ��ġ�� ��� �����ϴ� ��쿡�� ��Ʈ��ũ ������ ������ ����� ������ �ǽ��ϴ� ���� ����.
- ICMP�� �����ϰ� �ִ� ��Ʈ��ũ ����� ���� �����̳� ���� �Ǵ� ��Ʈ��ũ ���̺��� ǰ�� �Ҿ��� ���� ������ �� �� �ִ�.

### Network Traffic
- Inbound : Inbound Traffic(����)
- Outbound : Outbound Traffic(�۽�)
- ���� ��ġ�� ���ѿ� �ٴٸ� ��쿡�� ��Ʈ��ũ �뿪�� ������ ���̴�.

# ���� ����͸� �����͸� �д� ��� - MySQL

### MySQL Command Counters
- Questions : ������Ʈ��Ʈ�� ������ ��Ƚ��
- Com xxx : xxx ������Ʈ��Ʈ�� ������ Ƚ��
  - ���� ���, SELECT ���� ����� ��� Com Select�� ī��Ʈ�� �����Ѵ�.
- ���� ĳ�ÿ� ���� ����� ��� Questions�� ī��Ʈ�� ����������, Com Select�� ī��Ʈ���� �ݿ����� �ʴ´�.

### MySQL Connections
- Max Connections : ������ �ִ� ������ ��
- Max Used Connections : �ִ� ���� ���� ������ ��
- Aborted Clients : ���ӵǾ����� ������ ������ ��
  - ���������� ���ӵǾ����� wait_timeout�̳� interactive_timeout � ���� ������ ������ ��
- Aborted Connects : ���ӵ��� ���� ������ ��
  - �������� � ���� ���ӵ��� ���� ������ ��
- Threads Connected : ���� ���� ������ ��
- Connections : ���ӵ� ������ ��
- Ŀ�ؼ� Ǯ�� ����ϸ� Threads Connected�� ��������, Connections�� �۾�����.

### MySQL Files and Tables
- Tables Cache : ���̺� ĳ���� ũ��
- Open Tables : Open�� ���� �ִ� ���̺��� ��
- Open Files : Open�� ���� �ִ� ������ ��
- Opened Tables : ���� Open�Ǿ� �ִ� ���̺��� ��
- Table Cache�� �����ϰ� �ִ��� Ȯ���Ͽ� ���̺� ĳ���� Ʃ�׿� �̿��Ѵ�.

### MySQL Handlers
- Handler Write : INSERT �䱸 Ƚ��
- Handler Update : UPDATE �䱸 Ƚ��
- Handler Delete : DELETE �䱸 Ƚ��
- Handler Read First : ù ��° ��Ʈ���� �ε����κ��� ���� Ƚ��
  - �ش� ���� ���� ���� ��ü�� �˻��ϴ� ��찡 ���Ƽ��� ���� ������ Ȯ���� ���� ���� ����.
- Handler Read Key : Ű(�ε���)�� ������� �� �б� Ƚ��
  - �ش� ���� ���� ���� �����ϰ� �ε����� �ο��ǰ� �ִٴ� �����̹Ƿ� ���� �����̴�.
- Handler Read Next : Ű �������� ���� ���ڵ� �б� �䱸 Ƚ��
- Handler Read Prev : Ű �������� ���� ���ڵ� �б� �䱸 Ƚ��
- Handler Read Rnd : ���� ��ġ�� ������� �� ���ڵ� �б� �䱸 Ƚ��
  - SQL ����� �����ϴ� ��찡 ���� �� �����Ѵ�.
  - ���̺� �˻簡 ���ų�, �ε��� ���� JOIN�� ����, �ε����� ������ �ο��ǰ� ���� ���� ���ɼ��� �ִ�.
- Handler Read Rnd Next : ������ ���Ͽ��� ���� ���ڵ� �б� �䱸 Ƚ��
  - �ش� ���� ���� ��쿡�� ���̺� �˻簡 ����, �ε����� ������ �ο��ǰ� ���� ���� ���ɼ��� �ִ�. 

### MySQL Network Traffic
- Bytes Sent : MySQL���� Ŭ���̾�Ʈ�� �۽��� �������� ��
- Bytes Received : MySQL�� Ŭ���̾�Ʈ�κ��� ������ �������� ��

### MySQL Processlist
- State End : SQL ���� ALTER TABLE, CREATE VIEW, DELETE, INSERT, SELECT, UPDATE�� ���� ���� ó�� ����
  - Ŭ���� �� ����
- State Freeing Items : Ŭ���� ���� �ܰ�� ���� ĳ�ø� ������ �� ���� �������� �����ϰ� �ִ� ����
- State Init : ALTER TABLE, CREATE VIEW, DELETE, INSERT, SELECT, UPDATE�� ���� �غ� ���� ����

### MySQL Threads
- Thread Cache Size : ������ ĳ���� ũ��
- Threads Connected ���� ���� �������� ��
- Threads Running : ���� ������ �������� ��
- Threads Created : ������ �������� ��
  - �ش� ���� ū ���� ǥ���ϰ� �ִٸ� Thread Cache Size�� ���� ��Ŵ���ν� ó���� ȿ��ȭ�� ���� �ִ�.
  - Threads Cached: ĳ�õǾ� �ִ� �������� ��

### MySQL Select Types
- Select Full Join : �ε����� ������� �ʴ� JOIN�� ��
- Select Range Check : �ε����� ���� JOIN�� ��
- �� ���� 0�� �ƴ� ��쿡�� �ε����� �ٽ� �����ϴ� ���� ����.

### MySQL Table Locks
- Table Locks Immediate : ���̺� Lock�� ��� ������ Ƚ��
- Table Locks Waited : ���̺� Lock�� ������ �� ��Ⱑ �߻��� Ƚ��
  - ���̺� Lock�� �߻��ϸ� ó���� ���ĵ��� �����ϰ� �������� ������ �ش� ���� 0�� �ǵ��� �Ѵ�.
- Slow Queries : ���ο� ������ ��
- Lock�� ������ �ִ��� �۰� �Ѵ�.

# ���� ����͸� �����͸� �д� ��� - InnoDB

### InnoDB Buffer Pool Efficiency
- Pool Read Requests : �б� �䱸 Ƚ��
- Pool Reads : ���� Ǯ�� ������ �̿����� ���� �б� Ƚ��
  - ���� ������ �����ϱ� ���� �⺻������ �ش� ���� 0�� �ǵ��� �����Ѵ�.

### InnoDB Current Lock Waits
- Innodb Lock Wait Secs : Lock ���� ��� ��
  - ������ Ȯ�� �������� ���� ���� Ʈ������� Lock ���� ��� �ð��� �հ�
  - ���� 0�� ������ �� �ֵ��� SQL�� �����Ѵ�.

### InnoDB I/O
- File Reads : OS������ read I/O ���� Ƚ��
- File Writes : OS������ write I/O ���� Ƚ��
- Log Writes : �α� write I/O ���� Ƚ��
- File Fsyncs : OS������ fsyncs ���� Ƚ��
- OS ���������� I/O ���� ������ DB ������ �ٽ��̹Ƿ� ����� �ľ��� �δ� ���� ����.
- � ������� ������ ��Ȯ�ϰ� �ľ��ϰ� �����ϵ��� �Ѵ�.

### InnoDB I/O Pending
- Pending Aio Log los : insert buffer�� �񵿱� log������ ��� I/O ��
- Pending Aio Sync los : insert buffer�� �񵿱� sync������ ��� I/O ��
- Pending Buf Pool Flushes : buffer pool flush������ ���
- Pending Chkp Writes : checkpoint������ ���
- Pending Ibuf Aio Reads : insert buffer�� �񵿱� log Read������ ���
- Pending Log Flushes : log flush������ ���
- Pending Log Writes : log write������ ���
- Pending Normal Aio Reads : �Ϲ����� �񵿱� read I/O ������ ���
- Pending Normal Aio Writes : �Ϲ����� �񵿱� write I/O ������ ���
- �ش� �׸��� ��� ��ġ�� 0�� �ƴ� ���, ��ũ I/O�� ��Ʋ���� �ǰ� ���� ���ɼ��� �ִ�.

### InnoDB Lock Structures : ���� ��� ���� lock structure�� ��
- ���� 0�� �����ϵ��� SQL�� �����Ѵ�.
- �� ���� Ʈ������� ���� �ƴ϶� Lock Structure�� ���̹Ƿ� �߸� �̾����� �ʵ��� �����Ѵ�.

### InnoDB Row Lock Time : Row Lock�� �ҿ�� �� �ð�(msec)
- SQL���� SHOW GLOBAL STATUS�� ������� Innodb_row_lock_time�� ���̴�.
- �� ���� 0 �Ǵ� �ſ� ���� ���� ������ �� �ֵ��� SQL�� �����Ѵ�.

### InnoDB Row Lock Waits : Row Lock�� �ʿ��� ��� Ƚ��
- SQL���� SHOW GLOBAL STATUS�� ������� Innodb_row_lock_waits�� ��
- �� ���� 0 �Ǵ� �ſ� ���� ���� ������ �� �ֵ��� SQL�� �����Ѵ�.

### InnoDB Semaphores
- Spin Rounds : Spin lock�� ���� ���� ��
- Spin Waits : Spin lock�� �ʿ��� ��� ��
- Os Waits : OS lock�� �ʿ��� ��� ��
- OS Lock�� Spin Lock���� ó���� ���̱� ������ �۰� �����ϵ��� �Ѵ�.
- Spin Lock�� ó���� ������ ������ CPU�� ����ϱ� ������ �׸� ũ�� �ʵ��� �����Ѵ�.

### InnoDB Transactions Active / Locked
- Active Transactions : ���� ���� Ʈ����� �߿��� ACTIVE�� ��
- Locked Transactions : ���� ���� Ʈ����� �߿��� LOCKED�� ��
  - ���� 0 �Ǵ� �ſ� ���� ���� ������ �� �ֵ��� SQL�� �����Ѵ�.
- Current Transactions : ���� ���� Ʈ������� ��
- Read Views : ���� ���� Read View�� ��

### MyISAM Indexes
- Key Read Requests : ĳ�ÿ��� Ű ����� �б� ���� �䱸 Ƚ��
- Key Reads : ��ũ���� Ű ����� ���� Ƚ��
  - Key Reads�� ���� 0�� �ǵ��� �����ϴ� ���� ����.
- Key Write Requests : ĳ�ÿ� Ű ����� ���� ���� �䱸 Ƚ��
- Key Writes : ��ũ�� Ű ����� �� Ƚ��
- Cache miss ���� = Key Reads / Key Read Requests

# �ǽð� ����͸��� ���

### dstat ����ϱ�
- �ɼ��� ������ ����.
  - -t : �Ͻ�
  - -l : ��� ����
  - -m : �޸�
  - -a : cdngy
  - -f : CPU, Disk, Network ���� ����̽����� ǥ��
  - --output : ����� CSV ���Ͽ��� ����
  - 1 : 1�ʸ��� ǥ��
- ex) 1�ð� ���� 1�� �������� �����͸� ���
  - �ڿ� 3600�� Ƚ���� �̰��� ������ ������ ��� ����
```
dstat -tlaf --output stat.csv 1 3600
```
- ex) crontab���� ����
```
# crontab
0 0 * * * dstat -tlaf --output /tmp/stat.`date +%%Y%%m%%d`.csv 1 86400 > /dev/null 2>&1
```

### top ����ϱ�
- �ش� ����� �����ϰ� 1 Ű�� ���� CPU �ھ�� ǥ���� �� �ִ�.
- S(Status)�� R(���� ��) �Ǵ� D(Read/Write ��)�� ��쿡�� CPU�� �ſ� ���������� ����ϰ� �ִٴ� ���� �� �� �ִ�.
- ����� ���ʸ��� ���ŵǸ�, �⺻������ CPU ������ ���� ������ ǥ�õȴ�.
- �ھ�� 100%�� 4�ھ� ������� �ִ� 400%���� �ö󰣴�.

### iostat ����ϱ�
- I/O�� �����ؼ� ����ϸ� ����.
- �ɼ��� ������ ����.
  - -t : �Ͻø� ǥ��
  - -x : Ȯ�� ���¸� ǥ��
  - -n : NFS�� ���¸� ǥ��
  - 1 : 1�ʸ��� ǥ��
```
iostat -txn 1
```

### Ʈ���� ������ ����ϴ� ����͸� ��
- tcpdump ����ϱ�
  - ��Ʈ��ũ�� ������ �ľ��ϱ� ���� �����Ͱ� ��Ȯ�ϰ� �����ϰ� �ִ����� Ȯ���� �� �ִ�.
  - ex) ��Ʈ��ũ eth0���� �� HTTP �׼����� ĸó�� ���
  - tcpdump -i eth0 -n port 80
  - ex) ����ȣ��Ʈ�� MySQL�� ����� ĸó�� ���
  - tcpdump -i lo -n port 3306
- strace�� lsof ����ϱ�
  - ps ��ɾ �̿��� pid�� Ȯ���Ѵ�.
  - ex) ps aufx | grep http[d] | head -3
  - strace�� �̿��� ���� ���� ���μ����� �ý��� ���� Ȯ���� �� �ִ�.
  - ex) strace -f -p 5377
  - lsof�� ����ϸ� ���μ����� �����ִ� ������ Ȯ���� �� �ִ�. Linux������ ����̽��� ����ó�� ����ϱ� ������ ���μ����� �����ִ� ����̽��� ���̺귯�� ���� Ȯ���� �� �ִ�.
  - ex) lsof -p 5377