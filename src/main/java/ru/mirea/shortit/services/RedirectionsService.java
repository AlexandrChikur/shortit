package ru.mirea.shortit.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.shortit.repos.RedirectionsRepository;
import ru.mirea.shortit.tables.Redirections;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RedirectionsService {

    private final RedirectionsRepository redirectionsRepo;

    public void saveRedirection(Redirections redirection) {
        redirectionsRepo.save(redirection);
    }

    public List<Redirections> getAllRedirectionsByUrlId(Long id) {
        return redirectionsRepo.findAllByUrlId(id);
    }
}