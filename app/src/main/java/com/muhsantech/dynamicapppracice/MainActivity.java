package com.muhsantech.dynamicapppracice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.muhsantech.dynamicapppracice.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String url = "https://jsonplaceholder.typicode.com/users"; // GET
        ArrayList<String> arrNames = new ArrayList<>();

//        AndroidNetworking.initialize(this);
//        AndroidNetworking.get(url)
//                .setPriority(Priority.HIGH)
//                .build()
//                .getAsJSONArray(new JSONArrayRequestListener() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//
//                        Log.d("RES", response.toString());
//
//                        //paring
//
//                        try {
//
//                            for (int i = 0; i < response.length(); i++) {
//                                JSONObject objResult = response.getJSONObject(i);
//                                String name = objResult.getString("name");
//                                String username = objResult.getString("username");
//
//                                arrNames.add(name + "  " + username);
//
//                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arrNames);
//
//                                binding.listNameView.setAdapter(arrayAdapter);
//                            }
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        anError.printStackTrace();
//                        Log.e("ERROR", anError.toString());
//                    }
//                });



        AndroidNetworking.initialize(this);
        AndroidNetworking.post("https://wscubetech.org/android-course/get-data.php")
                .addBodyParameter("course_id", "1")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("res", response.toString());

                        try {
                            JSONObject objData = response.getJSONObject("data");
                            String name = objData.getString("name");

                            Log.d("Name", name);

                            JSONObject objDes = objData.getJSONObject("description");
                            String extension = objDes.getString("extension");

                            Log.d("Ext", extension);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                    }
                });
    }
}