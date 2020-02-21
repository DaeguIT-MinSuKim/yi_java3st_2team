select user(), database ();
select * from customer c ;
select * from plan p ;

insert into customer values("C001", "김가나", "B", 1, "서울시 강남구", "123-1234-1234");
insert into customer values("C002", "김다라", "S", 2, "서울시 마포구", "234-4578-5434"),
						   ("C003", "김마바", "G", 3, "대구시 서구", "456-4567-4578"),
						   ("C004", "김사아", "P", 4, "대구시 수성구", "123-4567-7894"),
						   ("C005", "김자차", "D", 5, "대구시 달서구", "124-6325-5788");
						   
insert into plan values("A001", "AA001", "슈퍼정기예금", "가입자 맞춤형 다기능 정기예금", "V"),
					   ("A002", "AB001", "직장인 우대 적금", "직장인의 재테크 적금", "N"),
					   ("A003", "AC001", "내집마련 통장", "목돈 마련을 위한 마이너스 통장", "N"),
					   ("B001", "BA001", "Easy 적립 카드", "포인트 적립을 위한 체크카드", "N"),
					   ("B002", "BB001", "올쇼핑 카드", "쇼핑을 위한 신용카드", "N"),
					   ("C001", "CA001", "행복 대출", "소득 입증 고객을 위한 대출", "N"),
					   ("C002", "CB001", "직장인 신용 대출", "정규직 공무원을 위한 신용대출", "N"),
					   ("C003", "CC001", "생활안정 대출", "생활비를 위한 카드론", "N");

insert into plan values("A004", "AA002", "일반정기예금", "일반 정기예금", "N");


-- test 
select custCode, custName, custRank, custCredit, custAddr, custTel from customer;
select planCode, planDetail, planName, planDesc, planDiv from plan;