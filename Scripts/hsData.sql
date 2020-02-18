('B001','나지점','지점장','AD',10000000,'010-1234-1234','ADid',password('testforAD'),2),
('B002','장현서','부지점장','CS',8000000,'010-4444-7767','CSid1',password('testforCS1'),2),
('B003','박인선','부장','CS',6000000,'010-2992-2222','CSid2',password('testforCS2'),2),
('B004','황하나','차장','CS',5000000,'010-2222-1111','CSid3',password('testforCS3'),2),
('B005','현재승','과장','CS',4000000,'010-2282-4848','CSid4',password('testforCS4'),2),
('B006','황태원','대리','CS',3000000,'010-2902-5959','CSid5',password('testforCS5'),2),
('B007','나고객','사원','CS',2000000,'010-2212-7766','CSid6',password('testforCS6'),2);


insert into performance values ('A001','A003','C001');
select * from performance;


-- 전체직원수
select count(*) from bank.employee;  -- 3출력
-- 부서별 직원수
select count(*) from bank.employee where deptNo =1; -- 1명
select count(*) from bank.employee where deptNo =2; -- 2명
-- 직급별 직원수(등급 같이 표시)
select concat(count(*),'명(',`empTitle`,')') as '직급별사원수(직책)' from bank.employee 
  group by `empTitle`;
-- 연간 급여 총액/ 월별 급여 총액
drop procedure if exists proc_total_avg;
delimiter $
  create procedure proc_total_avg()
    begin 
	    declare empSalary1 int default 0;
	    declare empCnt int default -1;
	    declare empTotalSalary int default 0;
	    declare endOfRow boolean default false;
	    
	   declare salaryCursor cursor for
	      select empSalary from bank.employee;
	     
	     declare continue handler for not found set endOfRow = true;
	    
	    open salaryCursor;
	   
	   while(!endOfRow) do 
	     set empCnt = empCnt +1;
	     set empTotalSalary = empTotalSalary + empSalary1;
	     fetch salaryCursor into empSalary1;
	   end while;
	  
	  select concat('모든사원의 총 급여-->',empTotalSalary) as total,concat('모든사원의 평균 급여-->',empTotalSalary/empSalary1) as average;
	  
	 close salaryCursor;
    end $
delimiter ;

call proc_total_avg();