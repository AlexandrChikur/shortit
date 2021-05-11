package ru.mirea.shortit.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.shortit.objects.RandomStringGenerator;
import ru.mirea.shortit.repos.UrlsRepository;
import ru.mirea.shortit.tables.Urls;


@Service
@RequiredArgsConstructor
public class UrlsService {

    private final UrlsRepository urlsRepo;

    private String getGeneratedUrlString(){
        RandomStringGenerator gen = new RandomStringGenerator(5);
        return gen.nextString();
    }
    private String getGeneratedUniqueUrlString(){
        String generated = this.getGeneratedUrlString();

        if (urlsRepo.existsUrlsByNewurl(generated)){
            return this.getGeneratedUniqueUrlString();
        }

        return generated;
    }

    public Urls createAndGetUrlInstanceByOldUrl(String old_url){
        Urls url_ = new Urls();
        if (urlsRepo.existsUrlsByOldurl(old_url)) {
            return urlsRepo.findUrlsByOldurl(old_url);
        }
        url_.setOldurl(old_url);
        url_.setNewurl(this.getGeneratedUniqueUrlString());
        urlsRepo.save(url_);
        return url_;
    }

    public Urls getUrlInstanceByNewUrl(String new_url) {
        return urlsRepo.findUrlsByNewurl(new_url);
    }

    public Urls getUrlInstanceByOldUrl(String old_url) {
        return urlsRepo.findUrlsByOldurl(old_url);
    }

    public Urls getWhereHaveToBeRedirected(String endpoint) {
        return urlsRepo.findUrlsByNewurl(endpoint);
    }
}
