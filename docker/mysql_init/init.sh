#!/bin/bash

# DDLでテーブルを作成する
mysql -u gisapp -pfoobar GISApp < "/docker-entrypoint-initdb.d/sql/GISApp-schema.sql"
