package uk.hardwareswap.shortenerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.hardwareswap.shortenerapi.model.UrlRedirectData;

@Repository
public interface UrlRepository extends JpaRepository<UrlRedirectData, String> {
}
