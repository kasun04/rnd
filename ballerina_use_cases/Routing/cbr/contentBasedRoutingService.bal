package Routing.cbr;

import ballerina.net.http;
import ballerina.lang.jsons;
import ballerina.lang.messages;

@http:BasePath {value:"/cbr"}
service contentBasedRouting {
    http:ClientConnector nasdaqEP = create http:ClientConnector("http://localhost:9090/nasdaqStocks");
    http:ClientConnector nyseEP = create http:ClientConnector("http://localhost:9090/nyseStocks");

    @http:POST{}
    resource cbrResource (message m) {

        string nyseString = "nyse";
        json jsonMsg = messages:getJsonPayload(m);
        string nameString = jsons:getString(jsonMsg, "$.name");
        message response = {};
        if (nameString == nyseString) {
            response = http:ClientConnector.post(nyseEP, "/", m);

        }
        else {
            response = http:ClientConnector.post(nasdaqEP, "/", m);

        }
        reply response;

    }

}