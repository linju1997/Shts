-- Drop table
drop table if exists user;
-- 用户表
create table user
(
  `id`       int auto_increment,
  `username` varchar(20) unique default ' '          not null comment '用户名',
  `password` varchar(50)        default ' '          not null comment '密码',
  `email`    varchar(50) unique default ' '          not null comment '邮箱',
  `phone`    varchar(10)        default ' '          not null comment '手机号码',
  `address`  varchar(50)        default ' '          not null comment '用户地址',
  `remark`   varchar(50)        default ' '          not null comment '备注',
  `created`  timestamp          default current_timestamp not null comment '创建时间',
  `modify`   timestamp          default current_timestamp not null comment '修改时间',
  primary key (`id`)
) engine = InnoDB
  default charset = utf8
  auto_increment = 10000;
-- 创建索引
create index idx_username on user (`username`);
create index idx_email on user (`email`);


