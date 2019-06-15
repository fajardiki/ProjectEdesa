package com.example.projectedesa;

public class konfigurasi {

    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
    public static final String URL_ADD="http://192.168.1.44/Android/pegawai/tambahPgw.php";
    public static final String URL_GET_ALL = "http://192.168.1.44/Android/pegawai/tampilSemuaPgw.php";
    public static final String URL_GET_EMP = "http://192.168.1.44/Android/pegawai/tampilPgw.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.1.44/Android/pegawai/updatePgw.php";
    public static final String URL_DELETE_EMP = "http://192.168.1.44/Android/pegawai/hapusPgw.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAMA = "name";
    public static final String KEY_EMP_POSISI = "desg"; //desg itu variabel untuk posisi
    public static final String KEY_EMP_GAJIH = "salary"; //salary itu variabel untuk gajih

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "name";
    public static final String TAG_POSISI = "desg";
    public static final String TAG_GAJIH = "salary";

    //ID karyawan
    //emp itu singkatan dari Employee
    public static final String EMP_ID = "emp_id";

    // URL DATABASE R DESA
    public static final String URL_PENGUMUMAN_ALL = "http://192.168.100.11/GitHub/ProjectWebService/api/C_pengumuman";
    public static final String URL_PERMOHONAN_ONE = "http://192.168.100.11/GitHub/ProjectWebService/api/c_permohonan/";

    // JSON TAGS PENGUMUMAN
    public static final String TAG_ID_PENGUMUMAN = "id_pengumuman";
    public static final String TAG_JUDUL_PENGUMUMAN = "judul_pengumuman";
    public static final String TAG_ISI_PENGUMUMAN = "isi_pengumuman";
    public static final String TAG_GAMBAR_PENGUMUMAN = "gambar_pengumuman";
    public static final String TAG_WAKTU_PENGUMUMAN = "waktu_pengumuman";

    // JSON TAGS PENGAJUAN
    public static final String TAG_NIK_PENGAJUAN = "nik";
    public static final String TAG_NAMA_PENGAJUAN = "nama";
    public static final String TAG_NAMASURAT_PENGAJUAN = "nama_surat";
    public static final String TAG_TANGGAL_PENGAJUAN = "tanggal_pengajuan";
    public static final String TAG_STATUS_PENGAJUAN = "status_pengajuan";

    //emp itu singkatan dari Employee
    public static final String ID = "id";
}
