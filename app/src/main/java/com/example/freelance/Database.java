package com.example.freelance;

import android.content.ContentResolver;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Database {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference userRef = database.getReference("user");
    DatabaseReference userContentRef = database.getReference("user_content");
    DatabaseReference userContentDescriptionRef = database.getReference("user_content_description");

    // Create a Cloud Storage reference from the app
    FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    StorageReference userContentImageStorageRef = firebaseStorage.getReference("user_content_images");

    public boolean createUser(String id, String first_name, String last_name, String about_me_description) {
        User user = new User(id, first_name,last_name,about_me_description);
        System.out.println("createUser() " + user);
        userRef.child(id).setValue(user);
        return true;
    }

    public boolean getUser(String id) {
        // Read from the database
        Log.i(null, "Getting User from database: " + userRef.child(id).get());

//                userRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(User.class);
//                Log.d(null, "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(null, "Failed to read value.", error.toException());
//            }
//        });
        return true;
    }

    public boolean uploadUserCreatedContent(Uri uriw) {
        Boolean successful = false;
//        StorageReference fileRef = userContentImageStorageRef
//                .child(System.currentTimeMillis() + "." + getFileExtension(uri));
//        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//            }
//        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//                progressBar_Upload.setVisibility(View.INVISIBLE);
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
////                Toast.makeText(Database.class, "Please select an image", Toast.LENGTH_SHORT);\
//                Log.i(null, "failed to upload image uri");
//
//            }
//        });

//        userContentRef.child().setValue();

        // Create a reference to "mountains.jpg"
        StorageReference mountainsRef = userContentImageStorageRef.child("mountains.jpg");

// Create a reference to 'images/mountains.jpg'
        StorageReference mountainImagesRef = userContentImageStorageRef.child("images/mountains.jpg");

// While the file names are the same, the references point to different files
        mountainsRef.getName().equals(mountainImagesRef.getName());    // true
        mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false

        return true;
    }

    public boolean getUserCreatedContent() {

// Create a reference with an initial file path and name
        StorageReference pathReference = userContentImageStorageRef.child("images/stars.jpg");

// Create a reference to a file from a Cloud Storage URI
        StorageReference gsReference = firebaseStorage.getReferenceFromUrl("gs://bucket/images/stars.jpg");

// Create a reference from an HTTPS URL
// Note that in the URL, characters are URL escaped!
        StorageReference httpsReference = firebaseStorage.getReferenceFromUrl("https://firebasestorage.googleapis.com/b/bucket/o/images%20stars.jpg");
        return true;
    }

    public boolean getUserPurchasedContent() {
        return true;
    }

    public boolean getServicesContent() {
        return true;
    }

    private String getFileExtension(Uri uri) {
//        ContentResolver contentResolver = getContentResolver();
//        MimeTypeMap mime = MimeTypeMap.getSingleton();
//        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
        return "";
    }


    //        Toast.makeText(MainActivity.this,"Firebase connection Success", Toast.LENGTH_LONG).show();

// Write a message to the database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//
//        myRef.setValue("Hello, World!");
//        // Read from the database
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(null, "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(null, "Failed to read value.", error.toException());
//            }
//        });
}
