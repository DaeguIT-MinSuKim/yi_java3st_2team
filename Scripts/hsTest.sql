select user(), database();


select * from employee e ;
select * from department d ;

select  empCode, empName, empTitle, empAuth, empSalary, empTel, empId, empPwd, d.deptNo
   from employee e left join department d on e.deptNo = d.deptNo 
   order by empCode;