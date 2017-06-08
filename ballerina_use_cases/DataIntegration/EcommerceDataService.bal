import ballerina.lang.messages;
import ballerina.net.http;
import ballerina.data.sql;
import ballerina.lang.datatables;

@http:BasePath {value:"/employees"}
service EcommerceDS {
    map dbProperties1 = {"jdbcUrl":"jdbc:mysql://localhost:3306/employeedb","username":"root", "password":"root"};
    sql:ClientConnector empDB = create sql:ClientConnector (dbProperties1);

    @http:GET {}
    @http:Path {value:"/{employeeId}"}
    resource empInfo (message m, @http:PathParam {value:"employeeId"}string employeeId) {
        sql:Parameter para = {sqlType:"integer", value:employeeId, direction:0};
        sql:Parameter[] params = [para];
        datatable dt = sql:ClientConnector.select (empDB, "Select * from Employees where id = ?", params);
        json payload = datatables:toJson (dt);
        message response = {};
        messages:setJsonPayload (response, payload);
        reply response;
    }



    @http:POST {}
    @http:Path {value:"/"}
    resource insertEmpInfo (message m) {
        int returnVal = 0;
        json input = messages:getJsonPayload(m);
        //insert to employee table
        sql:Parameter paraID = {sqlType:"integer", value:(int)input.employee.id, direction:0};
        sql:Parameter paraName = {sqlType:"varchar", value:(string)input.employee.name, direction:0};
        sql:Parameter[] paramsEmp = [paraID, paraName];
        sql:ClientConnector.update(empDB, "Insert into Employees(id,name) values (?,?)", paramsEmp);
        //insert to salary table
        sql:Parameter paraEmpID = {sqlType:"integer", value:(int)input.employee.id, direction:0};
        sql:Parameter paraSal = {sqlType:"float", value:(int)input.employee.salary, direction:0};
        sql:Parameter[] paramsSal = [paraEmpID,paraSal];
        sql:ClientConnector.update(empDB, "Insert into SalaryData (id, salary) values (?, ?)",paramsSal);

        message response = {};
        messages:setStringPayload(response, "Data Insertion Status:" + returnVal);
        reply response;
    }





}