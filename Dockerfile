FROM library/postgres:9.2.19
ADD scripts/create_tables.sql /docker-entrypoint-initdb.d/
