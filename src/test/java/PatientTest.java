/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

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

public class PatientTest {
    
    @Test
    void testPatientConstructor() {
        Patient patient = new Patient("P001", "Thabo", "Mokoena", 45, "Male", "Flu", PatientCategory.INPATIENT);
        
        assertEquals("P001", patient.getPatientId());
        assertEquals("Thabo", patient.getFirstName());
        assertEquals("Mokoena", patient.getLastName());
        assertEquals(45, patient.getAge());
        assertEquals("Male", patient.getGender());
        assertEquals("Flu", patient.getMedicalCondition());
        assertEquals(PatientCategory.INPATIENT, patient.getCategory());
    }
    
    @Test
    void testPatientWithDifferentNames() {
        Patient patient1 = new Patient("P002", "Lerato", "Ndlovu", 30, "Female", "Fracture", PatientCategory.OUTPATIENT);
        assertEquals("Lerato", patient1.getFirstName());
        assertEquals("Ndlovu", patient1.getLastName());
        
        Patient patient2 = new Patient("P003", "Sipho", "Zulu", 60, "Male", "Heart Disease", PatientCategory.EMERGENCY);
        assertEquals("Sipho", patient2.getFirstName());
        assertEquals("Zulu", patient2.getLastName());
    }
    
    @Test
    void testPatientSetters() {
        Patient patient = new Patient("P001", "Thabo", "Mokoena", 45, "Male", "Flu", PatientCategory.INPATIENT);
        
        patient.setFirstName("Thabiso");
        patient.setLastName("Mokoena-Masondo");
        patient.setAge(46);
        patient.setGender("Male");
        patient.setMedicalCondition("Pneumonia");
        
        assertEquals("Thabiso", patient.getFirstName());
        assertEquals("Mokoena-Masondo", patient.getLastName());
        assertEquals(46, patient.getAge());
        assertEquals("Pneumonia", patient.getMedicalCondition());
    }
    
    @Test
    void testPatientDisplayDetails() {
        Patient patient = new Patient("P001", "Thabo", "Mokoena", 45, "Male", "Flu", PatientCategory.INPATIENT);
        assertDoesNotThrow(() -> patient.displayDetails());
    }
}