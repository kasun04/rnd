package org.kasun;

import javax.script.*;

/**
 * Created by kasun on 11/16/16.
 */
public class JSTester {
    public static void main(String[] args) {

        Customer customer = new Customer("abc", "123", 1);

        try {

            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("javascript");



            Bindings bindings = engine.createBindings();
            bindings.put("foo", customer);

            engine.eval("var id = foo.getId(); " +
                        "print (id);", bindings);


//            engine.eval("var name = foo.getId();");
//            engine.eval("print (name);");




        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}
