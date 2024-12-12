
-- User Name: admin@admin.com
-- PWD: admin

INSERT INTO dqs.dq_app_users(id, email, "name", "password",expiredate,role_name)
VALUES(nextval('dqs.dq_app_users_id_seq'::regclass),
'admin@admin.com', 'admin',
'$2a$10$nX5ixHBDmhUgJrFr//ipOO53lwB5u/fbtOo84LgztQj/113eBT9QO','2024-12-30',
'admin');


INSERT INTO dqs.dq_app_users(id, email, "name", "password",expiredate,role_name)
VALUES(nextval('dqs.dq_app_users_id_seq'::regclass),
'user@user.com', 'user',
'$2a$10$nX5ixHBDmhUgJrFr//ipOO53lwB5u/fbtOo84LgztQj/113eBT9QO','2024-12-30',
'user');


INSERT INTO dqs.dq_app_roles(id, "name")
VALUES(nextval('dqs.dq_app_roles_id_seq'::regclass), 'admin');

INSERT INTO dqs.dq_app_roles(id, "name")
VALUES(nextval('dqs.dq_app_roles_id_seq'::regclass), 'user');

INSERT INTO dqs.dq_app_users_roles (user_id, role_id)
VALUES((select id from dqs.dq_app_users where email='admin@admin.com'),
 (select id from dqs.dq_app_roles where name ='admin' ));

 INSERT INTO dqs.dq_app_users_roles (user_id, role_id)
 VALUES((select id from dqs.dq_app_users where email='user@user.com'),
  (select id from dqs.dq_app_roles where name ='user' ));




--INSERT INTO dqs.dq_db_connction_check_det (id,connection_name, hostname, port, username, password, database, db_name, dbsource) VALUES
--(nextval('dqs.dq_db_connction_check_det_id_seq'::regclass),'DefaultConnection', 'localhost', 5432, 'user', 'password', 'default_db', 'default_db_name', 'default_source');
