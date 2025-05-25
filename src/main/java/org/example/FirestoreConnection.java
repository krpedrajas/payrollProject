package org.example;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirestoreConnection {
    Firestore db;

    MainUi gui;

    public FirestoreConnection() {
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("src/main/java/org/example/kenny-payroll-firebase-adminsdk-fbsvc-a967c4a37a.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://kenny-payroll-default-rtdb.asia-southeast1.firebasedatabase.app")
                    .build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            db = FirestoreClient.getFirestore();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Employee> getAllEmployees(){
        ArrayList<Employee> employees=new ArrayList<>();
//        int i = 0;
        try {
            ApiFuture<QuerySnapshot> query = db.collection("employees").get();
            List<QueryDocumentSnapshot> documents = query.get().getDocuments();
//            gui.model.persons.clear();
            for(QueryDocumentSnapshot document : documents){
//                System.out.println(i++);
//                String firstName = document.getString("firstName");
//                String lastName = document.getString("lastName");
//                String documentId = document.getId();
//                gui.model.addEmployee(new Person(firstName,lastName,documentId));

                Employee employee=document.toObject(Employee.class);
                employees.add(employee);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return employees;
    }

    public void addEmployee (Employee employee){
//        Map<String, Object> person = new HashMap<>();
//        person.put("firstName", firstname);
//        person.put("lastName", lastname);

//        ApiFuture<DocumentReference> result = db.collection("persons").add(person);

//        try{
//            System.out.println("Added document with ID: " + result.get().getId());
//        }catch (InterruptedException | ExecutionException f){
//            f.printStackTrace();
//        }
//        db.collection("employees").add(employee);
        db.collection("employees").document(employee.getEmployeeId()).set(employee);
    }

    public void deleteEmployee(String employeeId) {
        db.collection("employees")
                .document(employeeId)
                .delete();
    }

    public void updateEmployee(Employee employee) {
        if (employee.getEmployeeId() != null && !employee.getEmployeeId().isEmpty()) {
            db.collection("employees")
                    .document(employee.getEmployeeId())
                    .set(employee); // Overwrites existing document with same employeeId
        } else {
            System.out.println("ERROR: Employee ID is required for update.");
        }
    }


//    public void DeleteDocuments(int[] selected) {
//
//        if(gui.model.getRowCount() > 1){
//            for (int i =  selected.length-1; i>=0; i--){
//                Person selectedPerson = gui.model.persons.get(selected[i]);
//                String documentId = selectedPerson.getDocumentId();
//                System.out.println("Deleting document with ID: " + documentId);
//
//                DocumentReference documentRef = db.collection("persons").document(documentId);  // Get document reference
//                ApiFuture<WriteResult> writeResult = documentRef.delete();  // Delete the document
//            }
//        }else{
//            Person selectedPerson = gui.model.persons.get(selected[0]);
//            String documentId = selectedPerson.getDocumentId();
//            System.out.println("Deleting document with ID: " + documentId);
//
//            DocumentReference documentRef = db.collection("persons").document(documentId);  // Get document reference
//            ApiFuture<WriteResult> writeResult = documentRef.delete();  // Delete the document
////            try {
////                // Wait for the delete operation to complete
////                WriteResult result = writeResult.get();
////                System.out.println("Document with ID " + documentId + " successfully deleted. Time: " + result.getUpdateTime());
////            } catch (Exception e) {
////                System.err.println("Error occurred while deleting document with ID " + documentId);
////                e.printStackTrace();
////            }
////            persons.remove(index[0]);
//        }
//
//
//
//    }

}