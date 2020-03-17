use bank;
#사원 - 장현서

update employee set emppwd = 111 where `empName` ='test';


select  empCode, empName, empTitle, empAuth, empSalary, empTel, empId, empPwd, d.deptName, d.deptNo  from employee e left join department d on e.deptNo = d.deptNo where d.deptName='인사';
select  empCode, empName, empTitle, empAuth, empSalary, empTel, empId, empPwd, d.deptName, d.deptNo from employee e left join department d on e.deptNo = d.deptNo  where empName like '%장%';

select * from employee e2 ;
-- 전체직원수
select count(*) from bank.employee;  -- 3출력
-- 부서별 직원수
select count(*) from bank.employee where deptNo =1; -- 1명
select count(*) from bank.employee where deptNo =2; -- 2명
-- 직급별 직원수(등급 같이 표시)
select concat(count(*),'명(',`empTitle`,')') as '직급별사원수(직책)' from bank.employee 
  group by `empTitle`;
 
 select count(`custCode`) , `empCode` , 
  from performance p
  group by `empCode`;
 
-- 사원업무 조회에서 사용
select e.empCode, e.empName, e.empTitle, count(if(p.custCode=null,0,p.custCode)) as perf , if(count(if(p.custCode=null,0,p.custCode))>=10,e.`empSalary`*0.1,0) as bonus, if(p.`planCode`='A001',vip,null) as vip
from employee e left join performance p on e.`empCode` = p.`empCode`  left join customer c on p.`custCode`=c.`custCode` left join viptable v on p.`custCode`= v.vip
group by e.`empCode`;

-- 1인 평균 급여액
select sum(empSalary) 
   from employee e ;
select (sum(empSalary))/(count(*))
   from employee e ;
#고객 - 황하나
-- test 
select custCode, custName, custRank, custCredit, custAddr, custTel from customer;
select planCode, planDetail, planName, planDesc, planDiv from plan;
select * from plan;
delete from customer where custCode = "C008";

select c.custName, b.accountBalance from customer c left join bankbook b on c.custCode = b.custCode;
select  c.custCode, c.custName, b.accountNum , b.accountPlanCode, b.accountBalance from BankBook b left join customer c on b.custCode = c.custCode ;
select c.custCode, c.custName, b.accountNum, b.accountBalance from customer c left join bankbook b on c.custcode = b.custcode;
update BankBook set accountBalance = 5000300 where accountNum ="293133-11-000007"; 

select planCode, planDetail, planName, planDesc, planDiv from plan where planName like '%예금%';
select custName, accountNum, accountBalance from customer c left join bankbook b on c.custcode = b.custCode where custName = "김가나";

select (count(*) - (select count(*) from customer where custRank = "D")) from customer;
select count(*) from customer where custRank = "D"; 
select * from customer c ;

select * from card;
select * from bankbook;
select * from customer;
select * from plan;


#BSGPD
select count(*) from customer where custRank = "B";

select * from bankbook;
select SUBSTRING_INDEX(SUBSTRING_INDEX(accountOpenDate, '-', 2), '-', -1) from bankbook where SUBSTRING_INDEX(SUBSTRING_INDEX(accountNum, '-', 2), '-', -1) ="11";

select  SUBSTRING_INDEX(SUBSTRING_INDEX(accountDate, '-', 2), '-', -1) from cust_dw_audit where dw ="입금";

select * from bankbook b ;
desc bankbook;
select * from cust_dw_audit cda ;
delete from cust_dw_audit where dw = "출금";
select * from loan;
select * from plan;


#은행업무 - 박인선
select * from plan where planCode like 'A%';
select * from plan where planCode like 'B%';
select * from plan where planCode like 'C%';
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

#카드 통계를 위한 insert
insert into cardInfo values 
('김가나','2020-01-01 09:00:00'),
('김가나','2020-01-02 09:00:00'),
('김가나','2020-01-03 09:00:00'),
('김가나','2020-01-04 09:00:00'),
('김가나','2020-01-05 09:00:00'),
('김가나','2020-01-06 09:00:00'),
('김가나','2020-01-07 09:00:00'),
('김가나','2020-01-08 09:00:00'),
('김가나','2020-01-09 09:00:00'),
('김가나','2020-01-10 09:00:00'),
('김가나','2020-01-11 09:00:00'),
('김가나','2020-01-12 09:00:00'),
('김가나','2020-01-13 09:00:00'),
('김가나','2020-01-14 09:00:00'),
('김가나','2020-01-15 09:00:00'),
('김가나','2020-01-16 09:00:00'),
('김가나','2020-01-17 09:00:00'),
('김가나','2020-01-18 09:00:00'),
('김가나','2020-01-19 09:00:00'),
('김가나','2020-01-20 09:00:00'),
('김가나','2020-01-21 09:00:00'),
('김가나','2020-01-22 09:00:00'),
('김가나','2020-01-23 09:00:00'),
('김가나','2020-01-24 09:00:00'),
('김가나','2020-01-25 09:00:00'),
('김가나','2020-01-26 09:00:00'),
('김가나','2020-01-27 09:00:00'),
('김가나','2020-01-28 09:00:00'),
('김가나','2020-01-29 09:00:00'),
('김가나','2020-01-30 09:00:00'),
('김가나','2020-01-31 09:00:00'),
('김가나','2020-02-01 09:00:00'),
('김가나','2020-02-02 09:00:00'),
('김가나','2020-02-03 09:00:00'),
('김가나','2020-02-04 09:00:00'),
('김가나','2020-02-05 09:00:00'),
('김가나','2020-02-06 09:00:00'),
('김가나','2020-02-07 09:00:00'),
('김가나','2020-02-08 09:00:00'),
('김가나','2020-02-09 09:00:00'),
('김가나','2020-02-10 09:00:00'),
('김가나','2020-02-11 09:00:00'),
('김가나','2020-02-12 09:00:00'),
('김가나','2020-02-13 09:00:00'),
('김가나','2020-02-14 09:00:00'),
('김가나','2020-02-15 09:00:00'),
('김가나','2020-02-16 09:00:00'),
('김가나','2020-02-17 09:00:00'),
('김가나','2020-02-18 09:00:00'),
('김가나','2020-02-19 09:00:00'),
('김가나','2020-02-20 09:00:00'),
('김가나','2020-02-21 09:00:00'),
('김가나','2020-02-22 09:00:00'),
('김가나','2020-02-23 09:00:00'),
('김가나','2020-02-24 09:00:00'),
('김가나','2020-02-25 09:00:00'),
('김가나','2020-02-26 09:00:00'),
('김가나','2020-02-27 09:00:00'),
('김가나','2020-02-28 09:00:00'),
('김가나','2020-02-29 09:00:00'),
('김가나','2020-03-01 09:00:00'),
('김가나','2020-03-02 09:00:00'),
('김가나','2020-03-03 09:00:00'),
('김가나','2020-03-04 09:00:00'),
('김가나','2020-03-05 09:00:00'),
('김가나','2020-03-06 09:00:00'),
('김가나','2020-03-07 09:00:00'),
('김가나','2020-03-08 09:00:00'),
('김가나','2020-03-09 09:00:00'),
('김가나','2020-03-10 09:00:00');

insert into bankbookInfo values 
('김가나','2020-01-01 09:00:00'),
('김가나','2020-01-02 09:00:00'),
('김가나','2020-01-03 09:00:00'),
('김가나','2020-01-04 09:00:00'),
('김가나','2020-01-05 09:00:00'),
('김가나','2020-01-06 09:00:00'),
('김가나','2020-01-07 09:00:00'),
('김가나','2020-01-08 09:00:00'),
('김가나','2020-01-09 09:00:00'),
('김가나','2020-01-10 09:00:00'),
('김가나','2020-01-11 09:00:00'),
('김가나','2020-01-12 09:00:00'),
('김가나','2020-01-13 09:00:00'),
('김가나','2020-01-14 09:00:00'),
('김가나','2020-01-15 09:00:00'),
('김가나','2020-01-16 09:00:00'),
('김가나','2020-01-17 09:00:00'),
('김가나','2020-01-18 09:00:00'),
('김가나','2020-01-19 09:00:00'),
('김가나','2020-01-20 09:00:00'),
('김가나','2020-01-21 09:00:00'),
('김가나','2020-01-22 09:00:00'),
('김가나','2020-01-23 09:00:00'),
('김가나','2020-01-24 09:00:00'),
('김가나','2020-01-25 09:00:00'),
('김가나','2020-01-26 09:00:00'),
('김가나','2020-01-27 09:00:00'),
('김가나','2020-01-28 09:00:00'),
('김가나','2020-01-29 09:00:00'),
('김가나','2020-01-30 09:00:00'),
('김가나','2020-01-31 09:00:00'),
('김가나','2020-02-01 09:00:00'),
('김가나','2020-02-02 09:00:00'),
('김가나','2020-02-03 09:00:00'),
('김가나','2020-02-04 09:00:00'),
('김가나','2020-02-05 09:00:00'),
('김가나','2020-02-06 09:00:00'),
('김가나','2020-02-07 09:00:00'),
('김가나','2020-02-08 09:00:00'),
('김가나','2020-02-09 09:00:00'),
('김가나','2020-02-10 09:00:00'),
('김가나','2020-02-11 09:00:00'),
('김가나','2020-02-12 09:00:00'),
('김가나','2020-02-13 09:00:00'),
('김가나','2020-02-14 09:00:00'),
('김가나','2020-02-15 09:00:00'),
('김가나','2020-02-16 09:00:00'),
('김가나','2020-02-17 09:00:00'),
('김가나','2020-02-18 09:00:00'),
('김가나','2020-02-19 09:00:00'),
('김가나','2020-02-20 09:00:00'),
('김가나','2020-02-21 09:00:00'),
('김가나','2020-02-22 09:00:00'),
('김가나','2020-02-23 09:00:00'),
('김가나','2020-02-24 09:00:00'),
('김가나','2020-02-25 09:00:00'),
('김가나','2020-02-26 09:00:00'),
('김가나','2020-02-27 09:00:00'),
('김가나','2020-02-28 09:00:00'),
('김가나','2020-02-29 09:00:00'),
('김가나','2020-03-01 09:00:00'),
('김가나','2020-03-02 09:00:00'),
('김가나','2020-03-03 09:00:00'),
('김가나','2020-03-04 09:00:00'),
('김가나','2020-03-05 09:00:00'),
('김가나','2020-03-06 09:00:00'),
('김가나','2020-03-07 09:00:00'),
('김가나','2020-03-08 09:00:00'),
('김가나','2020-03-09 09:00:00'),
('김가나','2020-03-10 09:00:00');

insert into loanInfo values 
('김가나','2020-01-01 09:00:00'),
('김가나','2020-01-02 09:00:00'),
('김가나','2020-01-03 09:00:00'),
('김가나','2020-01-04 09:00:00'),
('김가나','2020-01-05 09:00:00'),
('김가나','2020-01-06 09:00:00'),
('김가나','2020-01-07 09:00:00'),
('김가나','2020-01-08 09:00:00'),
('김가나','2020-01-09 09:00:00'),
('김가나','2020-01-10 09:00:00'),
('김가나','2020-01-11 09:00:00'),
('김가나','2020-01-12 09:00:00'),
('김가나','2020-01-13 09:00:00'),
('김가나','2020-01-14 09:00:00'),
('김가나','2020-01-15 09:00:00'),
('김가나','2020-01-16 09:00:00'),
('김가나','2020-01-17 09:00:00'),
('김가나','2020-01-18 09:00:00'),
('김가나','2020-01-19 09:00:00'),
('김가나','2020-01-20 09:00:00'),
('김가나','2020-01-21 09:00:00'),
('김가나','2020-01-22 09:00:00'),
('김가나','2020-01-23 09:00:00'),
('김가나','2020-01-24 09:00:00'),
('김가나','2020-01-25 09:00:00'),
('김가나','2020-01-26 09:00:00'),
('김가나','2020-01-27 09:00:00'),
('김가나','2020-01-28 09:00:00'),
('김가나','2020-01-29 09:00:00'),
('김가나','2020-01-30 09:00:00'),
('김가나','2020-01-31 09:00:00'),
('김가나','2020-02-01 09:00:00'),
('김가나','2020-02-02 09:00:00'),
('김가나','2020-02-03 09:00:00'),
('김가나','2020-02-04 09:00:00'),
('김가나','2020-02-05 09:00:00'),
('김가나','2020-02-06 09:00:00'),
('김가나','2020-02-07 09:00:00'),
('김가나','2020-02-08 09:00:00'),
('김가나','2020-02-09 09:00:00'),
('김가나','2020-02-10 09:00:00'),
('김가나','2020-02-11 09:00:00'),
('김가나','2020-02-12 09:00:00'),
('김가나','2020-02-13 09:00:00'),
('김가나','2020-02-14 09:00:00'),
('김가나','2020-02-15 09:00:00'),
('김가나','2020-02-16 09:00:00'),
('김가나','2020-02-17 09:00:00'),
('김가나','2020-02-18 09:00:00'),
('김가나','2020-02-19 09:00:00'),
('김가나','2020-02-20 09:00:00'),
('김가나','2020-02-21 09:00:00'),
('김가나','2020-02-22 09:00:00'),
('김가나','2020-02-23 09:00:00'),
('김가나','2020-02-24 09:00:00'),
('김가나','2020-02-25 09:00:00'),
('김가나','2020-02-26 09:00:00'),
('김가나','2020-02-27 09:00:00'),
('김가나','2020-02-28 09:00:00'),
('김가나','2020-02-29 09:00:00'),
('김가나','2020-03-01 09:00:00'),
('김가나','2020-03-02 09:00:00'),
('김가나','2020-03-03 09:00:00'),
('김가나','2020-03-04 09:00:00'),
('김가나','2020-03-05 09:00:00'),
('김가나','2020-03-06 09:00:00'),
('김가나','2020-03-07 09:00:00'),
('김가나','2020-03-08 09:00:00'),
('김가나','2020-03-09 09:00:00'),
('김가나','2020-03-10 09:00:00');

select * from plan;

select count(transDate) from cardinfo where custname = '김가나' and date(transdate) = date(now());
select custname,count(transDate) from cardinfo where custname = '김가나' and year(transDate) = year(now());
select replace(accountnum,'-1','-2') from bankbook where custcode = (select custcode from customer where custname = '김가나') and accountPlanCode = (select planCode from plan where planname = '휴면,해지계좌테스트용');

call make_dormant('김가나','휴면,해지계좌테스트용');
call make_termination('김가나','휴면,해지계좌테스트용');

select cs.custname,
(select count(plancode) from card where plancode = 'B001' and custcode = c.custcode) as 'check',
(select count(plancode) from card where plancode = 'B002' and custcode = c.custcode) as 'credit' 
from card c join customer cs on c.custcode = cs.custcode group by c.custcode;

select cs.custname,
(select count(loanplancode) from loan where loanplancode = 'C001' and custcode = l.custcode) as 'normal',
(select count(loanplancode) from loan where loanplancode = 'C002' and custcode = l.custcode) as 'credit',
(select count(loanplancode) from loan where loanplancode = 'C003' and custcode = l.custcode) as 'card'
from loan l join customer cs on l.custCode = cs.custcode group by l.custcode;

select * from changebankbookdormantinfo;
select * from changebankbookterminationinfo;
drop table changebankbookdormantinfo;
drop table changebankbookterminationinfo;

select accountnum from bankbook where custcode = (select custcode from customer where custname = '김가나') and `accountPlanCode` = 'A001' and (select substring(accountnum,8,1) from bankbook where custcode = (select custcode from customer where custname = '김가나') and `accountPlanCode` = 'A001') = '2';