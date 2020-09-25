package uk.hardwareswap.shortenerapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.hardwareswap.shortenerapi.exception.UrlEntryNotFoundException;
import uk.hardwareswap.shortenerapi.service.UrlRedirectService;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Api(hidden = true)
public class UrlRedirectController {

    private final UrlRedirectService urlRedirectService;

    @GetMapping("/{urlId}")
    @ApiOperation(value = "Redirect to the URL referenced by the given urlId.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Void.class),
        @ApiResponse(code = 404, message = "Not Found - a link with the given URL ID does not exist.", response = Void.class)
    })
    public ResponseEntity<Void> redirect(@PathVariable String urlId) {

        log.info("Redirecting for URL with ID {}", urlId);

        try {
            urlRedirectService.getUrlById(urlId);
        } catch (UrlEntryNotFoundException ignored) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }
}
