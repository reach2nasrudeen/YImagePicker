package com.ypx.imagepickerdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.ypx.imagepicker.ImagePicker;
import com.ypx.imagepicker.bean.ImageItem;
import com.ypx.imagepicker.bean.MimeType;
import com.ypx.imagepicker.data.OnImagePickCompleteListener;
import com.ypx.imagepickerdemo.style.RedBookPresenter;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        AppCompatButton btnPicker = findViewById(R.id.btn_picker);
        btnPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPicker();
            }
        });
        AppCompatButton btnPickerSample = findViewById(R.id.btn_show_sample);
        btnPickerSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestActivity.this, MainActivity.class));
            }
        });
    }

    private void openPicker() {
        ImagePicker.withCrop(new RedBookPresenter())//设置presenter
                .setMaxCount(9)//设置选择数量
                .showCamera(true)//设置显示拍照
                .setColumnCount(4)//设置列数
                .mimeTypes(MimeType.ofImage())//设置需要加载的文件类型
                .filterMimeTypes(MimeType.GIF)//设置需要过滤掉的文件类型
                .assignGapState(false)//强制留白模式
                .setFirstImageItem(null)//设置上一次选中的图片
                .setFirstImageItemSize(1,1)//设置上一次选中的图片地址
                .pick(this, new OnImagePickCompleteListener() {
                    @Override
                    public void onImagePickComplete(ArrayList<ImageItem> items) {
                        //图片剪裁回调，主线程
                        //注意：剪裁回调里的ImageItem中getCropUrl()才是剪裁过后的图片地址
                    }
                });
    }
}
