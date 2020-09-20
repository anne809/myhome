DROP table member;

create table member(
id			 	varchar2(15),
password 	 	varchar2(10),
name 		 	varchar2(15),
age				number,
gender 		 	varchar2(5),
email 		 	varchar2(130),
code 		 	number,
address 	 	varchar2(100),
address_datail  varchar2(100),
address_datail2 varchar2(100),
PRIMARY KEY(id)
);

select*
from member;
