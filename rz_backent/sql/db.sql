
drop database api_platform;
create database  if not exists api_platform;

use api_platform;

-- 接口信息
create table if not exists api_platform.`interface_info`
(
    `id` int not null comment '主键' primary key,
    `name` varchar(256) not null comment '名称',
    `description` varchar(256) null comment '描述',
    `url` varchar(512) not null comment '网址',
    `requestHeader` text null comment '请求头',
    `responseHeader` text null comment '响应头',
#     'requestParameter' text null comment '请求参数',
    `status` varchar(256) default '0' not null comment '接口状态：0-关闭 1-开启',
    `method` varchar(256) not null comment '请求类型',
    `userId` bigint not null comment '创建人',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDeleted` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '接口信息';

insert into api_platform.`interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values (1, '赵熠彤', '0wv', 'www.stephen-lueilwitz.com', 'F1', 'lYzXk', '0', 'get', 958);
insert into api_platform.`interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values (2, '钱乐驹', 'ZLGT', 'www.lesli-hirthe.net', 'rGv6L', 'YGNEh', '0', 'get', 46);
insert into api_platform.`interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values (3, '梁绍齐', '8pRF', 'www.ellamae-ziemann.org', 'Pe', 'ysZ', '0', 'get', 766210);
insert into api_platform.`interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values (4, '贾博超', 'da', 'www.hue-abshire.org', 'aU', 'Gm', '0', 'get', 32);
insert into api_platform.`interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values (5, '苏皓轩', 'of', 'www.augustus-aufderhar.co', '710Qh', 'LemFg', '0', 'get', 365669);
insert into api_platform.`interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values (6, '郭荣轩', 'gYQW', 'www.gus-heathcote.info', 'M7f', 'TYu63', '0', 'get', 66);
insert into api_platform.`interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values (7, '熊明哲', 'CWn', 'www.leonardo-boyle.org', 'r1T', 'lmmIs', '0', 'get', 7);
insert into api_platform.`interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values (8, '郝鹤轩', 'bAnCg', 'www.micheline-grant.name', 'uRvj', '1i', '0', 'get', 1839518);
insert into api_platform.`interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values (9, '于鸿涛', 'cq', 'www.wayne-lowe.biz', 'z2Jmb', 'FvWh8', '0', 'get', 3099725331);
insert into api_platform.`interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values (10, '谢思', 'zA', 'www.brook-rippin.com', 'oQ', 'rJWYS', '0', 'get', 71360522);
insert into api_platform.`interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values (11, '邱弘文', 'EMdWN', 'www.ramonita-gutmann.info', 'Fom', 'wreU', '0', 'get', 5980717);
insert into api_platform.`interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values (12, '郑文博', 'rcfOn', 'www.neville-padberg.com', 'mhCS2', 'ny7s', '0', 'get', 58);
insert into api_platform.`interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values (13, '黎浩', 'A7D3A', 'www.roberto-collier.net', 'Ycg', 'y4Y', '0', 'get', 3945458271);
insert into api_platform.`interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values (14, '范展鹏', 'EOj0', 'www.damian-orn.io', 'ZXyW', 'WXE0', '0', 'get', 38521763);
insert into api_platform.`interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values (15, '梁鹏涛', 'K4i6p', 'www.raymundo-bode.io', 'zsX9', '6I39p', '0', 'get', 4499879);
insert into api_platform.`interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values (16, '蒋烨霖', 'rcbpF', 'www.marcelina-raynor.net', '5QP', 'CD', '0', 'get', 91286);
insert into api_platform.`interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values (17, '黄智渊', 'pZhM', 'www.jame-predovic.biz', 'HZLw', 'x490r', '0', 'get', 55433);
insert into api_platform.`interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values (18, '于建辉', 'NpJN', 'www.pearl-oconnell.co', '1Vgk', 'IHu6E', '0', 'get', 56512);
insert into api_platform.`interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values (19, '崔昊强', 'xyRd', 'www.antone-ziemann.info', '63kP', 'fDuL9', '0', 'get', 19372400);
insert into api_platform.`interface_info` (`id`, `name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values (20, '汪擎宇', 'mdlJ', 'www.janise-marks.biz', '4XeiV', 'o3', '0', 'get', 419590765);

drop table user;

use api_platform;
-- 用户接口关联表
create table if not exists user_interfaceInfo
(
    id              bigint auto_increment comment 'id' primary key,
    userId          varchar(256)                             null comment '调用者id',
    interfaceInfoId     varchar(256)                             not null comment '接口id',
    totalCalls      int                                      not null comment '总调用次数',
    remainingCalls  int                                      not null comment '剩余调用次数',
    status          int default 0                            not null comment '状态:0-正常 1-异常',
    create_time     datetime     default CURRENT_TIMESTAMP   not null comment '创建时间',
    update_time     datetime     default CURRENT_TIMESTAMP   not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete       tinyint      default 0                   not null comment '是否删除'
) comment '用户接口关联信息表';

-- 用户接口关系表
create table if not exists user
(
    id            bigint auto_increment comment 'id' primary key,
    user_name     varchar(256)                           null comment '用户昵称',
    user_account  varchar(256)                           not null comment '账号',
    user_avatar   varchar(1024)                          null comment '用户头像',
    gender        tinyint                                null comment '性别',
    user_role     varchar(256) default 'user'            not null comment '用户角色：user / admin',
    user_password varchar(512)                           not null comment '密码',
    access_key    varchar(256)                           null comment 'access_key',
    secret_key    varchar(256)                           null comment 'secret_key',
    create_time   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint      default 0                 not null comment '是否删除',
    constraint uni_user_account
        unique (user_account)
) comment '用户';

-- 帖子表
create table if not exists post
(
    id            bigint auto_increment comment 'id' primary key,
    age           int comment '年龄',
    gender        tinyint  default 0                 not null comment '性别（0-男, 1-女）',
    education     varchar(512)                       null comment '学历',
    place         varchar(512)                       null comment '地点',
    job           varchar(512)                       null comment '职业',
    contact       varchar(512)                       null comment '联系方式',
    loveExp       varchar(512)                       null comment '感情经历',
    content       text                               null comment '内容（个人介绍）',
    photo         varchar(1024)                      null comment '照片地址',
    reviewStatus  int      default 0                 not null comment '状态（0-待审核, 1-通过, 2-拒绝）',
    reviewMessage varchar(512)                       null comment '审核信息',
    viewNum       int                                not null default 0 comment '浏览数',
    thumbNum      int                                not null default 0 comment '点赞数',
    userId        bigint                             not null comment '创建用户 id',
    createTime    datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete      tinyint  default 0                 not null comment '是否删除'
) comment '帖子';


