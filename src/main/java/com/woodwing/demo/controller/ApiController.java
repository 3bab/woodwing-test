package com.woodwing.demo.controller;

import com.woodwing.demo.configuration.ExceptionConfiguration;
import com.woodwing.demo.domain.RequestBodyInput;
import com.woodwing.demo.domain.RequestResponse;
import com.woodwing.demo.domain.UnitType;
import com.woodwing.demo.service.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.function.DoublePredicate;

@RestController
@RequestMapping("/sum")
public class ApiController {

    private final Logger logger = LoggerFactory.getLogger("ApiController:" + this.getClass());

    @Autowired
    private ApiService service;

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RequestResponse> apiSum(@RequestBody RequestBodyInput input) {
        validateInput(input);
        logger.info("Returning the sum");
        return new ResponseEntity<>(new RequestResponse(service.sum(input)), HttpStatus.OK);
    }

    /**
     * Validates the input payload. In case of non valid input, throws an exception.
     */
    private void validateInput(@NotNull RequestBodyInput input) {
        // check for missing data
        if (input.getSummandOneType() == null ||
                input.getSummandTwoType() == null||
                input.getSummandOneValue() == null ||
                input.getSummandTwoValue() == null ||
                input.getSumType() == null) {
            logger.error("Request payload not complete.");
            throw new ExceptionConfiguration.PayloadNotComplete();
        }

        // check for negative values
        DoublePredicate predicate  = v -> v < 0;
        if (predicate.test(input.getSummandOneValue()) ||
                predicate.test(input.getSummandTwoValue())) {
            logger.error("Summand cannot be negative.");
            throw new ExceptionConfiguration.SummandIsNegative();
        }

        // validate summand types
        if (!UnitType.contains(input.getSummandOneType()) ||
                !UnitType.contains(input.getSummandTwoType())) {
            logger.error("Correct summand type not found in request.");
            throw new ExceptionConfiguration.SummandTypeNotFound();
        }

        // validate sum type
        if (!UnitType.contains(input.getSumType())) {
            logger.error("Correct sum type not found in request.");
            throw new ExceptionConfiguration.SumTypeNotFound();
        }
    }
}
