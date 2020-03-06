select user(), database();


select * from employee e ;
select * from department d ;
select * from performance p;
desc performance;
select * from customer c;
select * from plan p;
select * from card c;


select  empCode, empName, empTitle, empAuth, empSalary, empTel, empId, empPwd, d.deptNo
   from employee e left join department d on e.deptNo = d.deptNo 
   order by empCode;
   
  
  select  empCode, empName, empTitle, empAuth, empSalary, empTel, empId, empPwd, d.deptName, d.deptNo
   from employee e left join department d on e.deptNo = d.deptNo 
   where empName= '장현서' ;
  
  update employee set empName='aa', empTitle='음' where empCode='B008';
   
  
  select deptNo, deptName from department order by deptNo;
  
 desc employee;
 
delete from employee where `empName` ="짱난다잉";

update employee set empName='짜증나',empTitle='사원',empAuth=null,empSalary=1000000,empTel='111',empId='aaa',empPwd=password('1111'),deptNo=1 where empCode='B008';

select count(`custCode`) , `empCode` , 
  from performance p
  group by `empCode`;
 
select  e.empCode, e.empName, e.empTitle, count(p.custCode) as perf, if(c.custRank="D",p.custCode,null) as vip
from employee e left join performance p on e.`empCode`=p.`empCode` left join customer c on p.`custCode` = c.`custCode`
group by p.`empCode`;


select * from performance p;


drop view if exists vipTable;  
create view vipTable as select custCode as vip from customer c where `custRank`="B";
select * from vipTable;

-- vip커스터머가 추가될 때마다 vipTable에 입력되는 트리거 생성 
drop trigger if exists vip_trigger;
delimiter $
 create trigger vip_trigger
 after insert on customer
 for each row 
 
 begin 
	 insert into viptable values(new.custCode);
 end $
delimiter ;

-- 데이터 합친 뒤 vip고객이 vip전용 상품을 넣었을 경우 트리거를 통해 view테이블에 제대로 입력되는지, 되고 나서 사원 업무 조회에서 업데이트 되는지 확인할 것 0306


insert into customer values("C001", "김가나", "B", 1, "서울시 강남구", "123-1234-1234");
update customer set `custRank`='A' where `custCode`='C001';

-- 사원업무 조회에서 사용
select e.empCode, e.empName, e.empTitle, count(if(p.custCode=null,0,p.custCode)) as perf , if(count(if(p.custCode=null,0,p.custCode))>=10,e.`empSalary`*0.1,0) as bonus, if(p.`planCode`='A001',vip,null) as vip
from employee e left join performance p on e.`empCode` = p.`empCode`  left join customer c on p.`custCode`=c.`custCode` left join viptable v on p.`custCode`= v.vip
group by e.`empCode`;


-- 삭제된 사원의 정보를 담는 테이블 생성 
create table deleted_employee (
 empCode char(4),
 empName varchar(5),
 empTitle varchar(20),
 empTel char(13),
 empId varchar(12),
 deptNo int
);
drop trigger if exists deleted_emp_trigger;
delimiter $
 create trigger deleted_employee
 before delete on employee
 for each row 
 
 begin 
	 insert into deleted_employee values(old.empCode, old.empName, old.empTitle, old.empTel, old.empId, old.deptNo);
 end $
delimiter ;

select * from deleted_employee ;


-- 통계부분 ----------------------------------------------------------------------------------------------------
-- 전체 직원 수 구하기
select count(*) from employee e ;
-- 고객팀 직원 수
select count(*) from employee e  where `deptNo` =2;
select count(*) from employee e  where `deptNo` =1;

