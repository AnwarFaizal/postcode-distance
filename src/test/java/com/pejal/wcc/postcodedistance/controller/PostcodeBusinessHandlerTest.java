/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.pejal.wcc.postcodedistance.controller;

import com.pejal.wcc.postcodedistance.PostcodeLocation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 *
 * @author pejalhebat
 */
@Slf4j
public class PostcodeBusinessHandlerTest {
    
    public PostcodeBusinessHandlerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getDistanceBetween method, of class PostcodeBusinessHandler.
     */
    @Test
    public void testGetDistanceBetween_PostcodeLocation_PostcodeLocation() {
        log.info("getDistanceBetween");
        PostcodeLocation locationA = PostcodeLocation
                .builder()
                .postcode("AB12 5GL")
                .latitude(57.081938)
                .longitude(-2.246567)
                .build();
        PostcodeLocation locationB = PostcodeLocation
                .builder()
                .postcode("L2 2HF")
                .latitude(53.40792)
                .longitude(-2.989414)
                .build();
        PostcodeBusinessHandler instance = new PostcodeBusinessHandler();
        DistanceResponse expResult = DistanceResponse.builder()
                .a(PostcodeResponse.builder()
                        .postcode("AB12 5GL")
                        .latitude(57.081938)
                        .longitude(-2.246567)
                        .build())
                .b(PostcodeResponse.builder()
                        .postcode("L2 2HF")
                        .latitude(53.40792)
                        .longitude(-2.989414)
                        .build())
                .distance(411.2302392863869)
                .unit("km")
                .build();
        DistanceResponse result = instance.getDistanceBetween(locationA, locationB);
        assertThat(result).isEqualTo(expResult);
    }

    /**
     * Test of formatToDistanceResponse method, of class PostcodeBusinessHandler.
     */
    @Test
    public void testFormatToDistanceResponse() {
        log.info("formatToDistanceResponse");
        PostcodeLocation locationA = PostcodeLocation
                .builder()
                .postcode("AB12 5GL")
                .latitude(57.081938)
                .longitude(-2.246567)
                .build();
        PostcodeLocation locationB = null;
        PostcodeLocation notALocation = PostcodeLocation
                    .builder()
                    .latitude(Double.NaN)
                    .longitude(Double.NaN)
                    .postcode("Not a valid postcode")
                    .build();
        PostcodeBusinessHandler instance = new PostcodeBusinessHandler();
        DistanceResponse expResult = DistanceResponse.builder()
                .a(PostcodeResponse.builder()
                        .postcode("AB12 5GL")
                        .latitude(57.081938)
                        .longitude(-2.246567)
                        .build())
                .b(PostcodeResponse.builder()
                        .postcode("Not a valid postcode")
                        .latitude(Double.NaN)
                        .longitude(Double.NaN)
                        .build())
                .distance(0.0)
                .unit("km")
                .build();
        DistanceResponse result = instance.formatToDistanceResponse(locationA, locationB);
        assertThat(result).isEqualTo(expResult);

    }
    
}
