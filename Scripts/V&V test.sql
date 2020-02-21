select * from employee;
select * from plan;
select * from customer;

insert into bankbook values 
('293133-11-000001','C001','A001',"2020-02-05 09:00:00",0.10,5000000),
('293133-12-000002','C001','A002',"2020-02-05 09:00:00",0.05,0),
('293133-13-000003','C001','A003',"2020-02-05 09:00:00",0.03,50000000),
('293133-11-000004','C002','A004',"2020-02-05 09:00:00",0.05,5000000),
('293133-12-000005','C002','A002',"2020-02-05 09:00:00",0.05,0),
('293133-13-000006','C002','A003',"2020-02-05 09:00:00",0.03,50000000),
('293133-11-000007','C003','A004',"2020-02-05 09:00:00",0.05,5000000),
('293133-12-000008','C003','A002',"2020-02-05 09:00:00",0.05,0),
('293133-13-000009','C003','A003',"2020-02-05 09:00:00",0.03,50000000),
('293133-11-000010','C004','A004',"2020-02-05 09:00:00",0.05,5000000),
('293133-12-000011','C004','A002',"2020-02-05 09:00:00",0.05,0),
('293133-13-000012','C004','A003',"2020-02-05 09:00:00",0.03,50000000),
('293133-11-000013','C005','A004',"2020-02-05 09:00:00",0.05,5000000),
('293133-12-000014','C005','A002',"2020-02-05 09:00:00",0.05,0),
('293133-13-000015','C005','A003',"2020-02-05 09:00:00",0.03,50000000);
select * from bankbook;

desc card;

insert into card values
('2931331000000010','C001','B001','111',"2020-02-05 09:00:00",null,null),
('2931332000000020','C001','B002','222',"2020-02-05 09:00:00",null,null),
('2931331000000030','C002','B001','333',"2020-02-05 09:00:00",null,null),
('2931332000000040','C002','B002','444',"2020-02-05 09:00:00",null,null),
('2931331000000050','C003','B001','555',"2020-02-05 09:00:00",null,null),
('2931332000000060','C003','B002','666',"2020-02-05 09:00:00",null,null),
('2931331000000070','C004','B001','777',"2020-02-05 09:00:00",null,null),
('2931332000000080','C004','B002','888',"2020-02-05 09:00:00",null,null),
('2931331000000090','C005','B001','999',"2020-02-05 09:00:00",null,null),
('2931332000000100','C005','B002','000',"2020-02-05 09:00:00",null,null);

select * from card;
select * from card where planCode = 'B001';
select accountBalance from bankbook where accountPlanCode in ('A001','A004');
select * from card where plancode = 'B002';
update card set cardBalance = null where plancode = 'B002';
#신용 카드 한도 조정
#1등급 : 10,000,000
#2등급 : 9,000,000
#3등급 : 8,000,000
#4등급 : 7,000,000
#5등급 : 6,000,000
update card set cardLimit = 10000000 where custCode = 'C001' and plancode = 'B002';
update card set cardLimit = 9000000 where custCode = 'C002' and plancode = 'B002';
update card set cardLimit = 8000000 where custCode = 'C003' and plancode = 'B002';
update card set cardLimit = 7000000 where custCode = 'C004' and plancode = 'B002';
update card set cardLimit = 6000000 where custCode = 'C005' and plancode = 'B002';
#체크 카드 통장에 있는 잔액에 따라 업데이트
select * from bankbook where custcode = 'C002';
select * from card where plancode = 'B001';
update card c set c.cardBalance = (select accountbalance from bankbook b where b.custCode = 'C001' and b.accountPlanCode = 'A001') where plancode = 'B001';
update card c set c.cardBalance = (select accountbalance from bankbook b where b.custCode = 'C002' and b.accountPlanCode = 'A004') where plancode = 'B001';
update card c set c.cardBalance = (select accountbalance from bankbook b where b.custCode = 'C003' and b.accountPlanCode = 'A004') where plancode = 'B001';
update card c set c.cardBalance = (select accountbalance from bankbook b where b.custCode = 'C004' and b.accountPlanCode = 'A004') where plancode = 'B001';
update card c set c.cardBalance = (select accountbalance from bankbook b where b.custCode = 'C005' and b.accountPlanCode = 'A004') where plancode = 'B001';

#테이블을 위한 카드 SQL
select c.cardnum,cs.custname,p.planname,c.cardsecucode,c.cardissuedate,c.cardlimit,c.cardbalance from card c left join customer cs on c.custcode = cs.custcode left join plan p on p.planCode = c.plancode;
#카드 부분은 추후 구현시 카드한도가 신용카드일때만 한도가 정해져야 하고, 카드잔액이 체크카드일시 예금계좌 잔액과 같아야 하므로 트리거가 필요함 - 일단 테이블부터 구현 후 수정 필요

insert into loan values
('293133-11-000001','C001','C001',"2020-02-05 09:00:00",0.01,100000000),
('293133-12-000002','C001','C002',"2020-02-05 09:00:00",0.02,100000000),
('293133-13-000003','C001','C003',"2020-02-05 09:00:00",0.03,100000000),
('293133-11-000004','C002','C001',"2020-02-05 09:00:00",0.02,100000000),
('293133-12-000005','C002','C002',"2020-02-05 09:00:00",0.03,100000000),
('293133-13-000006','C002','C003',"2020-02-05 09:00:00",0.04,100000000),
('293133-11-000007','C003','C001',"2020-02-05 09:00:00",0.03,100000000),
('293133-12-000008','C003','C002',"2020-02-05 09:00:00",0.04,100000000),
('293133-13-000009','C003','C003',"2020-02-05 09:00:00",0.08,100000000),
('293133-11-000010','C004','C001',"2020-02-05 09:00:00",0.04,100000000),
('293133-12-000011','C004','C002',"2020-02-05 09:00:00",0.05,100000000),
('293133-13-000012','C004','C003',"2020-02-05 09:00:00",0.16,100000000),
('293133-11-000013','C005','C001',"2020-02-05 09:00:00",0.05,100000000),
('293133-12-000014','C005','C002',"2020-02-05 09:00:00",0.06,100000000),
('293133-13-000015','C005','C003',"2020-02-05 09:00:00",0.32,100000000);
#대출 부분은 대출 금액이 0원이 될 때 계좌가 휴면계좌가 되어야 하므로, 삭제 및 휴면계좌가만 보관하는 휴면계좌 뷰테이블에 삽입되는 트리거 필요
select * from loan;

#performance table은 상품번호,담당사원,담당고객으로 나누어진다
#상품코드 : A001 사원코드 : B003 고객코드 C001:
#상품코드 : A002 사원코드 : B004 고객코드 C001,C002,C003,C004,C005;
#상품코드 : A003 사원코드 : B004 고객코드 C001,C002,C003,C004,C005;
#상품코드 : A004 사원코드 : B005 고객코드 C001,C002,C003,C004,C005;
#상품코드 : B001 사원코드 : B005 고객코드 C001,C002,C003,C004,C005;
#상품코드 : B002 사원코드 : B006 고객코드 C001,C002,C003,C004,C005;
#상품코드 : C001 사원코드 : B006 고객코드 C001,C002,C003,C004,C005;
#상품코드 : C002 사원코드 : B007 고객코드 C001,C002,C003,C004,C005;
#상품코드 : C003 사원코드 : B007 고객코드 C001,C002,C003,C004,C005;
desc performance;
insert into performance values
('A001','B003','C001'),
('A002','B004','C001'),('A002','B004','C002'),('A002','B004','C003'),('A002','B004','C004'),('A002','B004','C005'),
('A003','B004','C001'),('A003','B004','C002'),('A003','B004','C003'),('A003','B004','C004'),('A003','B004','C005'),
('A004','B005','C001'),('A004','B005','C002'),('A004','B005','C003'),('A004','B005','C004'),('A004','B005','C005'),
('B001','B005','C001'),('B001','B005','C002'),('B001','B005','C003'),('B001','B005','C004'),('B001','B005','C005'),
('B002','B006','C001'),('B002','B006','C002'),('B002','B006','C003'),('B002','B006','C004'),('B002','B006','C005'),
('C001','B006','C001'),('C001','B006','C002'),('C001','B006','C003'),('C001','B006','C004'),('C001','B006','C005'),
('C002','B007','C001'),('C002','B007','C002'),('C002','B007','C003'),('C002','B007','C004'),('C002','B007','C005'),
('C003','B007','C001'),('C003','B007','C002'),('C003','B007','C003'),('C003','B007','C004'),('C003','B007','C005');
select * from performance;

select * from employee;
insert into employee values ('B008','테스트','지점장','AD',10000000,'010-1234-1234','111',password('111'),2);
select empname from employee where empid='111' and emppwd=password('111');
select * from employee where empAuth = 'CS';
select * from plan where planCode like 'B%';
select c.cardnum,cs.custcode,cs.custname,p.plancode,p.planname,c.cardsecucode,c.cardissuedate,c.cardlimit,c.cardbalance from card c left join customer cs on c.custcode = cs.custcode left join plan p on p.planCode = c.plancode where cs.custname = '김가나';
select customer.custname,bankbook.accountBalance from customer left join bankbook on customer.`custCode` = bankbook.`custCode` where bankbook.`accountPlanCode` in ('A001','A004'); 