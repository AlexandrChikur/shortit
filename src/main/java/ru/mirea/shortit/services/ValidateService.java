package ru.mirea.shortit.services;

import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


@Service
@RequiredArgsConstructor
public class ValidateService {

    UrlValidator urlValidator = new UrlValidator();

    @Autowired
    private Environment env;


    public Boolean isValidUrl(String url) {
        return urlValidator.isValid(url);
    }

    public Boolean isAccessible(String url) throws IOException {
        int responseCode = 0;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            responseCode = connection.getResponseCode();
        } catch (Exception e) {

        }
        if (responseCode == 200) {
            return true;
        }

        return false;
    }

    public Boolean isServiceOwnUrl(HttpServletRequest request, String url) throws URISyntaxException {
        String requestedUrlDomainName = this.getDomainName(url);
        String backendDigDomainName = this.getDomainName(env.getProperty("frontend.base.dig.url"));
        String backendLetDomainName = this.getDomainName(env.getProperty("frontend.base.let.url"));

        if(requestedUrlDomainName.equals(backendDigDomainName) || requestedUrlDomainName.equals(backendLetDomainName)){
            return true;
        }
        return false;
    }

    private String getDomainName(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

}
