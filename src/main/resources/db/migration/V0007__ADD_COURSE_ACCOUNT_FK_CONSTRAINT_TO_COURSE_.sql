alter table course add constraint course_account_fk foreign key (account_id) references account (id);
alter table course alter column account_id set not null;