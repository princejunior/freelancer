package com.example.freelance;

import static android.app.Activity.RESULT_OK;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.freelance.createContent.Upload;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {
//    view
     private View rootView;
//    widgets
    private ImageView imageView;
    private ProgressBar progressBar;
    private EditText titleEdit;
    private EditText descriptionEdit;
    private EditText priceEdit;
    private Button uploadButton;
    private Button cancelButton;
//    connections
    private Uri imageUri;
    private Database database;
//    reference to store data in database
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("User_Content");
    //    reference to store image data in storage
    private StorageReference reference = FirebaseStorage.getInstance().getReference();



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(String param1, String param2) {

        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

//  connects the fragment activity page (palettes) to the backend
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//     pulls the fragment_third page
        rootView = inflater.inflate(R.layout.fragment_third, container, false);
//      connects the buttons to the palettes on activity page
        imageView = rootView.findViewById(R.id.imageView_ThirdFragment);
        progressBar = rootView.findViewById(R.id.progressBar_ThirdFragment);
        titleEdit =  rootView.findViewById(R.id.title_edit_ThridFragment);
        descriptionEdit =  rootView.findViewById(R.id.description_edit_ThirdFragment);
        priceEdit =  rootView.findViewById(R.id.price_edit_ThirdFragment);
        uploadButton = rootView.findViewById(R.id.upload_button_ThirdFragment);
        cancelButton =  rootView.findViewById(R.id.cancel_button_ThirdFragment);
        progressBar.setVisibility(View.INVISIBLE);
//      once user clicks on imageView then the user will be able to select an image from phone
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 2 );

            }
        });

//      Once clicked upload content button then function will run uploadUserContent
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUri != null) {
                    uploadUserCreatedContent(imageUri);
                } else {
                    Toast.makeText(getActivity(), "Please select an image", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode,data);
//
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }

    }
//  uploads image data to firebase storage
    public void uploadUserCreatedContent(Uri uri) {
        Boolean successful = false;
//      creating a specific reference for the image (one of a kind)
        StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));

        String title = (String) titleEdit.getText().toString();
        String description = (String) descriptionEdit.getText().toString();
        String price = (String) priceEdit.getText().toString();
        Log.i(null,  title + " " + description +" " + uri.toString() +" " + price);
        if (title == null)
            Toast.makeText(getActivity(), "Enter Title", Toast.LENGTH_SHORT).show();
        else if (description == null)
            Toast.makeText(getActivity(), "Enter a Description", Toast.LENGTH_SHORT).show();
        else if (price == null)
            Toast.makeText(getActivity(), "Enter a price", Toast.LENGTH_SHORT).show();
        else {
            fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//              gets the url for the image from Storage and stores the url in the database
                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        //                  stores the data in database and storage
                        @Override
                        public void onSuccess(Uri uri) {


                            Upload contentUpload = new Upload(title, description, uri.toString(), price);


//                        ImageUrlModel imageUrlModel = new ImageUrlModel(uri.toString());
//                      creates a unique Id for the content
                            String modelId = root.push().getKey();
//                      shows where to store content data in the database
//                        root.child(modelId).setValue(imageUrlModel);
                            root.child(modelId).setValue(contentUpload);
//                      Lets the user know the content was uploaded successfully
                            Toast.makeText(getActivity(), "Uploaded Successful", Toast.LENGTH_SHORT).show();
                            Log.i(null, "Uploaded Successful");
                        }
                    });
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                //          when user clicks on upload content, a progress bar will show that it is trying to
//          upload content to database and storage.
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }).addOnFailureListener(new OnFailureListener() {
                //          displays a Toast when the user did not pick an image when upload button was clicked
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(), "Please select an image", Toast.LENGTH_SHORT).show();
                    Log.i(null, "failed to upload image uri");

                }
            });
        }
    }
//  Creates file extension for image.
    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }

}