package uk.hardwareswap.shortenerapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.hardwareswap.shortenerapi.exception.UrlEntryFetchException;
import uk.hardwareswap.shortenerapi.exception.UrlEntrySaveException;
import uk.hardwareswap.shortenerapi.model.UrlRedirectData;
import uk.hardwareswap.shortenerapi.repository.UrlRepository;

import static java.util.Objects.nonNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlManagementService {

    private final UrlRepository urlRepository;

    public String addUrl(String url) {

        // Check if the URL already exists.
        String urlId = getUrlIdByOriginalUrl(url);

        if (nonNull(urlId)) {
            return urlId;
        }

        UrlRedirectData data = UrlRedirectData.builder()
            .originalUrl(url)
            .build();

        UrlRedirectData saved = urlRepository.save(data);

        if (nonNull(saved.getUrlId()) && !saved.getUrlId().isBlank()) {
            return saved.getUrlId();
        } else {
            throw new UrlEntrySaveException("An error occurred while saving the URL entry.");
        }
    }

    public String getUrlIdByOriginalUrl(String url) {

        UrlRedirectData data;

        try {
            data = urlRepository.findByOriginalUrl(url);
        } catch (Exception e) {
            throw new UrlEntryFetchException("An error occurred while checking if a URL entry already exists.", e);
        }

        if (nonNull(data)) {
            return data.getUrlId();
        }

        return null;
    }
}
