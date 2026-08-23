/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.hospitalpatientadmissionsystem.Inpatient;
import com.mycompany.hospitalpatientadmissionsystem.Patient;
import com.mycompany.hospitalpatientadmissionsystem.PatientCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
// 

public class InpatientTest {
    
    @Test
    void testInpatientConstructor() {
        Inpatient inpatient = new Inpatient("P001", "Thabo", "Mokoena", 45, "Male", 
                                          "Flu", PatientCategory.INPATIENT, "Ward A", "B01");
        
        assertEquals("P001", inpatient.getPatientId());
        assertEquals("Thabo", inpatient.getFirstName());
        assertEquals("Mokoena", inpatient.getLastName());
        assertEquals(45, inpatient.getAge());
        assertEquals("Male", inpatient.getGender());
        assertEquals("Flu", inpatient.getMedicalCondition());
        assertEquals(PatientCategory.INPATIENT, inpatient.getCategory());
        assertEquals("Ward A", inpatient.getWardNumber());
        assertEquals("B01", inpatient.getBedNumber());
    }
    
    @Test
    void testInpatientWithSouthAfricanNames() {
        Inpatient inpatient1 = new Inpatient("P004", "Zanele", "Dlamini", 35, "Female", 
                                           "Surgery", PatientCategory.INPATIENT, "Ward A", "B01");
        assertEquals("Zanele", inpatient1.getFirstName());
        assertEquals("Dlamini", inpatient1.getLastName());
        
        Inpatient inpatient2 = new Inpatient("P005", "Mandla", "Nkosi", 40, "Male", 
                                           "Fracture", PatientCategory.INPATIENT, "Ward B", "B02");
        assertEquals("Mandla", inpatient2.getFirstName());
        assertEquals("Nkosi", inpatient2.getLastName());
    }
    
    @Test
    void testInpatientInheritance() {
        Inpatient inpatient = new Inpatient("P001", "Thabo", "Mokoena", 45, "Male", 
                                          "Flu", PatientCategory.INPATIENT, "Ward A", "B01");
        
        assertTrue(inpatient instanceof Patient);
        assertEquals("Thabo", inpatient.getFirstName());
        inpatient.setFirstName("Thabiso");
        assertEquals("Thabiso", inpatient.getFirstName());
    }
    
    @Test
    void testInpatientSetters() {
        Inpatient inpatient = new Inpatient("P004", "Zanele", "Dlamini", 35, "Female", 
                                          "Surgery", PatientCategory.INPATIENT, "Ward A", "B01");
        
        inpatient.setWardNumber("Ward B");
        inpatient.setBedNumber("B02");
        
        assertEquals("Ward B", inpatient.getWardNumber());
        assertEquals("B02", inpatient.getBedNumber());
    }
    
    @Test
    void testInpatientDisplayDetails() {
        Inpatient inpatient = new Inpatient("P001", "Thabo", "Mokoena", 45, "Male", 
                                          "Flu", PatientCategory.INPATIENT, "Ward A", "B01");
        assertDoesNotThrow(() -> inpatient.displayDetails());
    }
    
    @Test
    void testInpatientSuperKeyword() {
        Inpatient inpatient = new Inpatient("P001", "Thabo", "Mokoena", 45, "Male", 
                                          "Flu", PatientCategory.INPATIENT, "Ward A", "B01");
        
        assertEquals("P001", inpatient.getPatientId());
        assertEquals("Thabo", inpatient.getFirstName());
        assertEquals("Mokoena", inpatient.getLastName());
        assertEquals(45, inpatient.getAge());
        assertEquals("Male", inpatient.getGender());
        assertEquals("Flu", inpatient.getMedicalCondition());
        assertEquals(PatientCategory.INPATIENT, inpatient.getCategory());
    }
}
