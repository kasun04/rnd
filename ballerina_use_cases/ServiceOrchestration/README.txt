
Creating Service Archive
========================
/Users/kasun/development/deployment/ballerina/ballerina-tools-0.87/bin/ballerina build service Routing/cbr -o ServiceOrchestration/ATMLocatorUC.bsz


Service
=======
./ballerina run service /Users/kasun/development/source/git/kasun04/rnd/ballerina_use_cases/ServiceOrchestration/ATMLocatorUC.bsz


Client
======
curl -X POST -d '{"ATMLocator": {"ZipCode": "95999"}}' -v "http://localhost:9090/ABCBank/locator"