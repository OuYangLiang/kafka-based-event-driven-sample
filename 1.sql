create table if not exists `order` (
  `order_id`        bigint      not null  auto_increment comment '主键',
  `user_id`         bigint      not null comment '客户id',
  `order_amount`    int         not null comment '订单金额，单位：分',
  primary key (`order_id`)
) engine=innodb default charset=utf8 comment='订单表';

create table if not exists `order_report` (
  `user_id`         bigint      not null comment '客户id',
  `order_num`       bigint      not null comment '订单数量',
  `order_total`     bigint      not null comment '订单总金额，单位：分',
  primary key (`user_id`)
) engine=innodb default charset=utf8 comment='订单报表';
