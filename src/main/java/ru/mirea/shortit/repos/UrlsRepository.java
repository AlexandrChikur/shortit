package ru.mirea.shortit.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.shortit.tables.Urls;

public interface UrlsRepository extends JpaRepository<Urls, Integer> {
    Urls findUrlsByNewurl(String newurl);
    Urls findUrlsByOldurl(String oldurl);

    boolean existsUrlsByNewurl(String newurl);
    boolean existsUrlsByOldurl(String oldurl);
}
