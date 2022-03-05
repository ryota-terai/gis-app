#!/bin/bash

# DDLでテーブルを作成する
mysql -u ghp2021 -pfoobar GHP2021 < "/docker-entrypoint-initdb.d/sql/GHP2021-schema.sql"
