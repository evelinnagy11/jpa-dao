create sequence hibernate_sequence start with 1 increment by 1

    create table Book (
       id bigint not null,
        author varchar(255) not null,
        available boolean not null,
        format integer not null,
        isbn13 varchar(255) not null,
        pages integer not null,
        publicationDate date,
        publisher varchar(255) not null,
        title varchar(255) not null,
        primary key (id)
    )

    create table Todo (
       id bigint not null,
        created timestamp not null,
        description varchar(255) not null,
        done boolean not null,
        dueDate date,
        priority varchar(255) not null,
        version bigint not null,
        primary key (id)
    )

    create table Todo_tags (
       Todo_id bigint not null,
        tag varchar(255) not null
    )

    create table User (
       id bigint not null,
        email varchar(255) not null,
        name varchar(255) not null,
        password varchar(255) not null,
        username varchar(255) not null,
        primary key (id)
    )

    create table UserTodo (
       id bigint not null,
        created timestamp not null,
        description varchar(255) not null,
        done boolean not null,
        dueDate date,
        priority varchar(255) not null,
        version bigint not null,
        user_id bigint not null,
        primary key (id)
    )

    alter table Book 
       add constraint UK_919tmsbnljwad41x7od7ks7py unique (isbn13)

    alter table User 
       add constraint UK_jreodf78a7pl5qidfh43axdfb unique (username)

    alter table UserTodo 
       add constraint FKasqvcnh08o8er5f7mt41tja99 
       foreign key (user_id) 
       references User
create sequence hibernate_sequence start with 1 increment by 1

    create table Book (
       id bigint not null,
        author varchar(255) not null,
        available boolean not null,
        format integer not null,
        isbn13 varchar(255) not null,
        pages integer not null,
        publicationDate date,
        publisher varchar(255) not null,
        title varchar(255) not null,
        primary key (id)
    )

    create table Todo (
       id bigint not null,
        created timestamp not null,
        description varchar(255) not null,
        done boolean not null,
        dueDate date,
        priority varchar(255) not null,
        version bigint not null,
        primary key (id)
    )

    create table Todo_tags (
       Todo_id bigint not null,
        tag varchar(255) not null
    )

    create table User (
       id bigint not null,
        email varchar(255) not null,
        name varchar(255) not null,
        password varchar(255) not null,
        username varchar(255) not null,
        primary key (id)
    )

    create table UserTodo (
       id bigint not null,
        created timestamp not null,
        description varchar(255) not null,
        done boolean not null,
        dueDate date,
        priority varchar(255) not null,
        version bigint not null,
        user_id bigint not null,
        primary key (id)
    )

    alter table Book 
       add constraint UK_919tmsbnljwad41x7od7ks7py unique (isbn13)

    alter table User 
       add constraint UK_jreodf78a7pl5qidfh43axdfb unique (username)

    alter table UserTodo 
       add constraint FKasqvcnh08o8er5f7mt41tja99 
       foreign key (user_id) 
       references User
