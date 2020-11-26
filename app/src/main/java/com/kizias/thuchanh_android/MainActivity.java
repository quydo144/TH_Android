package com.kizias.thuchanh_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button exit, save, delete, update;
    EditText e_masp, e_tensp, e_soluong, e_dongia;
    Spinner spinner_sp;
    ListView list_item_sp;
    SanPhamSQLite sqLite;
    ArrayList<SanPham> listSp;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        SelectSanPham_ListView();
        Exit_Click();
        AddSanPham_Click();
        Click_ItemListView();
        Delete_Click();
        Update_Click();
        Load_Data_Spinner();
    }

    protected void Init() {
        sqLite = new SanPhamSQLite(MainActivity.this);
        exit = (Button) findViewById(R.id.exit);
        save = (Button) findViewById(R.id.save);
        delete = (Button) findViewById(R.id.delete);
        update = (Button) findViewById(R.id.update);
        e_masp = (EditText) findViewById(R.id.masp);
        e_tensp = (EditText) findViewById(R.id.tensp);
        e_soluong = (EditText) findViewById(R.id.soluong);
        e_dongia = (EditText) findViewById(R.id.dongia);
        spinner_sp = (Spinner) findViewById(R.id.spinner_sp);
        list_item_sp = (ListView) findViewById(R.id.list_item_sp);
    }

    protected void Exit_Click() {
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Thông báo");
                alert.setMessage("Bạn có muốn thoát ứng dụng hay không?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
    }

    protected void Click_ItemListView(){
        list_item_sp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SanPham sp = listSp.get(position);
                e_masp.setText(Integer.toString(sp.getMasp()));
                e_tensp.setText(sp.getTensp());
                e_soluong.setText(Integer.toString(sp.getSoluong()));
                e_dongia.setText(Double.toString(sp.getDongia()));
            }
        });
    }

    protected void AddSanPham_Click() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int masp = Integer.parseInt(e_masp.getText().toString().trim());
                String tensp = e_tensp.getText().toString().trim();
                int soluong = Integer.parseInt(e_soluong.getText().toString().trim());
                double dongia = Double.parseDouble(e_dongia.getText().toString().trim());
                SanPham sp = new SanPham(masp, tensp, soluong, dongia);
                if (listSp.contains(sp)){
                    Toast.makeText(MainActivity.this, "Trùng mã", Toast.LENGTH_SHORT).show();
                    return;
                }
                sqLite.AddSanPham(sp);
                SelectSanPham_ListView();
                ClearEdiText();
            }
        });
    }

    protected void SelectSanPham_ListView(){
        listSp = sqLite.getAllSanPham();
        adapter = new Adapter(MainActivity.this, listSp);
        list_item_sp.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    protected void Delete_Click(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!e_masp.getText().toString().equals("")){
                    int masp = Integer.parseInt(e_masp.getText().toString());
                    if (masp == 0)
                        return;
                    sqLite.DeleteSanPham(masp);
                    SelectSanPham_ListView();
                    ClearEdiText();
                }
            }
        });
    }

    protected void Update_Click(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int masp = Integer.parseInt(e_masp.getText().toString().trim());
                String tensp = e_tensp.getText().toString().trim();
                int soluong = Integer.parseInt(e_soluong.getText().toString().trim());
                double dongia = Double.parseDouble(e_dongia.getText().toString().trim());
                SanPham sp = new SanPham(masp, tensp, soluong, dongia);
                sqLite.UpdateSanPham(sp);
                SelectSanPham_ListView();
                ClearEdiText();
            }
        });
    }

    protected void ClearEdiText(){
        e_dongia.setText("");
        e_masp.setText("");
        e_soluong.setText("");
        e_tensp.setText("");
    }

    protected void Load_Data_Spinner(){
        String[] dsSp = {"Đồ điện tử", "Đồ gia dụng", "Linh kiện máy tính"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dsSp);
        spinner_sp.setAdapter(adapter);

        spinner_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("gia tri duoc chon", dsSp[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("gia tri khong chon", dsSp[0]);
            }
        });
    }
}