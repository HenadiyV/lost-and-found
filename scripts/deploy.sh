#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/id_rsa \
    target/Myproject.jar \
    root@185.238.3.222:/root/

echo 'Restart ...'

ssh -i ~/.ssh/id_rsa root@185.238.3.222 << EOF
 jps -l | grep Myproject.jar | awk '{print \$1}' | xargs kill -9
nohup java -jar Myproject.jar > log.txt &
EOF

echo 'Bye'