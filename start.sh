#!/usr/bin/env bash
echo "repackage jar file ..."
mvn clean package
echo "starting application ..."
nohup java -jar target/springboot-fastdfs-client-1.0.0.jar >/dev/null 2>&1 &
echo "start successfully."