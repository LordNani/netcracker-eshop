--select username from dba_users;
--select tablespace_name from dba_tablespaces;
--select * from v$datafile;
--DROP TABLESPACE jss_tablespace including contents and datafiles cascade constraints;/*if need to drop*/


--CREATE ROLE with admin grants;
create role ADMINDBA;
-- grant DBA,Connect and Resource permission to this role (not sure this is necessary if you give admin option)
GRANT CONNECT,RESOURCE, DBA to ADMINDBA;
--Give admin option to user. A newly created user cannot connect to the database until
--granted the CREATE SESSION system privilege.
GRANT CREATE SESSION TO ADMINDBA WITH ADMIN OPTION;
GRANT ALL PRIVILEGES TO ADMINDBA IDENTIFIED BY ADMIN; -- grant all privileges to <roleName>
GRANT unlimited tablespace to ADMINDBA;-- give unlimited tablespace grant

--CREATE ADMIN USER FOR MANAGEMENT APPLICATION USER
CREATE USER ADMIN IDENTIFIED BY '123456'; -- create user with password
--GRANTS
GRANT ADMINDBA to ADMIN;


--CREATE USER FOR APPLICATION (with restricted grants)
--CREATE TABLESPACE
create tablespace js_tablespace
datafile 'js_tablespace.dat' size 10M autoextend on;

create temporary tablespace js_tablespace_temp
tempfile 'js_tablespace_temp.dat' size 5M autoextend on;

--CREATE ROLE with basics grants;
create role SIMPLEDBA;
grant unlimited tablespace to SIMPLEDBA;
grant create session to SIMPLEDBA;
grant create table to SIMPLEDBA;
grant create any sequence to SIMPLEDBA;

--CREATE USER
create user JS identified by jsadmin
  default tablespace js_tablespace
  temporary tablespace js_tablespace_temp;

--GRANTS
GRANT SIMPLEDBA to JS;
GRANT unlimited tablespace to JS;