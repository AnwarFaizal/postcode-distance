/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pejal.wcc.postcodedistance;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author pejalhebat
 */

public interface PostcodeRepository extends JpaRepository<PostcodeLocation, Long> {
    List<PostcodeLocation> findByPostcode(String postcode);
}
