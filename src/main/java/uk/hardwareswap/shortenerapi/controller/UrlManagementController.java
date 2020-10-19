package uk.hardwareswap.shortenerapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.hardwareswap.shortenerapi.model.request.CreateUrlRedirectRequestBody;
import uk.hardwareswap.shortenerapi.model.response.CreateUrlRedirectResponseBody;
import uk.hardwareswap.shortenerapi.service.UrlManagementService;

@Api
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UrlManagementController {

    private final UrlManagementService urlManagementService;

    // TODO: Once error handling controller is implemented, add error response types to the Swagger definitions
    @PostMapping("/redirects")
    @ApiOperation(value = "Add a new redirect to the database.", response = CreateUrlRedirectResponseBody.class)
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created - the URL is now available for redirection with the returned ID.", response = CreateUrlRedirectResponseBody.class),
        @ApiResponse(code = 303, message = "See Other - this URL already has a redirect available to use. In this case, the ID of the existing URL is returned.", response = CreateUrlRedirectResponseBody.class),
        @ApiResponse(code = 400, message = "Bad Request - the request body was invalid in some way."),
        @ApiResponse(code = 500, message = "Internal Server Error - an error occurred while attempting to write to the database.")
    })
    public ResponseEntity<CreateUrlRedirectResponseBody> addUrl(@RequestBody CreateUrlRedirectRequestBody body) {

        // TODO: Add request body validation

        String urlId = urlManagementService.addUrl(body.getUrl());

        CreateUrlRedirectResponseBody response = new CreateUrlRedirectResponseBody(urlId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
