# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table customer (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  address1                  varchar(255),
  constraint pk_customer primary key (id))
;

create table purchase_order (
  order_number              bigint auto_increment not null,
  customer_id               bigint,
  order_date                datetime,
  constraint pk_purchase_order primary key (order_number))
;

alter table purchase_order add constraint fk_purchase_order_customer_1 foreign key (customer_id) references customer (id) on delete restrict on update restrict;
create index ix_purchase_order_customer_1 on purchase_order (customer_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table customer;

drop table purchase_order;

SET FOREIGN_KEY_CHECKS=1;

