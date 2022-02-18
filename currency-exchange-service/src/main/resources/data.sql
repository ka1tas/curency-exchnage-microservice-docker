--create table currency_exchange(currency_from varchar(255),currency_to varchar(255),conversion_multiple decimal,environment varchar(255));
insert into currency_exchange
(currency_from,currency_to,conversion_multiple,environment)
values('USD','INR',65,'');
insert into currency_exchange
(currency_from,currency_to,conversion_multiple,environment)
values('EUR','INR',75,'');
insert into currency_exchange
(currency_from,currency_to,conversion_multiple,environment)
values('AUD','INR',25,'');