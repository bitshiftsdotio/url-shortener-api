package uk.hardwareswap.shortenerapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.hardwareswap.shortenerapi.exception.UrlEntryNotFoundException;
import uk.hardwareswap.shortenerapi.service.UrlRedirectService;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(hidden = true)
public class UrlRedirectController {

    private final UrlRedirectService urlRedirectService;

    @GetMapping("/u/{urlId}")
    @ApiOperation(value = "Redirect to the URL referenced by the given urlId.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "Not Found - a link with the given URL ID does not exist.")
    })
    public ResponseEntity<Void> redirect(@PathVariable String urlId) {

        String url;

        try {
            url = urlRedirectService.getUrlById(urlId);
        } catch (UrlEntryNotFoundException ignored) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, url);
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
