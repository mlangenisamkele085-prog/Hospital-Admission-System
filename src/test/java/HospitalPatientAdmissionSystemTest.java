/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
 // ==================== HospitalPatientAdmissionSystemTest.java ====================
import com.mycompany.hospitalpatientadmissionsystem.Inpatient;
import com.mycompany.hospitalpatientadmissionsystem.Patient;
import com.mycompany.hospitalpatientadmissionsystem.PatientCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HospitalPatientAdmissionSystemTest {
    
    private ArrayList<Patient> patients;
    
    @BeforeEach
    void setUp() {
        patients = new ArrayList<>();
        Patient patient1 = new Patient("P001", "Thabo", "Mokoena", 45, "Male", "Flu", PatientCategory.INPATIENT);
        Patient patient2 = new Patient("P002", "Lerato", "Ndlovu", 30, "Female", "Fracture", PatientCategory.OUTPATIENT);
        Patient patient3 = new Patient("P003", "Sipho", "Zulu", 60, "Male", "Heart Disease", PatientCategory.EMERGENCY);
        patients.add(patient1);
        patients.add(patient2);
        patients.add(patient3);
    }
    
    @Test
    void testRegisterPatient() {
        Patient newPatient = new Patient("P004", "Nomalanga", "Mthembu", 25, "Female", "Cold", PatientCategory.OUTPATIENT);
        patients.add(newPatient);
        
        assertEquals(4, patients.size());
        
        Patient found = findPatientById("P004");
        assertNotNull(found);
        assertEquals("Nomalanga", found.getFirstName());
        assertEquals("Mthembu", found.getLastName());
        assertEquals(25, found.getAge());
        assertEquals("Female", found.getGender());
        assertEquals("Cold", found.getMedicalCondition());
        assertEquals(PatientCategory.OUTPATIENT, found.getCategory());
    }
    
    @Test
    void testSearchPatient() {
        Patient found = findPatientById("P001");
        assertNotNull(found);
        assertEquals("Thabo", found.getFirstName());
        assertEquals("Mokoena", found.getLastName());
        
        Patient notFound = findPatientById("P999");
        assertNull(notFound);
    }
    
    @Test
    void testUpdatePatientDetails() {
        Patient patient = findPatientById("P001");
        assertNotNull(patient);
        
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
    void testDeletePatient() {
        Patient patient = findPatientById("P002");
        assertNotNull(patient);
        assertEquals(3, patients.size());
        
        patients.remove(patient);
        
        assertEquals(2, patients.size());
        Patient deleted = findPatientById("P002");
        assertNull(deleted);
    }
    
    @Test
    void testPreventDuplicatePatientIDs() {
        Patient duplicate = new Patient("P001", "Sibusiso", "Khumalo", 20, "Male", "Test", PatientCategory.OUTPATIENT);
        
        Patient existing = findPatientById("P001");
        assertNotNull(existing);
        
        if (existing == null) {
            patients.add(duplicate);
        }
        
        assertEquals(3, patients.size());
        
        Patient original = findPatientById("P001");
        assertNotNull(original);
        assertEquals("Thabo", original.getFirstName());
    }
    
    @Test
    void testAllocateBed() {
        Inpatient inpatient = new Inpatient("P004", "Zanele", "Dlamini", 35, "Female", 
                                          "Surgery", PatientCategory.INPATIENT, "", "");
        patients.add(inpatient);
        
        String bedNumber = "B01";
        boolean bedAvailable = true;
        
        if (bedAvailable) {
            inpatient.setBedNumber(bedNumber);
            inpatient.setWardNumber("Ward A");
        }
        
        assertEquals("B01", inpatient.getBedNumber());
        assertEquals("Ward A", inpatient.getWardNumber());
    }
    
    @Test
    void testReleaseBed() {
        Inpatient inpatient = new Inpatient("P004", "Zanele", "Dlamini", 35, "Female", 
                                          "Surgery", PatientCategory.INPATIENT, "Ward A", "B01");
        patients.add(inpatient);
        
        assertEquals("B01", inpatient.getBedNumber());
        
        inpatient.setBedNumber("");
        inpatient.setWardNumber("");
        
        assertEquals("", inpatient.getBedNumber());
        assertEquals("", inpatient.getWardNumber());
    }
    
    @Test
    void testPreventAllocatingOccupiedBed() {
        Inpatient patient1 = new Inpatient("P004", "Zanele", "Dlamini", 35, "Female", 
                                          "Surgery", PatientCategory.INPATIENT, "Ward A", "B01");
        Inpatient patient2 = new Inpatient("P005", "Mandla", "Nkosi", 40, "Male", 
                                          "Fracture", PatientCategory.INPATIENT, "", "");
        patients.add(patient1);
        patients.add(patient2);
        
        String bedNumber = "B01";
        boolean bedOccupied = patient1.getBedNumber().equals(bedNumber);
        
        if (!bedOccupied) {
            patient2.setBedNumber(bedNumber);
            patient2.setWardNumber("Ward A");
        }
        
        assertEquals("", patient2.getBedNumber());
        assertEquals("B01", patient1.getBedNumber());
    }
    
    @Test
    void testPreventBedAllocationWhenFull() {
        int availableBeds = 0;
        
        Inpatient inpatient = new Inpatient("P004", "Nomalanga", "Mthembu", 25, "Female", 
                                          "Cold", PatientCategory.INPATIENT, "", "");
        patients.add(inpatient);
        
        String bedNumber = "B01";
        boolean bedAvailable = (availableBeds > 0);
        
        if (bedAvailable) {
            inpatient.setBedNumber(bedNumber);
            inpatient.setWardNumber("Ward A");
        }
        
        assertEquals("", inpatient.getBedNumber());
    }
    
    @Test
    void testSortPatientsBySurname() {
        Patient p4 = new Patient("P004", "Siphiwe", "Mabaso", 28, "Female", "Asthma", PatientCategory.OUTPATIENT);
        Patient p5 = new Patient("P005", "Bongani", "Ndlovu", 55, "Male", "Diabetes", PatientCategory.INPATIENT);
        patients.add(p4);
        patients.add(p5);
        
        Collections.sort(patients, new Comparator<Patient>() {
            @Override
            public int compare(Patient p1, Patient p2) {
                return p1.getLastName().compareToIgnoreCase(p2.getLastName());
            }
        });
        
        assertEquals("Mabaso", patients.get(0).getLastName());
        assertEquals("Mokoena", patients.get(1).getLastName());
        assertEquals("Ndlovu", patients.get(2).getLastName());
        assertEquals("Ndlovu", patients.get(3).getLastName());
        assertEquals("Zulu", patients.get(4).getLastName());
    }
    
    @Test
    void testSortPatientsByPatientId() {
        Patient p4 = new Patient("P004", "Siphiwe", "Mabaso", 28, "Female", "Asthma", PatientCategory.OUTPATIENT);
        Patient p5 = new Patient("P005", "Bongani", "Ndlovu", 55, "Male", "Diabetes", PatientCategory.INPATIENT);
        patients.add(p4);
        patients.add(p5);
        
        Collections.sort(patients, new Comparator<Patient>() {
            @Override
            public int compare(Patient p1, Patient p2) {
                return p1.getPatientId().compareToIgnoreCase(p2.getPatientId());
            }
        });
        
        assertEquals("P001", patients.get(0).getPatientId());
        assertEquals("P002", patients.get(1).getPatientId());
        assertEquals("P003", patients.get(2).getPatientId());
        assertEquals("P004", patients.get(3).getPatientId());
        assertEquals("P005", patients.get(4).getPatientId());
    }
    
    private Patient findPatientById(String id) {
        for (Patient p : patients) {
            if (p.getPatientId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }
}