package uk.hardwareswap.shortenerapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "url_redirect_data",
       indexes = { @Index(name = "idx_original_url", columnList = "original_url") })
public class UrlRedirectData {

    @Id
    @GenericGenerator(name = "url_id", strategy = "uk.hardwareswap.shortenerapi.repository.util.UrlIdGeneratorStrategy")
    @GeneratedValue(generator = "url_id")
    @Column(name = "url_id", columnDefinition = "varchar(10)")
    String urlId;

    @Column(name = "original_url", columnDefinition = "text")
    public String originalUrl;
}
