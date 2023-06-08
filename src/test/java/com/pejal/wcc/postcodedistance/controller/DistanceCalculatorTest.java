/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.pejal.wcc.postcodedistance.controller;

import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ryzen 5
 */
@Slf4j
public class DistanceCalculatorTest {
    
    public DistanceCalculatorTest() {
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

    @Test
    public void testCalculateDistance() {
        double expectedDistance = 410.19398329490474;
        DistanceCalculator calculator = new DistanceCalculator();
        double distance = calculator.calculateDistance(57.064273,-2.130018, 53.407920,-2.989414);
        log.info("Distance: " + distance);
        assertThat(distance).isEqualTo(expectedDistance);
    }
    
}
