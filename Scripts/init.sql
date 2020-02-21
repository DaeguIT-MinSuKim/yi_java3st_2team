-- 내 스키마
DROP SCHEMA IF EXISTS bank;

-- 내 스키마
CREATE SCHEMA bank;

-- 통장
CREATE TABLE bank.BankBook (
	accountNum      char(16) NOT NULL COMMENT '계좌번호', -- 계좌번호
	custCode        char(4)  NOT NULL COMMENT '고객코드', -- 고객코드
	accountPlanCode char(4)  NOT NULL COMMENT '통장상품코드', -- 통장상품코드
	accountOpenDate DATETIME NOT NULL COMMENT '계좌개설일', -- 계좌개설일
	accountInterest FLOAT    NOT NULL COMMENT '이자율', -- 이자율
	accountBalance  BIGINT   NOT NULL COMMENT '잔액' -- 잔액
)
COMMENT '통장';

-- 통장
ALTER TABLE bank.BankBook
	ADD CONSTRAINT PK_BankBook -- 통장 기본키
		PRIMARY KEY (
			accountNum -- 계좌번호
		);

-- 사원
CREATE TABLE bank.Employee (
	empCode   char(4)     NOT NULL COMMENT '사원코드', -- 사원코드
	empName   VARCHAR(5)  NULL     COMMENT '사원이름', -- 사원이름
	empTitle  VARCHAR(10) NULL     COMMENT '사원직책', -- 사원직책
	empAuth   char(2)     NULL     COMMENT '사원권한', -- 사원권한
	empSalary INTEGER     NULL     COMMENT '사원월급', -- 사원월급
	empTel    char(13)    NULL     COMMENT '사원연락처', -- 사원연락처
	empId     varchar(12) NULL     COMMENT '사원아이디', -- 사원아이디
	empPwd    varchar(41) NULL     COMMENT '사원비밀번호', -- 사원비밀번호
	deptNo    INTEGER     NOT NULL COMMENT '부서번호' -- 부서번호
)
COMMENT '사원';

-- 사원
ALTER TABLE bank.Employee
	ADD CONSTRAINT PK_Employee -- 담당고객
		PRIMARY KEY (
			empCode -- 사원코드
		);

-- 부서
CREATE TABLE bank.Department (
	deptNo   INTEGER     NOT NULL COMMENT '부서번호', -- 부서번호
	deptName VARCHAR(10) NULL     COMMENT '부서이름' -- 부서이름
)
COMMENT '부서';

-- 부서
ALTER TABLE bank.Department
	ADD CONSTRAINT PK_Department -- 부서 기본키
		PRIMARY KEY (
			deptNo -- 부서번호
		);

-- 고객
CREATE TABLE bank.Customer (
	custCode   char(4)     NOT NULL COMMENT '고객코드', -- 고객코드
	custName   VARCHAR(5)  NULL     COMMENT '고객이름', -- 고객이름
	custRank   char(1)     NULL     COMMENT '고객등급', -- 고객등급
	custCredit INTEGER(1)  NULL     COMMENT '고객신용등급', -- 고객신용등급
	custAddr   varchar(50) NULL     COMMENT '고객주소', -- 고객주소
	custTel    char(13)    NULL     COMMENT '고객연락처' -- 고객연락처
)
COMMENT '고객';

-- 고객
ALTER TABLE bank.Customer
	ADD CONSTRAINT PK_Customer -- 고객 기본키
		PRIMARY KEY (
			custCode -- 고객코드
		);

-- 고객상품
CREATE TABLE bank.Plan (
	planCode   char(4)      NOT NULL COMMENT '상품코드', -- 상품코드
	planDetail char(5)      NOT NULL COMMENT '상품세부코드', -- 상품세부코드
	planName   VARCHAR(30)  NULL     COMMENT '상품이름', -- 상품이름
	planDesc   varchar(100) NULL     COMMENT '상품설명', -- 상품설명
	planDiv    char(1)      NULL     COMMENT '상품구분코드' -- 상품구분코드
)
COMMENT '고객상품';

-- 고객상품
ALTER TABLE bank.Plan
	ADD CONSTRAINT PK_Plan -- 고객상품 기본키
		PRIMARY KEY (
			planCode -- 상품코드
		);

-- 대출
CREATE TABLE bank.Loan (
	loanAccountNum char(16) NOT NULL COMMENT '대출계좌번호', -- 대출계좌번호
	custCode       char(4)  NOT NULL COMMENT '고객코드', -- 고객코드
	loanPlanCode   char(4)  NOT NULL COMMENT '대출상품코드', -- 대출상품코드
	loanDate       DATETIME NULL     COMMENT '대출날짜', -- 대출날짜
	loanInterest   FLOAT    NULL     COMMENT '대출이자율', -- 대출이자율
	loanBalance    BIGINT   NULL     COMMENT '대출잔액' -- 대출잔액
)
COMMENT '대출';

-- 대출
ALTER TABLE bank.Loan
	ADD CONSTRAINT PK_Loan -- 대출 기본키
		PRIMARY KEY (
			loanAccountNum -- 대출계좌번호
		);

-- 카드
CREATE TABLE bank.Card (
	cardNum       char(16)    NOT NULL COMMENT '카드번호', -- 카드번호
	custCode      char(4)     NOT NULL COMMENT '고객코드', -- 고객코드
	planCode      char(4)     NOT NULL COMMENT '상품코드', -- 상품코드
	cardSecuCode  char(3)     NOT NULL COMMENT '카드보안코드', -- 카드보안코드
	cardName      VARCHAR(30) NULL     COMMENT '카드이름', -- 카드이름
	cardIssueDate DATETIME    NULL     COMMENT '카드발급일', -- 카드발급일
	cardLimit     INTEGER     NULL     COMMENT '카드한도', -- 카드한도
	cardBalance   BIGINT      NULL     COMMENT '카드잔액' -- 카드잔액
)
COMMENT '카드';

-- 카드
ALTER TABLE bank.Card
	ADD CONSTRAINT PK_Card -- 카드 기본키
		PRIMARY KEY (
			cardNum -- 카드번호
		);

-- 실적
CREATE TABLE bank.Performance (
	planCode char(4) NOT NULL COMMENT '상품코드', -- 상품코드
	empCode  char(4) NOT NULL COMMENT '사원코드', -- 사원코드
	custCode char(4) NOT NULL COMMENT '고객코드' -- 고객코드
)
COMMENT '실적';

-- 실적
ALTER TABLE bank.Performance
	ADD CONSTRAINT PK_Performance -- 실적 기본키
		PRIMARY KEY (
			planCode, -- 상품코드
			empCode,  -- 사원코드
			custCode  -- 고객코드
		);

-- 통장
ALTER TABLE bank.BankBook
	ADD CONSTRAINT FK_Customer_TO_BankBook -- 고객 -> 통장
		FOREIGN KEY (
			custCode -- 고객코드
		)
		REFERENCES bank.Customer ( -- 고객
			custCode -- 고객코드
		);

-- 통장
ALTER TABLE bank.BankBook
	ADD CONSTRAINT FK_Plan_TO_BankBook -- 고객상품 -> 통장
		FOREIGN KEY (
			accountPlanCode -- 통장상품코드
		)
		REFERENCES bank.Plan ( -- 고객상품
			planCode -- 상품코드
		);

-- 사원
ALTER TABLE bank.Employee
	ADD CONSTRAINT FK_Department_TO_Employee -- 부서 -> 사원
		FOREIGN KEY (
			deptNo -- 부서번호
		)
		REFERENCES bank.Department ( -- 부서
			deptNo -- 부서번호
		);

-- 대출
ALTER TABLE bank.Loan
	ADD CONSTRAINT FK_Customer_TO_Loan -- 고객 -> 대출
		FOREIGN KEY (
			custCode -- 고객코드
		)
		REFERENCES bank.Customer ( -- 고객
			custCode -- 고객코드
		);

-- 대출
ALTER TABLE bank.Loan
	ADD CONSTRAINT FK_Plan_TO_Loan -- 고객상품 -> 대출
		FOREIGN KEY (
			loanPlanCode -- 대출상품코드
		)
		REFERENCES bank.Plan ( -- 고객상품
			planCode -- 상품코드
		);

-- 카드
ALTER TABLE bank.Card
	ADD CONSTRAINT FK_Customer_TO_Card -- 고객 -> 카드
		FOREIGN KEY (
			custCode -- 고객코드
		)
		REFERENCES bank.Customer ( -- 고객
			custCode -- 고객코드
		);

-- 카드
ALTER TABLE bank.Card
	ADD CONSTRAINT FK_Plan_TO_Card -- 고객상품 -> 카드
		FOREIGN KEY (
			planCode -- 상품코드
		)
		REFERENCES bank.Plan ( -- 고객상품
			planCode -- 상품코드
		);

-- 실적
ALTER TABLE bank.Performance
	ADD CONSTRAINT FK_Customer_TO_Performance -- 고객 -> 실적
		FOREIGN KEY (
			custCode -- 고객코드
		)
		REFERENCES bank.Customer ( -- 고객
			custCode -- 고객코드
		);

-- 실적
ALTER TABLE bank.Performance
	ADD CONSTRAINT FK_Employee_TO_Performance -- 사원 -> 실적
		FOREIGN KEY (
			empCode -- 사원코드
		)
		REFERENCES bank.Employee ( -- 사원
			empCode -- 사원코드
		);

-- 실적
ALTER TABLE bank.Performance
	ADD CONSTRAINT FK_Plan_TO_Performance -- 고객상품 -> 실적
		FOREIGN KEY (
			planCode -- 상품코드
		)
		REFERENCES bank.Plan ( -- 고객상품
			planCode -- 상품코드
		);
/*
grant all privileges on *.* to 'root'@'%' identified by 'bank';
create user if not exists 'hana'@'192.168.10.%';
grant all privileges on bank.* to 'hana'@'192.168.10.%' identified by 'bank';
flush privileges;

create user if not exists 'hyeonseo'@'192.168.10.%';
grant all privileges on bank.* to 'hyeonseo'@'192.168.10.%' identified by 'bank';
flush privileges;

create user if not exists 'insun'@'192.168.10.%';
grant all privileges on bank.* to 'insun'@'192.168.10.%' identified by 'bank';
flush privileges;
*/