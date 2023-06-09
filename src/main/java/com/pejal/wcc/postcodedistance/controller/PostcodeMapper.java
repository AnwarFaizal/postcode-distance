/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pejal.wcc.postcodedistance.controller;

import com.pejal.wcc.postcodedistance.PostcodeLocation;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * Maps the postcode location entity to the postcode response payload.
 * @author pejalhebat
 */
@Mapper
public interface PostcodeMapper {
    
    PostcodeResponse entityToResponse(PostcodeLocation postcodeLocation);
    List<PostcodeResponse> map(List<PostcodeLocation> postcodes);
    
}
