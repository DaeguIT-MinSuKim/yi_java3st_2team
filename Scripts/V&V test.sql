desc employee;
desc customer;
desc department;
desc plan;
desc performance;
desc bankbook;
desc loan;
desc card;

insert into department values(1,'인사'),
							 (2,'고객');
select * from department;

insert into employee values
('A001','나지점','지점장','AD',10000000,'010-1111-1111','test','test',2),
('A002','나인사','부장','HR',6000000,'010-2222-2222','test2','test2',1),
('A003','나고객','부장','CS',6000000,'010-3333-3333','test3','test3',2);

select * from employee;

insert into customer values
('C001','다이아','D',1,'대구광역시','010-4444-4444','test4','test4'),
('C002','플레','P',2,'대구광역시','010-5555-5555','test5','test5'),
('C003','골드','G',3,'대구광역시','010-6666-6666','test6','test6'),
('C004','실버','S',4,'대구광역시','010-7777-7777','test7','test7'),
('C005','브론즈','B',5,'대구광역시','010-8888-8888','test8','test8');

select * from customer;

insert into plan values 
('A001','AA001','예금통장','테스트용','N'),
('A002','AB001','적금통장','테스트용','N'),
('A003','AC001','마이너스상통장','테스트용','N'),
('B001','BA001','체크카드','테스트용','N'),
('B002','BB001','신용카드','테스트용','N'),
('C001','CA001','일반대출','테스트용','N'),
('C002','CB001','신용대출','테스트용','N'),
('C003','CC001','카드론','테스트용','N');

select * from plan;

insert into bankbook values 
('293133-11-000001','C001','A001',"2020-02-05 09:00:00",0.01,500000000);
select * from bankbook;

desc card;

insert into card values
('2931330100000010','C001','B001','111','체크카드 테스트 상품',"2020-02-05 09:00:00",null,null);
select * from card;
#카드 부분은 추후 구현시 카드한도가 신용카드일때만 한도가 정해져야 하고, 카드잔액이 체크카드일시 예금계좌 잔액과 같아야 하므로 트리거가 필요함
insert into loan values
('293133-01-000001','C001','C001',"2020-02-05 09:00:00",0.01,100000000);
select * from loan;

desc performance;
insert into performance values ('A001','A003','C001');
select * from performance;