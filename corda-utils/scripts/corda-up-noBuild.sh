!#/bin/sh

#killall -s SIGKILL java

echo "UP NODE PARTY A"
cd ~/git/eletrikas/Corda/build/nodes/PartyA
java -Xmx1024m -jar corda.jar > /dev/null & 

echo "UP NODE PARTY B"
cd ~/git/eletrikas/Corda/build/nodes/PartyB
java -Xmx1024m -jar corda.jar > /dev/null & 

sleep 60
echo "UP SPRING API"
cd ~/git/eletrikas/Corda/clients/build/libs
java -Xmx1024m -jar clients.jar > /dev/null & 

