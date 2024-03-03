package com.cursojava.curso.services;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class SpiderService {


    private static String getWebContent(String link) {
        try {
            URL url = new URL(link);
            //openconnection devuelve un objeto diferente por eso hay que parsearlo
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            String encoding = conn.getContentEncoding();
            //descargar la pagina web
            InputStream input = conn.getInputStream();

            Stream<String> lines = new BufferedReader(new InputStreamReader(input)).lines();
            String result = lines.collect(Collectors.joining());
            return result;

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return " ";
    }
}

