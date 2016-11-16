package com.kasun;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;

public class SampleTester {

    public static void main(String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        ObjectNode root = (ObjectNode) mapper.readTree(new File("src/main/res/Order.json"));

        // === DM ===
        String newString = "{\"delivery_date\": \"12-25-2016\"}";
        JsonNode newNode = mapper.readTree(newString);
        ((ObjectNode) root.get("order")).remove("source_id");
        root.set("delivery_date", newNode);
        System.out.println(" Result : " + root.toString());
    }
}
