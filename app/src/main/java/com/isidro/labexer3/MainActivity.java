package com.isidro.labexer3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.FileHandler;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);
    }


    public void Next(View view) {
        startActivity(new Intent(this,Main2Activity.class));
    }

    public void SaveInInternalStorage(View view) {
        String text=editText.getText().toString();
        File dir = getFilesDir();
        File file = new File(dir,"File1.txt");
        writeData(file, text);

    }

    public void SaveInInternalCacheStorage(View view) {
        String text=editText.getText().toString();
        File dir = getCacheDir();
        File file = new File(dir,"File2.txt");
        writeData(file, text);

    }

    public void SaveInExternalCacheStorage(View view) {
        String text=editText.getText().toString();
        File dir = getExternalCacheDir();
        File file = new File(dir,"File3.txt");
        writeData(file, text);

    }

    public void SaveInExternalPrivateStorage(View view) {
        String text=editText.getText().toString();
        File dir = getExternalFilesDir("MyDir");
        File file = new File(dir,"File4.txt");
        writeData(file, text);

    }

    public void SaveInExternalPublicStorage(View view) {
        String text=editText.getText().toString();
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(dir,"File5.txt");
        writeData(file, text);

    }

    public void writeData(File file,String text)
    {
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream = new FileOutputStream(file);

            fileOutputStream.write(text.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fileOutputStream!=null)
            {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Toast.makeText(this,"Data saved to "+file.getAbsolutePath(),Toast.LENGTH_LONG).show();

    }
}
