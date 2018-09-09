CREATE TABLE IF NOT EXISTS `order` (
  `order_id`        bigint      NOT NULL  auto_increment COMMENT '主键',
  `user_id`         bigint      NOT NULL COMMENT '客户ID',
  `order_amount`    int         NOT NULL COMMENT '订单金额，单位：分',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

CREATE TABLE IF NOT EXISTS `order_report` (
  `user_id`         bigint      NOT NULL COMMENT '客户ID',
  `order_num`       bigint      NOT NULL COMMENT '订单数量',
  `order_total`     bigint      NOT NULL COMMENT '订单总金额，单位：分',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单报表';
