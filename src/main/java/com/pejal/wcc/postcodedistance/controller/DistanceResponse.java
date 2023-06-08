/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pejal.wcc.postcodedistance.controller;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author pejalhebat
 */
@Data
@Builder
public class DistanceResponse {
    
    private PostcodeResponse a;
    
    private PostcodeResponse b;
    
    private Double distance;
    
    private String unit = "km";
    
}
