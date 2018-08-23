create table book (id varchar(255) not null, create_time datetime(6) not null, create_user_id varchar(255), create_user_name varchar(255), is_deleted integer, update_time datetime(6), update_user_id varchar(255), update_user_name varchar(255), address varchar(255), book_type integer, name varchar(255), page integer not null, pub_date datetime(6), sell_price decimal(19,2), shop_id varchar(255), primary key (id)) engine=InnoDB;
create table permission (id varchar(255) not null, create_time datetime(6) not null, create_user_id varchar(255), create_user_name varchar(255), is_deleted integer, update_time datetime(6), update_user_id varchar(255), update_user_name varchar(255), code varchar(255), comments varchar(255), name varchar(255), num integer, type integer, parent_id varchar(255), primary key (id)) engine=InnoDB;
create table role (id varchar(255) not null, create_time datetime(6) not null, create_user_id varchar(255), create_user_name varchar(255), is_deleted integer, update_time datetime(6), update_user_id varchar(255), update_user_name varchar(255), comments varchar(255), name varchar(255), num integer, primary key (id)) engine=InnoDB;
create table role_permission (id varchar(255) not null, create_time datetime(6) not null, create_user_id varchar(255), create_user_name varchar(255), is_deleted integer, update_time datetime(6), update_user_id varchar(255), update_user_name varchar(255), permission_id varchar(255), role_id varchar(255), primary key (id)) engine=InnoDB;
create table shop (id varchar(255) not null, create_time datetime(6) not null, create_user_id varchar(255), create_user_name varchar(255), is_deleted integer, update_time datetime(6), update_user_id varchar(255), update_user_name varchar(255), address varchar(255), name varchar(255), primary key (id)) engine=InnoDB;
create table sys_loginfo (id varchar(255) not null, create_time datetime(6), message varchar(255), module varchar(255), param_info varchar(3000), user_id varchar(255), user_name varchar(255), primary key (id)) engine=InnoDB;
create table user (id varchar(255) not null, create_time datetime(6) not null, create_user_id varchar(255), create_user_name varchar(255), is_deleted integer, update_time datetime(6), update_user_id varchar(255), update_user_name varchar(255), enable_type integer, last_login_ip varchar(20), last_login_time datetime(6), password varchar(20), realname varchar(50), username varchar(20), avatar varchar(200), email varchar(50), nickname varchar(50), phone varchar(20), primary key (id)) engine=InnoDB;
create table user_role (id varchar(255) not null, create_time datetime(6) not null, create_user_id varchar(255), create_user_name varchar(255), is_deleted integer, update_time datetime(6), update_user_id varchar(255), update_user_name varchar(255), role_id varchar(255), user_id varchar(255), primary key (id)) engine=InnoDB;
create index IDX_LOG_USERID on sys_loginfo (user_id);
create index IDX_LOG_DATE on sys_loginfo (create_time);