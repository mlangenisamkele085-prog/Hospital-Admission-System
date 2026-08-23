/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hospitalpatientadmissionsystem;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author Student
 */
public class HospitalPatientAdmissionSystem {
 
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static String[][] beds = new String[4][5];
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeBeds();
        int choice;

        do {
            System.out.println("\nMEDICARE HOSPITAL PATIENT ADMISSION SYSTEM");
            System.out.println("1. Patient Management");
            System.out.println("2. Bed Management");
            System.out.println("3. Reports");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    patientManagementMenu();
                    break;
                case 2:
                    bedManagementMenu();
                    break;
                case 3:
                    reportsMenu();
                    break;
                case 4:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice.Please try again.");
            }
        } while (choice != 4);
    }

    private static void initializeBeds() {
        int bedNumber = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                beds[i][j] = "B" + String.format("%02d", bedNumber);
                bedNumber++;
            }
        }
    }

    private static void patientManagementMenu() {
        int choice;
        do {
            System.out.println("\nPATIENT MANAGEMENT");
            System.out.println("1. Register Patient");
            System.out.println("2. Search Patient");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. Display All Patients");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    searchPatient();
                    break;
                case 3:
                    updatePatient();
                    break;
                case 4:
                    deletePatient();
                    break;
                case 5:
                    displayAllPatients();
                    break;
                case 6:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 6);
    }

    private static void registerPatient() {
        System.out.println("\n--- REGISTER NEW PATIENT ---");
        System.out.print("Enter Patient ID: ");
        String id = scanner.nextLine();

        if (findPatientById(id) != null) {
            System.out.println("Error: Patient ID already exists.");
            return;
        }

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();
        
        System.out.print("Enter Medical Condition: ");
        String condition = scanner.nextLine();

        System.out.println("Select Patient Category:");
        System.out.println("1. Inpatient");
        System.out.println("2. Outpatient");
        System.out.println("3. Emergency");
        System.out.print("Enter choice: ");
        int categoryChoice = scanner.nextInt();
        scanner.nextLine();

        PatientCategory category;
        switch (categoryChoice) {
            case 1:
                category = PatientCategory.INPATIENT;
                break;
            case 2:
                category = PatientCategory.OUTPATIENT;
                break;
            case 3:
                category = PatientCategory.EMERGENCY;
                break;
            default:
                System.out.println("Invalid category. Defaulting to Outpatient.");
                category = PatientCategory.OUTPATIENT;
        }

        if (category == PatientCategory.INPATIENT) {
            
            System.out.print("Enter Ward Number: ");
            String wardNumber = scanner.nextLine();
            
            System.out.print("Enter Bed Number: ");
            String bedNumber = scanner.nextLine();
            
            Inpatient inpatient = new Inpatient(id, firstName, lastName, age, gender, condition, category, wardNumber, bedNumber);
            patients.add(inpatient);
            System.out.println("Inpatient registered successfully.");
            
        } else {
            Patient patient = new Patient(id, firstName, lastName, age, gender, condition, category);
            patients.add(patient);
            System.out.println("Patient registered successfully.");
        }
    }

    private static Patient findPatientById(String id) {
        for (Patient p : patients) {
            if (p.getPatientId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    private static void searchPatient() {
        System.out.print("\nEnter Patient ID to search: ");
        String id = scanner.nextLine();
        
        Patient patient = findPatientById(id);
        if (patient != null) {
            patient.displayDetails();
        } else {
            System.out.println("Patient not found.");
        }
    }

    private static void updatePatient() {
        System.out.print("\nEnter Patient ID to update: ");
        String id = scanner.nextLine();
        
        Patient patient = findPatientById(id);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.println("Updating details for Patient ID: " + id);
        System.out.print("Enter new First Name (current: " + patient.getFirstName() + "): ");
        String firstName = scanner.nextLine();
        if (!firstName.isEmpty()) patient.setFirstName(firstName);

        System.out.print("Enter new Last Name (current: " + patient.getLastName() + "): ");
        String lastName = scanner.nextLine();
        if (!lastName.isEmpty()) patient.setLastName(lastName);

        System.out.print("Enter new Age (current: " + patient.getAge() + "): ");
        String ageInput = scanner.nextLine();
        if (!ageInput.isEmpty()) patient.setAge(Integer.parseInt(ageInput));

        System.out.print("Enter new Gender (current: " + patient.getGender() + "): ");
        String gender = scanner.nextLine();
        if (!gender.isEmpty()) patient.setGender(gender);

        System.out.print("Enter new Medical Condition (current: " + patient.getMedicalCondition() + "): ");
        String condition = scanner.nextLine();
        if (!condition.isEmpty()) patient.setMedicalCondition(condition);

        System.out.println("Patient details updated successfully.");
    }

    private static void deletePatient() {
        System.out.print("\nEnter Patient ID to delete: ");
        String id = scanner.nextLine();
        Patient patient = findPatientById(id);
        if (patient != null) {
            patients.remove(patient);
            System.out.println("Patient deleted successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    }

    private static void displayAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("\nNo patients registered.");
            return;
        }
        System.out.println("\n--- ALL REGISTERED PATIENTS ---");
        for (Patient p : patients) {
            p.displayDetails();
            System.out.println("-----------------------------");
        }
    }

    private static void bedManagementMenu() {
        int choice;
        do {
            System.out.println("\n--- BED MANAGEMENT ---");
            System.out.println("1. Allocate Bed to Inpatient");
            System.out.println("2. Release Bed");
            System.out.println("3. Display Ward Layout");
            System.out.println("4. Display Available Beds");
            System.out.println("5. Display Occupied Beds");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    allocateBed();
                    break;
                case 2:
                    releaseBed();
                    break;
                case 3:
                    displayWardLayout();
                    break;
                case 4:
                    displayAvailableBeds();
                    break;
                case 5:
                    displayOccupiedBeds();
                    break;
                case 6:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 6);
    }

    private static void allocateBed() {
        System.out.print("\nEnter Patient ID to allocate bed: ");
        String id = scanner.nextLine();
        Patient patient = findPatientById(id);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        if (!(patient instanceof Inpatient)) {
            System.out.println("Only Inpatients can be allocated a bed.");
            return;
        }

        Inpatient inpatient = (Inpatient) patient;
        if (inpatient.getBedNumber() != null && !inpatient.getBedNumber().isEmpty()) {
            System.out.println("Patient already has a bed allocated.");
            return;
        }

        if (getAvailableBedCount() == 0) {
            System.out.println("No beds available.");
            return;
        }

        System.out.println("Available beds:");
        displayAvailableBeds();
        System.out.print("Enter bed number to allocate (e.g., B01): ");
        String bedNumber = scanner.nextLine().toUpperCase();

        if (!isBedValid(bedNumber)) {
            System.out.println("Invalid bed number.");
            return;
        }

        if (isBedOccupied(bedNumber)) {
            System.out.println("Bed is already occupied.");
            return;
        }

        allocateBedToPatient(bedNumber, inpatient);
        System.out.println("Bed " + bedNumber + " allocated successfully.");
    }

    private static int getAvailableBedCount() {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (!beds[i][j].startsWith("X")) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isBedValid(String bedNumber) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (beds[i][j].equals(bedNumber)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isBedOccupied(String bedNumber) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (beds[i][j].equals(bedNumber)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void allocateBedToPatient(String bedNumber, Inpatient inpatient) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (beds[i][j].equals(bedNumber)) {
                    beds[i][j] = "X" + bedNumber.substring(1);
                    inpatient.setBedNumber(bedNumber);
                    inpatient.setWardNumber("Ward A");
                    return;
                }
            }
        }
    }

    private static void releaseBed() {
        System.out.print("\nEnter Patient ID to release bed: ");
        String id = scanner.nextLine();
        Patient patient = findPatientById(id);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        if (!(patient instanceof Inpatient)) {
            System.out.println("Patient is not an inpatient.");
            return;
        }

        Inpatient inpatient = (Inpatient) patient;
        String bedNumber = inpatient.getBedNumber();
        if (bedNumber == null || bedNumber.isEmpty()) {
            System.out.println("Patient does not have a bed allocated.");
            return;
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (beds[i][j].equals("X" + bedNumber.substring(1))) {
                    beds[i][j] = bedNumber;
                    inpatient.setBedNumber("");
                    inpatient.setWardNumber("");
                    System.out.println("Bed " + bedNumber + " released successfully.");
                    return;
                }
            }
        }
    }

    private static void displayWardLayout() {
        System.out.println("\n--- WARD LAYOUT (4 x 5) ---");
        System.out.println("Available: B01-B20 | Occupied: X01-X20");
        System.out.println("----------------------------------------");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(beds[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println("----------------------------------------");
    }

    private static void displayAvailableBeds() {
        System.out.println("\n--- AVAILABLE BEDS ---");
        boolean found = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (!beds[i][j].startsWith("X")) {
                    System.out.print(beds[i][j] + "  ");
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No available beds.");
        }
        System.out.println();
    }

    private static void displayOccupiedBeds() {
        System.out.println("\n--- OCCUPIED BEDS ---");
        boolean found = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (beds[i][j].startsWith("X")) {
                    System.out.print(beds[i][j] + "  ");
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No occupied beds.");
        }
        System.out.println();
    }

    private static void reportsMenu() {
        int choice;
        do {
            System.out.println("\n--- REPORTS ---");
            System.out.println("1. Display All Patients");
            System.out.println("2. Display Available Beds");
            System.out.println("3. Display Occupied Beds");
            System.out.println("4. Total Registered Patients");
            System.out.println("5. Total Occupied Beds");
            System.out.println("6. Ward Occupancy Percentage");
            System.out.println("7. Return to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayAllPatients();
                    break;
                case 2:
                    displayAvailableBeds();
                    break;
                case 3:
                    displayOccupiedBeds();
                    break;
                case 4:
                    System.out.println("\nTotal Registered Patients: " + patients.size());
                    break;
                case 5:
                    int occupied = 20 - getAvailableBedCount();
                    System.out.println("\nTotal Occupied Beds: " + occupied);
                    break;
                case 6:
                    int occupiedBeds = 20 - getAvailableBedCount();
                    double percentage = (occupiedBeds / 20.0) * 100;
                    System.out.printf("\nWard Occupancy Percentage: %.2f%%\n", percentage);
                    break;
                case 7:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 7);
    }
}

