package com.bompracavalo.api.util;

import java.io.BufferedReader;
import java.io.IOException;

public class Util {
    public static String convertJsonToString(BufferedReader bufferedReader) throws IOException {
        String response, jsonToString = "";
        while ((response = bufferedReader.readLine()) != null) {
            jsonToString += response;
        }
        return jsonToString;
    }
}
