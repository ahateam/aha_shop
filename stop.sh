PID=$(cat /var/run/aha_shop-0.0.1-fat.pid)
echo $PID
kill -s 9 $PID