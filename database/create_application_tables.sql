create table bill_application.bill_application
  (
    bill_id int not null auto_increment primary key,
    bill_name varchar(20),
    day_due int,
    amount decimal(10,2),
    balance decimal(10,2)
  );

create table bill_application.bill
  (
    ledger_id int not null auto_increment primary key,
    bill_id int,
    amount_due decimal(10,2),
    minimum_payment decimal(10,2),
    due_date date,
    date_paid date
  );

  
