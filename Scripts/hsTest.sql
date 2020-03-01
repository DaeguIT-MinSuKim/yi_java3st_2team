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


create view vipTable as select custCode as vip from customer c where `custRank`="A";
select * from vipTable;

insert into customer values("C001", "김가나", "B", 1, "서울시 강남구", "123-1234-1234");
update customer set `custRank`='A' where `custCode`='C001';

select e.empCode, e.empName, e.empTitle, count(p.custCode) as perf, vip
from employee e left join performance p on e.`empCode`=p.`empCode` left join customer c on p.`custCode`=c.`custCode` left join viptable v on p.`custCode`= v.vip
group by p.`empCode`;


select e.empCode, e.empName, e.empTitle, count(if(p.custCode=null,0,p.custCode)) as perf , if(count(if(p.custCode=null,0,p.custCode))>=10,e.`empSalary`*0.1,0) as bonus, if(p.`planCode`='A001',vip,null) as vip
from employee e left join performance p on e.`empCode` = p.`empCode`  left join customer c on p.`custCode`=c.`custCode` left join viptable v on p.`custCode`= v.vip
group by e.`empCode`;
