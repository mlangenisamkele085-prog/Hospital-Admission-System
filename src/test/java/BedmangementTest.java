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
import java.util.ArrayList;
/**
 *
 * @author Student
 */
// ==================== BedManagementTest.java ====================

public class BedmangementTest {
    
    private ArrayList<Patient> patients;
    private String[][] beds;
    
    @BeforeEach
    void setUp() {
        patients = new ArrayList<>();
        beds = new String[4][5];
        int bedNumber = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                beds[i][j] = "B" + String.format("%02d", bedNumber);
                bedNumber++;
            }
        }
    }
    
    @Test
    void testInitializeBeds() {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                count++;
                assertTrue(beds[i][j].startsWith("B"));
            }
        }
        assertEquals(20, count);
    }
    
    @Test
    void testAllocateBedToInpatient() {
        Inpatient inpatient = new Inpatient("P004", "Zanele", "Dlamini", 35, "Female", 
                                          "Surgery", PatientCategory.INPATIENT, "", "");
        patients.add(inpatient);
        
        String bedNumber = "B01";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (beds[i][j].equals(bedNumber)) {
                    beds[i][j] = "X" + bedNumber.substring(1);
                    inpatient.setBedNumber(bedNumber);
                    inpatient.setWardNumber("Ward A");
                }
            }
        }
        
        assertEquals("B01", inpatient.getBedNumber());
        assertEquals("Ward A", inpatient.getWardNumber());
        
        boolean bedFound = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (beds[i][j].equals("X01")) {
                    bedFound = true;
                }
            }
        }
        assertTrue(bedFound);
    }
    
    @Test
    void testReleaseBedForInpatient() {
        Inpatient inpatient = new Inpatient("P004", "Zanele", "Dlamini", 35, "Female", 
                                          "Surgery", PatientCategory.INPATIENT, "Ward A", "B01");
        patients.add(inpatient);
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (beds[i][j].equals("B01")) {
                    beds[i][j] = "X01";
                }
            }
        }
        
        String bedNumber = inpatient.getBedNumber();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (beds[i][j].equals("X" + bedNumber.substring(1))) {
                    beds[i][j] = bedNumber;
                    inpatient.setBedNumber("");
                    inpatient.setWardNumber("");
                }
            }
        }
        
        assertEquals("", inpatient.getBedNumber());
        assertEquals("", inpatient.getWardNumber());
        
        boolean bedAvailable = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (beds[i][j].equals("B01")) {
                    bedAvailable = true;
                }
            }
        }
        assertTrue(bedAvailable);
    }
    
    @Test
    void testGetAvailableBedCount() {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (!beds[i][j].startsWith("X")) {
                    count++;
                }
            }
        }
        assertEquals(20, count);
        
        beds[0][0] = "X01";
        beds[0][1] = "X02";
        beds[0][2] = "X03";
        
        count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (!beds[i][j].startsWith("X")) {
                    count++;
                }
            }
        }
        assertEquals(17, count);
    }
    
    @Test
    void testPreventAllocatingOccupiedBed() {
        Inpatient patient1 = new Inpatient("P004", "Zanele", "Dlamini", 35, "Female", 
                                          "Surgery", PatientCategory.INPATIENT, "Ward A", "B01");
        Inpatient patient2 = new Inpatient("P005", "Mandla", "Nkosi", 40, "Male", 
                                          "Fracture", PatientCategory.INPATIENT, "", "");
        patients.add(patient1);
        patients.add(patient2);
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (beds[i][j].equals("B01")) {
                    beds[i][j] = "X01";
                }
            }
        }
        
        boolean bedOccupied = true;
        String bedNumber = "B01";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (beds[i][j].equals(bedNumber)) {
                    bedOccupied = false;
                }
            }
        }
        
        if (!bedOccupied) {
            patient2.setBedNumber(bedNumber);
            patient2.setWardNumber("Ward A");
        }
        
        assertEquals("", patient2.getBedNumber());
    }
}