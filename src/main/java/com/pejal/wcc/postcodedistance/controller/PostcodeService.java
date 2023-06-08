/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pejal.wcc.postcodedistance.controller;

import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author pejalhebat
 */

@Slf4j
@RestController
@RequestMapping("/postcode")
public class PostcodeService {
    
    @Autowired
    PostcodeBusinessHandler postcodeHandler;
    
    @GetMapping
    public String index() {
        return "Postcode distance service";
    }
    
    @GetMapping("/count")
    public Long getCount() {
        return postcodeHandler.getLocationCount();
    }
    
    @GetMapping("/location")
    public List<PostcodeResponse> getPostcodeLocation(@RequestParam String postcode) {
        log.info("postcode param: " + postcode);
        if ("".equals(postcode.trim())) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        List<PostcodeResponse> response = postcodeHandler.getPostcode(postcode);
        if (response == null || response.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        } else {
            return response;
        }
        
    }
    
    @GetMapping("/location/default")
    public PostcodeResponse getDefaultPostcodeLocation() {
        return postcodeHandler.getDefaultPostcode();
    }
    
    @GetMapping("/distance")
    public DistanceResponse getDistanceBetween(
            @RequestParam String postcodeA, @RequestParam String postcodeB) {
        return postcodeHandler.getDistanceBetween(postcodeA, postcodeB);
    }
    
    @PutMapping
    public PostcodeResponse updatePostcodeLocation(@RequestBody PostcodeResponse request) {
        return postcodeHandler.updatePostcodeLocation(request);
    }
    
    private PostcodeResponse getDefaultPostcodeResponse() {
        return PostcodeResponse
                    .builder()
                    .postcode("NO POSTCODE")
                    .latitude(Double.NaN)
                    .longitude(Double.NaN)
                    .build();
    }
    
    private List<PostcodeResponse> getDefaultPostcodeListResponse() {
        return Arrays.asList(getDefaultPostcodeResponse());
    }
    
    
}
