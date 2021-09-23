package com.example.myapplication;
import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {
    private FragmentSecondBinding binding;
    Button btn;
    EditText titre;
    EditText noteMusique;
    EditText noteReal;
    EditText NoteScenario;
    EditText hour;
    EditText avisDetaillé;

    public void SQLiteDataBaseBuild(){
        sqLiteDatabaseObj = sqLiteDatabaseObj.openOrCreateDatabase("AndroidJSonDataBase", null);

    }
    public void SQLiteTableBuild(String sah){
        sqLiteDatabaseObj.execSQL(sah);
    }
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this).navigate(R.id.action_SecondFragment_to_FirstFragment);
                Editable title = (titre.getText());
                Editable musique = (noteMusique.getText());;
                Editable real = (noteReal.getText());;
                Editable scenario = (NoteScenario.getText());;
                Editable date = (hour.getText());;
                Editable avis = (avisDetaillé.getText());;
                SQLiteDataBaseBuild();
                String sah = "CREATE TABLE IF NOT EXISTS AndroidJSonTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+title+" VARCHAR, "+musique+" VARCHAR,"+real+" VARCHAR,"+scenario+" VARCHAR,"+date+" VARCHAR,"+avis+" VARCHAR);";
                SQLiteTableBuild(sah);
                SQLiteDataBaseQueryHolder = "INSERT INTO AndroidJSonTable (title,musique,real,scenario,date,avis) VALUES('" + title + "', '" + musique + "','" + real + "','" + scenario + "','" + date + "','" + avis+ "');";
                sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}