package ru.mirea.shortit.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import ru.mirea.shortit.services.*;
import ru.mirea.shortit.tables.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

@Api(tags = "Urls", description = "These endpoints are used for URL manipulation")
@JsonComponent
@RestController
@RequestMapping(path="/r")
public class UrlsController {


    private final UrlsService urlsService;
    private final RedirectionsService redirectionsService;
    private final RequestService requestService;
    private final ValidateService validateService;


    @Autowired
    private Environment env;

    @Autowired
    public UrlsController(UrlsService urlsService,
                          RequestService requestService,
                          RedirectionsService redirectionsService,
                          ValidateService validateService) {
        this.urlsService = urlsService;
        this.requestService = requestService;
        this.redirectionsService = redirectionsService;
        this.validateService = validateService;
    }

    @GetMapping
    @ApiOperation(value = "This endpoint is used to shorten specified url")
    public ResponseEntity shortenizeUrl(HttpServletRequest request, @RequestParam String url) throws IOException, URISyntaxException {

        if (!validateService.isValidUrl(url)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("URL is not valid");
        }

        if(validateService.isServiceOwnUrl(request, url)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Shortened URL can't be shorten");
        }

        if (!validateService.isAccessible(url)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("URL is not accessible");
        }


        Urls shortened = urlsService.createAndGetUrlInstanceByOldUrl(url);
        return ResponseEntity.ok(shortened);
    }

    @GetMapping("/{endpoint}")
    @ApiOperation(value = "This endpoint is used for redirect to specified endpoint. Not working in Swagger for obvious reasons")
    public ModelAndView doRedirectWithEndpoint(HttpServletRequest request, @PathVariable("endpoint") String endpoint) {
        String clientIp = requestService.getClientIp(request);
        String clientDevice = requestService.getClientDevice(request);

        Urls redirection_url = urlsService.getWhereHaveToBeRedirected(endpoint);
        if (redirection_url == null) {
            return new ModelAndView("redirect:" + env.getProperty("frontend.base.dig.url") + "404");
        }

        Urls url = urlsService.getUrlInstanceByNewUrl(endpoint);

        Redirections redirection = new Redirections();
        redirection.setUrlId(url.getId());
        redirection.setDevice(clientDevice);
        redirection.setIp(clientIp);


        redirectionsService.saveRedirection(redirection);
        return new ModelAndView("redirect:" + redirection_url.getOldurl());
    }


    @GetMapping("/info")
    @ApiOperation(value = "This endpoint is used for getting info about shorten URL")
    public ResponseEntity getUrlInfo(@RequestParam String url) {

        Urls urlInstance = null;
        String [] splitted_url = url.split("/");
        String endpont = splitted_url[splitted_url.length-1];

        if(urlsService.getUrlInstanceByNewUrl(endpont) != null) {
            urlInstance = urlsService.getUrlInstanceByNewUrl(endpont);
        } else if (urlsService.getUrlInstanceByOldUrl(endpont) != null) {
            urlInstance = urlsService.getUrlInstanceByOldUrl(endpont);
        }

        if(urlInstance == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("URL with \"url\" parameter that you specified does not exit");
        }

        List<Redirections> data = redirectionsService.getAllRedirectionsByUrlId(urlInstance.getId());
        HashMap<String, Object> alter_data = new HashMap<>();
        alter_data.put("count", data.toArray().length);
        alter_data.put("data", data);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(alter_data);
    }
}
