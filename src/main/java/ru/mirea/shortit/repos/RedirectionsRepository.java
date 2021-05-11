package ru.mirea.shortit.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.shortit.tables.Redirections;

import java.util.List;

public interface RedirectionsRepository extends JpaRepository<Redirections, Integer> {
    List<Redirections> findAllByUrlId(Long url_id);
}
