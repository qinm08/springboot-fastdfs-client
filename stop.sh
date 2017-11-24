pid=`ps -ef|grep "springboot-fastdfs" | grep -v "grep"|awk '{print $2}'`
kill -9 $pid
echo "[INFO]: Application[springboot-fastdfs](pid=$pid) has stopped !!"
