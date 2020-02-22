select user(), database();


select * from employee e ;
select * from department d ;

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