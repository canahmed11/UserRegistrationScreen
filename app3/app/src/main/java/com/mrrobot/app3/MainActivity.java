package com.mrrobot.app3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editTextNumber;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
            SharedPreferences küçük verileri kaydetmekte işe yarar.
            Bilgilerimiz kalıcı olarak veritabanında saklanır. Telefon kapatılıp açılsa bile veriler kaybolmaz.
            1.parametreye package ismini girdik, 2.parametrede ise bu veritabanına sadece bizim uygulamızdan ulaşılmasını belirttik.
         */
        sharedPreferences=this.getSharedPreferences("com.mrrobot.app3", Context.MODE_PRIVATE);
        textView=findViewById(R.id.textView);
        editTextNumber=findViewById(R.id.editTextNumber);

        /*
        Son olarak aşağıda kaydettiğimiz verileri burada almamız gerekiyor.
         */
        String kaydedilmisSayi=sharedPreferences.getString("kaydedilmisSayi","0");
        textView.setText("Your Age: "+kaydedilmisSayi);
    }

    public void save(View view){
        if(editTextNumber.getText().toString().matches("")){
         textView.setText("Lütfen bir sayı giriniz...");
        }else{
            String number=editTextNumber.getText().toString();
            textView.setText("Your Age: "+number);
            /*
            Yukarda sharedPreferences'i tanımladık fakat burada kullanmamız gerekiyor. Aksi halde kaydedilmez.
             */
            sharedPreferences.edit().putString("kaydedilmisSayi",number).apply();
        }

    }
    public void delete(View view){
        String kaydedilmisSayi=sharedPreferences.getString("kaydedilmisSayi","0");
        if(kaydedilmisSayi!="0"){
            sharedPreferences.edit().remove(kaydedilmisSayi).apply();
            textView.setText("Your Age: ");
        }
    }


}