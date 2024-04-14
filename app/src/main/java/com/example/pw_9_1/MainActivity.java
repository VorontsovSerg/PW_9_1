package com.example.pw_9_1;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainActivity extends AppCompatActivity {

    private TextView textViewFileContent;
    private EditText editTextFileName;
    private TextView textViewFileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewFileContent = findViewById(R.id.textViewFileContent);
        editTextFileName = findViewById(R.id.editTextFileName);
        textViewFileList = findViewById(R.id.textViewFileList);

        Button buttonReadFile = findViewById(R.id.buttonReadFile);
        buttonReadFile.setOnClickListener(v -> readFile());

        // Показать список файлов
        showFileList();
    }

    private void showFileList() {
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File filesDir = new File(storageDir, "Files");
        File[] files = filesDir.listFiles();
        if (files != null && files.length > 0) {
            StringBuilder fileListText = new StringBuilder();
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    fileListText.append(file.getName()).append("\n");
                }
            }
            textViewFileContent.setText(fileListText.toString());
        } else {
            textViewFileContent.setText("Нет доступных файлов");
        }
    }

    private void readFile() {
        String fileName = editTextFileName.getText().toString() + ".txt";
        StringBuilder fileContent = new StringBuilder();

        try {
            File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Files");
            File file = new File("Files", fileName);

            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }

            textViewFileContent.setText(fileContent.toString());

            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
