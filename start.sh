nohup java -jar aha_shop-0.0.1-fat.jar &
echo $! > /var/run/aha_shop-0.0.1-fat.pid
echo "server started"