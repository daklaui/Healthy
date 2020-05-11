package com.example.healthy.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.healthy.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class CreateFood extends AppCompatActivity {
ImageView imageView;
EditText TitreNewFoo,CaloriesFood,ProteinesFood,GlucidesFood,CalciumFood,FerFood,Graisse,Sodium,Fibres,Sucres;
    Uri uri;
    Button uploade;
    LoadingDialog loadingDialog;
//create food into api
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_food);

        Toolbar toolbar=findViewById(R.id.include3);
        toolbar.setTitle("Add food Api");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {/* ... */}
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */}

                    @Override
                    public void onPermissionRationaleShouldBeShown(com.karumi.dexter.listener.PermissionRequest permission, PermissionToken token) {

                    }


                }).check();

        loadingDialog=new LoadingDialog(this);
        TitreNewFoo=findViewById(R.id.TitreNewFoo);
        CaloriesFood=findViewById(R.id.CaloriesFood);
        ProteinesFood=findViewById(R.id.ProteinesFood);
        GlucidesFood=findViewById(R.id.GlucidesFood);
        CalciumFood=findViewById(R.id.CalciumFood);
        FerFood=findViewById(R.id.FerFood);
        Graisse=findViewById(R.id.Graisse);
        Sodium=findViewById(R.id.SODIUM);
        Fibres=findViewById(R.id.Fibres);
        Sucres=findViewById(R.id.Sucres);
        uploade=findViewById(R.id.uploade);

        imageView = findViewById(R.id.AddImageFood);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        uploade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
    }


    private void selectImage()
    {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        intent.setType("image/*");

        startActivityForResult(intent, 42);
    }

    private String uriToFilename(Uri uri) {
        String path = null;

        if ((Build.VERSION.SDK_INT < 19) && (Build.VERSION.SDK_INT > 11)) {
            path = getRealPathFromURI_API11to18(this, uri);
        } else {
            path = getFilePath(this, uri);
        }

        return path;
    }
    public static String getRealPathFromURI_API11to18(Context context, Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        String result = null;
        CursorLoader cursorLoader = new CursorLoader(
                context,
                contentUri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        if (cursor != null) {
            int column_index =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            result = cursor.getString(column_index);
        }
        return result;
    }

    public String getFilePath(Context context, Uri uri) {
        //Log.e("uri", uri.getPath());
        String filePath = "";
        if (DocumentsContract.isDocumentUri(context, uri)) {
            String wholeID = DocumentsContract.getDocumentId(uri);
            //Log.e("wholeID", wholeID);
            // Split at colon, use second item in the array
            String[] splits = wholeID.split(":");
            if (splits.length == 2) {
                String id = splits[1];

                String[] column = {MediaStore.Images.Media.DATA};
                // where id is equal to
                String sel = MediaStore.Images.Media._ID + "=?";
                Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        column, sel, new String[]{id}, null);
                int columnIndex = cursor.getColumnIndex(column[0]);
                if (cursor.moveToFirst()) {
                    filePath = cursor.getString(columnIndex);
                }
                cursor.close();
            }
        } else {
            filePath = uri.getPath();
        }
        return filePath;
    }

                // Get Path of selected image
    private String getPath(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(this,    contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }
  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
      super.onActivityResult(requestCode, resultCode, data);

      if (requestCode == 42 && resultCode == Activity.RESULT_OK) {
          uri = null;
          if (data != null) {
              uri = data.getData();
              Log.i("test", "Uri: " + uri.toString());
              imageView.setImageURI(uri);
             // upload.setVisibility(View.VISIBLE);
          }
      }
  }


    public void uploadImage(){
        loadingDialog.startLoadingDialog();
        if(uri == null){
            return;
        }

        final File imageFile = new File(uriToFilename(uri));
        Uri uris = Uri.fromFile(imageFile);
        String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uris.toString());
        String mime = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension.toLowerCase());
        String imageName = imageFile.getName();

        final JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("Titre",TitreNewFoo.getText().toString());
            object.put("Image","/Content/Img/"+imageName);
            object.put("Protéines",ProteinesFood.getText().toString());
            object.put("Glucides",GlucidesFood.getText().toString());
            object.put("Calcium",CalciumFood.getText().toString());
            object.put("Fer",FerFood.getText().toString());
            object.put("Calories",CaloriesFood.getText().toString());
            object.put("Graisse",Graisse.getText().toString());
            object.put("Sodium",Sodium.getText().toString());
            object.put("Fibres",Fibres.getText().toString());
            object.put("Sucres",Sucres.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Log.e(TAG, imageFile.getName()+" "+mime+" "+uriToFilename(uri));
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", imageName,
                        RequestBody.create(imageFile, MediaType.parse(mime)))
                .build();



        OkHttpClient imageUploadClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url("http://92.222.83.184:9999/api/FileUploading/UploadFile")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();


        imageUploadClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage();
               Toast.makeText(CreateFood.this, "Error uploading file", Toast.LENGTH_LONG).show();
                loadingDialog.fermer();
                Log.e("failure Response", mMessage);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String mMessage = response.body().string();

                CreateFood.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        SendData(object);
                        Log.e("testmoataz", mMessage);

                    }
                });
            }
        });
    }




private  void SendData(JSONObject jsonObject)
{
    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
    String url = "http://92.222.83.184:9999/api/Food";
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(com.android.volley.Request.Method.POST,url,jsonObject,
            new com.android.volley.Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                  //  resultTextView.setText("String Response : "+ response.toString());

                    Toast.makeText(CreateFood.this,response.toString(),Toast.LENGTH_LONG).show();
                    loadingDialog.fermer();
                }
            },new com.android.volley.Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(CreateFood.this,error.getMessage(),Toast.LENGTH_LONG).show();
            loadingDialog.fermer();
           // resultTextView.setText("Error getting response");
        }
    });
    requestQueue.add(jsonObjectRequest);
}


}
