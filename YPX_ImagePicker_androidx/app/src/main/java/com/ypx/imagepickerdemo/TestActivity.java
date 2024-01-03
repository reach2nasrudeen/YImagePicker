package com.ypx.imagepickerdemo;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;
import com.ypx.imagepicker.ImagePicker;
import com.ypx.imagepicker.bean.ImageItem;
import com.ypx.imagepicker.bean.MimeType;
import com.ypx.imagepicker.bean.selectconfig.MultiSelectConfig;
import com.ypx.imagepicker.data.OnImagePickCompleteListener;
import com.ypx.imagepickerdemo.style.RedBookPresenter;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    private ArrayList<ImageItem> selectedImages = new ArrayList<>();
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

        findViewById(R.id.iv_result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.preview(TestActivity.this, new RedBookPresenter(), selectedImages, 0, new OnImagePickCompleteListener() {
                    @Override
                    public void onImagePickComplete(ArrayList<ImageItem> items) {

                    }
                });
            }
        });
    }

    private void openPicker() {
        ImagePicker.withCrop(new RedBookPresenter())//设置presenter
                .setMaxCount(9)//设置选择数量
                .showCamera(true)//设置显示拍照
                .setColumnCount(4)//设置列数
//                .setCropRatio(1, 1)

//                .cropStyle(MultiSelectConfig.STYLE_GAP)
                .mimeTypes(MimeType.ofImage())//设置需要加载的文件类型
                .filterMimeTypes(MimeType.GIF)//设置需要过滤掉的文件类型
                .assignGapState(true)//强制留白模式
                .setFirstImageItem(null)//设置上一次选中的图片
                .setFirstImageItemSize(1, 1)//设置上一次选中的图片地址
                .pick(this, new OnImagePickCompleteListener() {
                    @Override
                    public void onImagePickComplete(ArrayList<ImageItem> items) {
                        selectedImages = items;
                        //图片剪裁回调，主线程
                        //注意：剪裁回调里的ImageItem中getCropUrl()才是剪裁过后的图片地址
                        if (!items.isEmpty()) {
                            displayImage(items.get(0));
                        }
                    }
                });
    }

    private void displayImage(ImageItem imageItem) {
        ImageView ivResult = findViewById(R.id.iv_result);
        if (imageItem.getCropUrl() != null && imageItem.getCropUrl().length() > 0) {
            Glide.with(this).load(imageItem.getCropUrl()).into(ivResult);
        } else {
            if (imageItem.getUri() != null) {
                Glide.with(this).load(imageItem.getUri()).into(ivResult);
            } else {
                Glide.with(this).load(imageItem.path).into(ivResult);
            }
        }
    }
}
