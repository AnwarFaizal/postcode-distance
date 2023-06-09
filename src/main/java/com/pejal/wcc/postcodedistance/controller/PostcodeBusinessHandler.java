/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pejal.wcc.postcodedistance.controller;

import com.pejal.wcc.postcodedistance.PostcodeLocation;
import com.pejal.wcc.postcodedistance.PostcodeRepository;
import java.util.List;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author pejalhebat
 */
@Slf4j
@Component
public class PostcodeBusinessHandler {
    
    @Autowired
    PostcodeRepository postcodeRepository;
    
    private PostcodeMapper mapper = Mappers.getMapper(PostcodeMapper.class);
    
    public Long getLocationCount() {
        return postcodeRepository.count();
    }
    
    /**
     * Retrieves the postcode location based on the postcode string provided.
     * @param postcode UK postcode
     * @return response payload of the postcode location info.
     */
    public List<PostcodeResponse> getPostcode(String postcode){
        return mapper.map(postcodeRepository.findByPostcode(postcode));
    }

    public PostcodeResponse getDefaultPostcode() {
        PostcodeLocation result = postcodeRepository.findById(5L).get();
        log.info("Postcode: " + result.getPostcode());
        return mapper.entityToResponse(result);
    }
    
    /**
     * Calculates the distance between two postcode locations. Returns in 
     * format containing the two locations, as well as distance calculated.
     * @param locationA First postcode location
     * @param locationB Second postcode location
     * @return response format of two postcode locations and distance in km
     */
    public DistanceResponse getDistanceBetween(PostcodeLocation locationA, PostcodeLocation locationB){
        DistanceCalculator calculator = new DistanceCalculator();
        Double distance = calculator.calculateDistance(locationA.getLatitude(), locationA.getLongitude()
                , locationB.getLatitude(), locationB.getLongitude());
        DistanceResponse distanceResponse = DistanceResponse.builder()
                .a(mapper.entityToResponse(locationA))
                .b(mapper.entityToResponse(locationB))
                .distance(distance)
                .unit("km")
                .build();
        log.info("Distance between " + locationA.getPostcode() + " and "
                + locationB.getPostcode() + " is " + distance);
        return distanceResponse;
    }
    
    /**
     * Retrieves both postcode location info, based on the postcode string provided. 
     * It then calls for the actual calculation and processing to return the distance info.
     * @param postcodeA
     * @param postcodeB
     * @return 
     */
    public DistanceResponse getDistanceBetween(String postcodeA, String postcodeB) {
        PostcodeLocation locationA = getSinglePostcodeLocationFrom(postcodeRepository.findByPostcode(postcodeA));
        PostcodeLocation locationB = getSinglePostcodeLocationFrom(postcodeRepository.findByPostcode(postcodeB));

        log.info("\nPostcodeA: " + postcodeA + ", Location: " + locationA
                + "\nPostcodeB: " + postcodeB + ", Location: " + locationB);

        return formatToDistanceResponse(locationA, locationB);
    }
    
    /**
     * Processes the two geo location to get the distance between them. If one
     * or both of the location is invalid (not in the DB), it will replace it with
     * a default non-location and return a 0.0 distance. 
     * @param locationA
     * @param locationB
     * @return distance in KM, 0.0 if any of the postcodes are invalid.
     */
    public DistanceResponse formatToDistanceResponse(PostcodeLocation locationA, PostcodeLocation locationB) {
        if (locationA != null && locationB != null) {
            return getDistanceBetween(locationA, locationB);
        }
        if (locationA == null) {
            locationA = getDefaultNonLocation();
        }
        if (locationB == null) {
            locationB = getDefaultNonLocation();
        }
        return DistanceResponse.builder()
                .a(mapper.entityToResponse(locationA))
                .b(mapper.entityToResponse(locationB))
                .distance(0.0)
                .unit("km")
                .build();
    }
    
    private PostcodeLocation getSinglePostcodeLocationFrom(List<PostcodeLocation> postcodeLocationList) {
        if (postcodeLocationList != null && !postcodeLocationList.isEmpty()) {
            return postcodeLocationList.get(0);
        } else {
            return null;
        }
    }
    
    private PostcodeLocation getDefaultNonLocation() {
        return PostcodeLocation
                    .builder()
                    .latitude(Double.NaN)
                    .longitude(Double.NaN)
                    .postcode("Not a valid postcode")
                    .build();
    }

    /**
     * Updates the latitude and longitude of the postcode location.
     * @param request
     * @return 
     */
    public PostcodeResponse updatePostcodeLocation(PostcodeResponse request) {
        postcodeRepository
                .findByPostcode(request.getPostcode())
                .stream()
                .forEach(p -> this.updateLocation(p, request));
        return request;
    }

    private void updateLocation(PostcodeLocation postcodeLocation, PostcodeResponse request) {
        postcodeLocation.setLatitude(request.getLatitude());
        postcodeLocation.setLongitude(request.getLongitude());
        postcodeRepository.save(postcodeLocation);
    }

    
}
