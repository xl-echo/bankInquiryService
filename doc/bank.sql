create table operate_log
(
    id             bigint(11) auto_increment comment '自增id'
        primary key,
    param_context  text                                null comment '请求参数',
    result_context text                                null comment '返回参数',
    type           char                                null comment '请求类型 1:发送, 0:接收',
    `desc`         varchar(200)                        null comment '被请求方法上标记的日志描述',
    request_ip     char(15)                            null comment '请求或被请求端Ip',
    status         char(3)   default '200'             null comment '请求状态',
    create_time    timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    create_user    varchar(45)                         null comment '创建人',
    update_time    timestamp default CURRENT_TIMESTAMP null comment '更新时间',
    update_user    varchar(45)                         null comment '更新人'
)
    comment '银行请求日志记录表';

create table springboard_machine_addr
(
    id          bigint(11) auto_increment comment '自增id'
        primary key,
    bank_code   char(3) default '' null comment '银行编号',
    machine_id  char(7)            null comment '跳板机ID',
    sign_ip     char(15)           null comment '登录ip',
    sign_port   char(7)            null comment '登录端口',
    trade_ip    char(15)           null comment '交易ip',
    trade_port  char(7)            null comment '交易端口',
    channel_id  char(6)            null comment '渠道ID',
    status      char               null comment '状态 0: 无效 1: 有效',
    create_time datetime           null comment '创建时间',
    create_user varchar(45)        null comment '创建人',
    update_time datetime           null comment '更新时间',
    update_user varchar(45)        null comment '更新人'
)
    comment '银行请求日志记录表';

