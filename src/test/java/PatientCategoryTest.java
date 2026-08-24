/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.hospitalpatientadmissionsystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
// 

public class PatientCategoryTest {
    
    @Test
    void testPatientCategoryValues() {
        PatientCategory[] categories = PatientCategory.values();
        assertEquals(3, categories.length);
        assertEquals(PatientCategory.INPATIENT, categories[0]);
        assertEquals(PatientCategory.OUTPATIENT, categories[1]);
        assertEquals(PatientCategory.EMERGENCY, categories[2]);
    }
    
    @Test
    void testPatientCategoryValueOf() {
        PatientCategory category = PatientCategory.valueOf("INPATIENT");
        assertEquals(PatientCategory.INPATIENT, category);
        
        category = PatientCategory.valueOf("OUTPATIENT");
        assertEquals(PatientCategory.OUTPATIENT, category);
        
        category = PatientCategory.valueOf("EMERGENCY");
        assertEquals(PatientCategory.EMERGENCY, category);
    }
    
    @Test
    void testPatientCategoryInPatientClass() {
        Patient inpatient = new Patient("P001", "Thabo", "Mokoena", 45, "Male", 
                                      "Flu", PatientCategory.INPATIENT);
        assertEquals(PatientCategory.INPATIENT, inpatient.getCategory());
        
        Patient outpatient = new Patient("P002", "Lerato", "Ndlovu", 30, "Female", 
                                       "Cold", PatientCategory.OUTPATIENT);
        assertEquals(PatientCategory.OUTPATIENT, outpatient.getCategory());
        
        Patient emergency = new Patient("P003", "Sipho", "Zulu", 60, "Male", 
                                      "Heart Attack", PatientCategory.EMERGENCY);
        assertEquals(PatientCategory.EMERGENCY, emergency.getCategory());
    }
}