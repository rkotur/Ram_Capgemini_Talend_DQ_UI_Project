
-- User Name: admin@admin.com
-- PWD: admin

INSERT INTO dqs.dq_app_users(id, email, "name", "password")
VALUES(nextval('dqs.dq_app_users_id_seq'::regclass), 'admin@admin.com', 'admin', '$2a$10$nX5ixHBDmhUgJrFr//ipOO53lwB5u/fbtOo84LgztQj/113eBT9QO');

--INSERT INTO dqs.dq_db_connction_check_det (id,connection_name, hostname, port, username, password, database, db_name, dbsource) VALUES
--(nextval('dqs.dq_db_connction_check_det_id_seq'::regclass),'DefaultConnection', 'localhost', 5432, 'user', 'password', 'default_db', 'default_db_name', 'default_source');
