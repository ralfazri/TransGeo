package com.example.transgeo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.transgeo.R;
import com.example.transgeo.adapter.TokohAdapterHorizontal;
import com.example.transgeo.dtobject.dtTokoh;
import com.example.transgeo.object.GlobalVar;
import com.example.transgeo.object.Tokoh;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private View popupView;
    private Dialog popDialogVerif;

    private RecyclerView rvTokoh;
    private ArrayList<Tokoh> listTokoh = new ArrayList<>();

    private Button btnLihatSemua;
    private ImageButton btnRefleksi, btnRotasi, btnDilatasi, btnTranslasi, btnSoalEasy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvTokoh = findViewById(R.id.rv_tokoh_horizontal);
        toolbar = findViewById(R.id.tb_main_activity);
        btnLihatSemua = findViewById(R.id.btn_lihat_semua_tokoh);
        btnRefleksi = findViewById(R.id.btn_refleksi);
        btnRotasi = findViewById(R.id.btn_rotasi);
        btnDilatasi = findViewById(R.id.btn_dilatasi);
        btnTranslasi = findViewById(R.id.btn_translasi);
        btnSoalEasy = findViewById(R.id.btn_soal_easy);

        navigationView = findViewById(R.id.nav_drawer);
        drawerLayout = findViewById(R.id.drawer_layout);

        rvTokoh.setHasFixedSize(true);

        listTokoh.addAll(dtTokoh.getData());

        popDialogVerif = new Dialog(this);

        showRvTokoh();
        setUpNavigationView();

        btnLihatSemua.setOnClickListener(this);
        btnRefleksi.setOnClickListener(this);
        btnRotasi.setOnClickListener(this);
        btnDilatasi.setOnClickListener(this);
        btnTranslasi.setOnClickListener(this);
        btnSoalEasy.setOnClickListener(this);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){

                case R.id.nav_draw_kalkulator:
                    startActivity(new Intent(getApplicationContext(), CalculatorActivity.class));
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return false;

                case R.id.nav_draw_ujian:
                    sendPaketSoal(GlobalVar.PILIHAN_SOAL, GlobalVar.SOAL_UJIAN);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return false;

            }
            return false;
        });
    }


    private void showRvTokoh() {
        rvTokoh.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        TokohAdapterHorizontal tokohAdapterHorizontal = new TokohAdapterHorizontal(listTokoh, getApplicationContext());
        rvTokoh.setAdapter(tokohAdapterHorizontal);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_lihat_semua_tokoh:
                startActivity(new Intent(getApplicationContext(),ListTokohActivity.class));
                break;
            case R.id.btn_refleksi:
                startActivity(new Intent(getApplicationContext(),RefleksiActivity.class));
                break;
            case R.id.btn_rotasi:
                startActivity(new Intent(getApplicationContext(),RotasiActivity.class));
                break;
            case R.id.btn_dilatasi:
                startActivity(new Intent(getApplicationContext(),DilatasiActivity.class));
                break;
            case R.id.btn_translasi:
                startActivity(new Intent(getApplicationContext(),TranslasiActivity.class));
                break;

            case R.id.btn_soal_easy:
                sendPaketSoal(GlobalVar.PILIHAN_SOAL, GlobalVar.SOAL_EASY);
                break;

            case R.id.btn_soal_medium:
                sendPaketSoal(GlobalVar.PILIHAN_SOAL, GlobalVar.SOAL_EASY);
                break;

            case R.id.btn_soal_hard:
                sendPaketSoal(GlobalVar.PILIHAN_SOAL, GlobalVar.SOAL_EASY);
                break;
        }
    }

    @Override
    public void onBackPressed() {

        Button btnTidak, btnKeluar;
        DisplayMetrics metrics = getResources().getDisplayMetrics();

        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        LayoutInflater inflater = this.getLayoutInflater();

        popupView = inflater.inflate(R.layout.popup_verifikasi_keluar,null);
        btnTidak = popupView.findViewById(R.id.btn_popup_verif_tidak);
        btnKeluar = popupView.findViewById(R.id.btn_popup_verif_keluar);

        btnTidak.setOnClickListener(view1 -> {
            popDialogVerif.dismiss();
        });

        btnKeluar.setOnClickListener(view1 -> {
            popDialogVerif.dismiss();
            finish();
        });


        popDialogVerif.setContentView(popupView);
        popDialogVerif.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popDialogVerif.getWindow().setLayout((6*width)/7, (4*height)/5);
        popDialogVerif.show();

    }

    private void sendPaketSoal(String pilihanSoal, String soalEasy) {
        Intent intent = new Intent(getApplicationContext(), SoalActivity.class);
        intent.putExtra(pilihanSoal, soalEasy);
        startActivity(intent);
    }
}
