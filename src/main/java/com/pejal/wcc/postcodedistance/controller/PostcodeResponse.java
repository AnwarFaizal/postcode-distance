/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pejal.wcc.postcodedistance.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author pejalhebat
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostcodeResponse {
    
    private String postcode;
    
    private Double latitude;
    
    private Double longitude;
    
}
