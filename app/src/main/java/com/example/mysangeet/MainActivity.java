package com.example.mysangeet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);

        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        //Toast.makeText(MainActivity.this, "Permission Given", Toast.LENGTH_SHORT).show();
                        ArrayList<File>mySongs=fetchSongs(Environment.getExternalStorageDirectory());
                        String []list=new String[mySongs.size()];
                        for(int i=1;i<=mySongs.size();i++)
                        {
                            String kp=Integer.toString(i);
                            list[i-1]=kp.concat(".s ").concat(mySongs.get(i-1).getName().replace(".mp3",""));
                        }
                        ArrayAdapter<String>adapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,list);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent=new Intent(MainActivity.this,PlaySong.class);
                                String currentSong=listView.getItemAtPosition(i).toString();
                                intent.putExtra("songList",mySongs);
                                intent.putExtra("currentSong",currentSong);
                                intent.putExtra("position",i);
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                })
                .check();
    }
    public ArrayList<File> fetchSongs(File file){
        ArrayList arrayList=new ArrayList();
        File [] songs=file.listFiles();
        if(songs !=null){
            for(File myfile:songs)
            {
                if(!myfile.isHidden() && myfile.isDirectory())
                {
                    arrayList.addAll(fetchSongs(myfile));
                }
                else{
                    if(myfile.getName().endsWith("mp3") && !myfile.getName().startsWith("."))
                    {
                        arrayList.add(myfile);
                    }
                }
            }
        }
        return arrayList;
    }
}