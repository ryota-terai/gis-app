docker run --rm --name httpd -d httpd:2.4
docker cp httpd:/usr/local/apache2/conf/ conf
docker rm -f httpd
