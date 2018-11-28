-- 创建数据库
CREATE DATABASE captcha;

-- 创建用户
-- set global validate_password.length = 6;
-- set global validate_password.policy = low;
CREATE USER 'captcha'@'%' IDENTIFIED BY 'captcha';

-- 授权用户数据库权限
GRANT ALL ON captcha.* TO 'captcha'@'%' WITH GRANT OPTION;

-- 使用数据库
use captcha;

-- 解决中文乱码
set names utf8;

-- 检测体表
create table `detector` (
  `id` varchar(32) not null comment 'id',
  `name` varchar(32) not null comment '检测体名称',
  `create_time`	timestamp null default CURRENT_TIMESTAMP comment '创建时间',
primary key (`id`)
) engine=innodb comment '检测体表';
