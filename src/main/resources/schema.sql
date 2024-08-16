CREATE SCHEMA if not exists limits_schema;

drop table if exists limits;

CREATE TABLE limits(
	id	            SERIAL PRIMARY KEY,
	userid		    INTEGER UNIQUE,
	value           DECIMAL
);
