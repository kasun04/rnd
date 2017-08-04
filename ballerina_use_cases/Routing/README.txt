
Creating Service Archive
========================
/Users/kasun/development/deployment/ballerina/ballerina-tools-0.88/bin/ballerina build service Routing/cbr -o Routing/RoutingUC.bsz


Service
=======
./ballerina run service /Users/kasun/development/source/git/kasun04/rnd/ballerina_use_cases/Routing/RoutingUC.bsz


Client
======
curl -v http://localhost:9090/cbr -d '{"name" : "nasdaq"}'