package uk.hardwareswap.shortenerapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlRedirectData {

    @Id
    @GenericGenerator(name = "url_id", strategy = "uk.hardwareswap.shortenerapi.repository.util.UrlIdGeneratorStrategy")
    @GeneratedValue(generator = "url_id")
    @Column(name = "url_id")
    String urlId;

    @Column(name = "original_url", columnDefinition = "varchar(2000)")
    public String originalUrl;
}
