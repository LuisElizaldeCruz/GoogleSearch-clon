package com.cursojava.curso.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Set;

import com.cursojava.curso.entities.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.hibernate.internal.util.StringHelper.isBlank;

@Service
public class SpiderService {

    @Autowired
    private SearchService searchService;

    public void indexWebPage() {
        String url = "https://www.bbc.com/";
        String content = getWebContent(url);
        String domain = getDomain(url);
        if (isBlank(content)) {
            return;
        }
       indexAndSaveWebPage(url, content);
        saveLinks(getDomain(url),content);
    }

    private String getDomain(String url) {
        String[] aux = url.split("/");
        return aux[0] + "//" + aux[1];
    }

    private void saveLinks(String domain, String content){
        List<String> links = getLinks(domain,content);
        links.stream().filter(link -> !searchService.exist(link) )
                .map(link ->new WebPage(link))
                .forEach(webPage -> searchService.save(webPage));
    }



    public List<String> getLinks(String domain,String content) {
        List <String> links = new ArrayList<>();
        String[] splitHref = content.split("href=\"");
        List<String> listHref = Arrays.asList(splitHref);
        listHref.remove(0);

        listHref.forEach(strHref ->{
            String[] aux = strHref.split("\"");
            links.add(aux[0]);
        });

        return cleanLinks(domain,inks);
    }

    private List<String> cleanLinks(String domain, List<String> links){
        String[] excludedExtensions = new String[]{"css","js","json","jpg","png","woff2"};

        List<String> resultLinks = links.stream()
                .filter(link -> Arrays.stream(excludedExtensions)
                .noneMatch(extension -> link.endsWith(extension)))
                .map(link -> link.startsWith("/") ? domain+link : link)
                .collect(Collectors.toList());

        List<String> uniqueLinks = new ArrayList<String>();
        uniqueLinks.addAll(new HashSet<String>(resultLinks));
        return uniqueLinks;
    }

    private void indexAndSaveWebPage(String content, String url) {
        String title = getTitle(content);
        String description = getDescription(content);

        //save content in database
        WebPage webPage = new WebPage();
        webPage.setDescription(description);
        webPage.setTitle(title);
        webPage.setUrl(url);
        searchService.save(webPage);
    }

    public String getTitle(String content) {
        String[] aux = content.split("<title>");
        String[] aux2 = aux[1].split("</title>");
        return aux2[0];
    }

    public String getDescription(String content) {
        String[] aux = content.split("<meta name=\"description\" content=\"");
        String[] aux2 = aux[1].split("\">");
        return aux2[0];
    }

    private String getWebContent(String link) {
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

