package com.example.tugas5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    private RecyclerView recycleView;
    private ArrayList<Mahasiswa> MahasiswaList;
    private RecycleViewAdapter adapter;

    int[] photo = {
        R.drawable.avatar_1,
        R.drawable.avatar_2,
        R.drawable.avatar_3,
        R.drawable.avatar_4,
        R.drawable.avatar_5,
        R.drawable.avatar_6,
        R.drawable.avatar_7,
        R.drawable.avatar_8,
        R.drawable.avatar_9,
        R.drawable.avatar_10,
        R.drawable.avatar_11,
        R.drawable.avatar_12
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MahasiswaList = new ArrayList<>();
        MahasiswaList.add(new Mahasiswa("Hanifa Putri Rahima", "195150700111032", getPhoto(4)));
        MahasiswaList.add(new Mahasiswa("Kembaran Hanifa", "195150200111001", getPhoto(8)));
        MahasiswaList.add(new Mahasiswa("Alizza Iman Raddin", "195150700111015", getPhoto(5)));
        MahasiswaList.add(new Mahasiswa("Ghina Zahirah", "195150700111018", getPhoto(11)));
        MahasiswaList.add(new Mahasiswa("Wendy Siregar", "185150600111024", getPhoto(12)));
        MahasiswaList.add(new Mahasiswa("Nam Do San", "185150200111048", getPhoto(9)));
        MahasiswaList.add(new Mahasiswa("Cha Eun Woo", "185150407111012", getPhoto(1)));
        MahasiswaList.add(new Mahasiswa("Tony Chen", "17515040711018", getPhoto(7)));
        MahasiswaList.add(new Mahasiswa("John Smith", "175150307111019", getPhoto(3)));
        MahasiswaList.add(new Mahasiswa("Peter Ling", "175150300111003", getPhoto(6)));
        MahasiswaList.add(new Mahasiswa("Richard Mark", "175150400111016", getPhoto(2)));

        recycleView = (RecyclerView) findViewById(R.id.rv_list);

        adapter = new RecycleViewAdapter(this, MahasiswaList);
        recycleView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recycleView.setLayoutManager(layoutManager);

        recycleView.setHasFixedSize(true);
        recycleView.setItemViewCacheSize(20);
        recycleView.setDrawingCacheEnabled(true);
        recycleView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private Drawable getPhoto (int type){
        return getResources().getDrawable(photo[type-1]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}