package ServiceOrchestration;

import ballerina.lang.messages;
import ballerina.net.http;
import ballerina.lang.system;
import ballerina.lang.jsons;

@http:BasePath {value:"/ABCBank"}
service ATMLocator {

    http:ClientConnector branchLocator = create http:ClientConnector("http://localhost:9090/branchlocator");
    http:ClientConnector bankInfoService = create http:ClientConnector("http://localhost:9090/bankinfo");

    @http:POST{}
    @http:Path {value:"/locator"}
    resource locator (message m) {


        message backendServiceReq = {};
        json jsonLocatorReq = messages:getJsonPayload(m);
        string zipCode = jsons:getString(jsonLocatorReq, "$.ATMLocator.ZipCode");

        system:println("Zip Code " + zipCode);
        json branchLocatorReq = {"BranchLocator": {"ZipCode":""}};
        jsons:set(branchLocatorReq, "$.BranchLocator.ZipCode", zipCode);
        messages:setJsonPayload(backendServiceReq, branchLocatorReq);
        message response = http:ClientConnector.post(branchLocator, "", backendServiceReq);
        json branchLocatorRes = messages:getJsonPayload(response);
        string branchCode = jsons:getString(branchLocatorRes, "$.ABCBank.BranchCode");
        system:println("Branch Code " + branchCode);
        json bankInfoReq = {"BranchInfo": {"BranchCode":""}};
        jsons:set(bankInfoReq, "$.BranchInfo.BranchCode", branchCode);
        messages:setJsonPayload(backendServiceReq, bankInfoReq);
        response = http:ClientConnector.post(bankInfoService, "", backendServiceReq);
        reply response;

    }
}