package com.kotlin.a05_pictureframe

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private val addPhotoButon : Button by lazy {
        findViewById<Button>(R.id.addPhotoButon)
    }
    private val startPhotoFrameModeButton : Button by lazy {
        findViewById<Button>(R.id.startPhotoFrameModeButton)
    }
    private val listImageView : List<ImageView> by lazy{
        mutableListOf<ImageView>().apply{
            add(findViewById(R.id.imageView11))
            add(findViewById(R.id.imageView12))
            add(findViewById(R.id.imageView13))
            add(findViewById(R.id.imageView21))
            add(findViewById(R.id.imageView22))
            add(findViewById(R.id.imageView23))
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAddPhotoButton()//버튼을 초기화 해준다. 한눈에 보기좋게 fun 으로 뺀다.
        initStartPhotoFrameModeButton()
    }

    private fun initAddPhotoButton(){
        addPhotoButon.setOnClickListener {
            when{
                //권한이 등록되어 있는가?
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED ->{ //권한이 잘 부여되어 있음.
                    //기능

                }
                // 권한이 거절되었을 때 교육용 팝업을 띄울 필요가 있는지 체크
                // boolean
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE) ->{
                    // 교육용 팝업 확인 후 권한팝업을 띄우는 기능
                    showPermissionContextPopup()
                }
                else ->{
                    requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1000)
                }
            }

        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1000 ->{
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //true : 권한이 부여됨
                    //기능 구현
                    navigatePhotos()
                }else{
                    Toast.makeText(this,"권한을 거부하셨습니다. ",Toast.LENGTH_SHORT).show()
                }
            }
            else ->{}

        }
    }
    private fun navigatePhotos(){

    }


    private fun showPermissionContextPopup(){
        AlertDialog.Builder(this)
            .setTitle("권한이 필요합니다.")
            .setMessage("전자액자에 앱에서 사지을 불러오기 위해 권한이 필요합니다. ")
            .setPositiveButton("동의하기") { _, _ ->
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)
            }
                .setNegativeButton("취소하기") { _, _ -> }
                .create()
                .show()
    }
    private fun initStartPhotoFrameModeButton(){

    }
}