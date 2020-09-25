package uk.hardwareswap.shortenerapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.hardwareswap.shortenerapi.exception.UrlEntryNotFoundException;
import uk.hardwareswap.shortenerapi.repository.UrlRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlRedirectService {

    private final UrlRepository urlRepository;

    public String getUrlById(String urlId) {

        return (urlRepository.findById(urlId).orElseThrow(UrlEntryNotFoundException::new))
                .getOriginalUrl();
    }
}
