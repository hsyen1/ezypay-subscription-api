drop table if exists payment_subscription;
create table payment_subscription (
    id integer auto_increment not null,
    subscription_type varchar(64) not null,
    payment_method varchar(64) not null,
    billing_amount integer not null,
    description varchar(64),
    customer_id integer not null,
    created_date timestamp not null,
    modified_date timestamp not null,
    primary key(id)
);

drop table if exists customer;
create table customer (
    id integer auto_increment not null,
    name varchar(64) not null,
    email varchar(64) not null,
    created_date timestamp not null,
    primary key(id)
);


--- mock data for customer table
insert into customer(id, name, email, created_date)
values(1, 'Han Sern', 'testing@email.com', sysdate());

insert into customer(id, name, email, created_date)
values(2, 'John Doe', 'johndoe@email.com', sysdate());

--- mock data for payment_subscription table
insert into payment_subscription(id, subscription_type, payment_method, billing_amount, description, customer_id, created_date, modified_date)
values(1, 'WEEKLY', 'CREDIT_CARD', 20, 'Weekly payment for gym access', 1, sysdate(), sysdate());

insert into payment_subscription(id, subscription_type, payment_method, billing_amount, description, customer_id, created_date, modified_date)
values(2, 'MONTHLY', 'DEBIT_CARD', 80, 'Monthly payment for gym access', 2, sysdate(), sysdate());