package com.codebusters.medelivery;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Parser {

    private static final String API_KEY = "AIzaSyCttWOywG3gE8VfM17aGQyDl-306fMMPwE";

    public static ArrayList<Hospital> parse(String streetAddress, String city, String state, String zipCode) throws IOException, InterruptedException {
        String url = createURL1(streetAddress, city, state, zipCode);
        double[] coord = getCoord(url);
        double lat = coord[0];
        double lng = coord[1];
        String url2 = createURL2(lat, lng);
        ArrayList<Hospital> hospitals = getHospitals(url2);
        return hospitals;
    }

    public static ArrayList<Hospital> getHospitals (String url2) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        String response = gethttp(url2);

        Map<String, Object> map = mapper.readValue(response, new TypeReference<Map<String,Object>>(){});
        int max = ((ArrayList) map.get("results")).size();
        if (max > 5) {
            max = 5;
        }
        ArrayList<Hospital> hospitals = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            Map<String, Object> map1 = (LinkedHashMap) ((ArrayList) map.get("results")).get(i);
            String name = (String) map1.get("name");
            String address = (String) map1.get("vicinity");
            Hospital h1 = new Hospital(name, address);
            hospitals.add(h1);
        }
        return hospitals;
    }

    public static String createURL2 (double lat, double lng) {
        String start = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=";
        String end = "&radius=100000&rankby=prominence&keyword=hospital&types=hospital&key=" + API_KEY;
        String middle = lat + "," + lng;
        String result = start + middle + end;
        return result;
    }

    public static String createURL1 (String streetAddress, String city, String state, String zipCode) {
        String start = "https://maps.googleapis.com/maps/api/geocode/json?address=";
        String end = "&key=" + API_KEY;
        streetAddress = streetAddress.trim().replace(" ", "+");
        city = city.trim().replace(" ", "+");
        state = state.trim().replace(" ", "+");
        zipCode = zipCode.trim().replace(" ", "+");
        String middle = streetAddress + ",+" + city + ",+" + state + ",+" + zipCode;
        String result = start + middle + end;
        return result;
    }

    public static double[] getCoord (String url) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();

        String response = gethttp(url);

        Map<String, Object> map = mapper.readValue(response, new TypeReference<Map<String,Object>>(){});
        Map<String, Object> map1 = (LinkedHashMap) ((ArrayList) map.get("results")).get(0);
        Map<String, Object> map2 = (LinkedHashMap) map1.get("geometry");
        Map<String, Object> map3 = (LinkedHashMap) map2.get("location");
        double lat = (Double) map3.get("lat");
        double lng = (Double) map3.get("lng");
        double[] coord = {lat, lng};
        return coord;
    }

    public static String gethttp (String str1) throws IOException {
        URL url = new URL(str1);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");

        //adding header
        httpURLConnection.setRequestProperty("Auth", "Token");
        httpURLConnection.setRequestProperty("Data1", "Value1");

        String line = "";
        InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder response = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            response.append(line);
        }
        bufferedReader.close();
        return response.toString();
    }
}


