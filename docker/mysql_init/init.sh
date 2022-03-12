#!/bin/bash

# DDLでテーブルを作成する
mysql -u root -pfoobar GISApp < "/docker-entrypoint-initdb.d/sql/GISApp-schema.sql"
mysql -u root -pfoobar GISApp < "/docker-entrypoint-initdb.d/sql/COMMON-schema.sql"
mysql -u root -pfoobar GISApp < "/docker-entrypoint-initdb.d/sql/user_grant.sql"
