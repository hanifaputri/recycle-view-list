package com.example.tugas5;

import android.graphics.drawable.Drawable;

public class Mahasiswa {
    private String _name;
    private String _nim;
    private String _major;
    private Drawable _photo;

    public Mahasiswa(String name, String nim, Drawable photo) {
        this._name = name;
        this._nim = nim;
        this._photo = photo;
        set_major(nim);
    }

    public String get_name() { return _name; }
    public String get_nim() {
        return _nim;
    }
    public String get_major() {
        return _major;
    }
    public Drawable get_photo() {
        return _photo;
    }

    public void set_name(String name) {
        this._name = _name;
    }
    public void set_nim(String nim) {
        this._nim = _nim;
    }
    public void set_photo(Drawable photo) {
        this._photo = photo;
    }

    private void set_major(String nim) {
        String batch = nim.substring(0,2);
        String minor = nim.substring(6,7);
        this._major = get_minor(minor) + " 20" + batch;
    }

    private String get_minor(String code){
        String minor;
        switch (code) {
            case "2": minor = "Teknik Informatika"; break;
            case "3": minor =  "Teknik Komputer"; break;
            case "4": minor =  "Sistem Informasi"; break;
            case "6": minor =  "Pendidikan Teknologi Informasi"; break;
            case "7": minor = "Teknologi Informasi"; break;
            default: minor = "Undefined"; break;
        }
        return minor;
    }
}
