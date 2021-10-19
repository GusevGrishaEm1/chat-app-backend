create table ban_info (
usr_id bigint not null,
date_of_ban datetime not null,
minutes integer not null,
primary key (usr_id));

create table disc_info (
usr_in_room_id bigint not null,
date_of_disc datetime not null,
minutes integer not null,
primary key (usr_in_room_id));

create table hibernate_sequence (next_val bigint);
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );

create table message (
id bigint not null,
date_of_send datetime not null,
text varchar(1024) not null,
room_id bigint not null,
usr_id bigint not null,
primary key (id));

create table room (
id bigint not null,
name varchar(64) not null,
private_room bit,
type integer not null,
primary key (id));

create table usr (
id bigint not null,
banned bit,
password varchar(64) not null,
role integer not null,
username varchar(64) not null,
primary key (id));

create table usr_in_room (
id bigint not null,
disconnected bit,
owner bit,
room_id bigint not null,
usr_id bigint not null,
primary key (id));

alter table room add constraint name_uk unique (name);
alter table usr add constraint username_uk unique (username);
alter table ban_info add constraint ban_info_usr_fk foreign key (usr_id) references usr (id);
alter table disc_info add constraint disc_info_usr_in_room_fk foreign key (usr_in_room_id) references usr_in_room (id);
alter table message add constraint message_room_fk foreign key (room_id) references room (id);
alter table message add constraint message_usr_fk foreign key (usr_id) references usr (id);
alter table usr_in_room add constraint usr_in_room_room_fk foreign key (room_id) references room (id);
alter table usr_in_room add constraint usr_in_room_usr_fk foreign key (usr_id) references usr (id);