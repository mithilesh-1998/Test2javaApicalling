package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.System.in;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {


        String myUrl = "https://api.nationalize.io/?name=nathaniel";

        HttpURLConnection connection = null;
        int responsecode = 0;
        URL url = null;

        try {

            url = new URL(myUrl);
        } catch (MalformedURLException e){
            System.out.println("Somethings is wrong with the URL");

        }

        //connection:
        try {
            connection = (HttpURLConnection) url.openConnection();
            responsecode = connection.getResponseCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(responsecode == 200){

            BufferedReader an = new BufferedReader(new InputStreamReader(connection.getInputStream()));


            StringBuilder apiData = new StringBuilder();
            String readLine = null;
            while((readLine = an.readLine())!=null) {
                apiData.append(readLine);
            }

            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(apiData.toString());
            JSONObject jsonApIResponse = new JSONObject(apiData.toString());

            System.out.println(jsonApIResponse.get("count"));
            System.out.println(jsonApIResponse.get("name"));
            System.out.println(jsonApIResponse.get("country"));



        }else{
            System.out.println("api call not successfull");
        }
    }
}