package com.lingc.zhihudaily.view.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutItemOnClickAction;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.danielstone.materialaboutlibrary.util.OpenSourceLicense;
import com.lingc.zhihudaily.BuildConfig;
import com.lingc.zhihudaily.R;

import java.util.ArrayList;

public class AboutActivity extends MaterialAboutActivity {

    @NonNull
    @Override
    protected MaterialAboutList getMaterialAboutList(@NonNull Context context) {
        MaterialAboutCard.Builder aboutCard = new MaterialAboutCard.Builder();
        aboutCard.addItem(new MaterialAboutTitleItem.Builder()
                .icon(R.mipmap.ic_launcher)
                .text(R.string.app_name)
                .desc("Hello World")
                .build());
        aboutCard.addItem(new MaterialAboutActionItem.Builder()
                .text("Version")
                .subText(BuildConfig.VERSION_NAME)
                .icon(R.drawable.ic_info_black_24dp)
                .setOnClickAction(new MaterialAboutItemOnClickAction() {
                    @Override
                    public void onClick() {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/HelloLingC/zhihu-daily-open/blob/master/changelog.md"));
                        startActivity(intent);
                    }
                }).build());
        aboutCard.addItem(new MaterialAboutActionItem.Builder()
                .text("在GitHub上浏览本项目源代码")
                .subText("采用 GUN GPL 3.0 协议进行许可")
                .setOnClickAction(new MaterialAboutItemOnClickAction() {
                    @Override
                    public void onClick() {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/HelloLingC/zhihu-daily-open"));
                        startActivity(intent);
                    }
                })
                .build());

        MaterialAboutCard.Builder developerCard = new MaterialAboutCard.Builder()
                .title("开发者相关");
        developerCard.addItem(new MaterialAboutActionItem.Builder()
                .icon(R.drawable.ic_person_black_24dp)
                .text("LingC")
                .subText("Developer & Designer")
                .build());
        developerCard.addItem(new MaterialAboutActionItem.Builder()
                .icon(R.drawable.ic_link_black_24dp)
                .text("开发者的博客 - 咸鱼不咸")
                .subText("https://lcblog.cn")
                .setOnClickAction(new MaterialAboutItemOnClickAction() {
                    @Override
                    public void onClick() {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://lcblog.cn"));
                        startActivity(intent);
                    }
                })
                .build());
        developerCard.addItem(new MaterialAboutActionItem.Builder()
                .icon(R.drawable.ic_sms_black_24dp)
                .text("联系开发者")
                .subText("QQ: 61677516")
                .build());

        MaterialAboutCard PicassoLicenseCard = ConvenienceBuilder.createLicenseCard(context,
                context.getResources().getDrawable(R.drawable.ic_book_black_24dp), "Picasso", "2013", "square",
                OpenSourceLicense.APACHE_2);
        MaterialAboutCard okHttpLicenseCard = ConvenienceBuilder.createLicenseCard(context,
                context.getResources().getDrawable(R.drawable.ic_book_black_24dp), "OkHttp", "2019", "square",
                OpenSourceLicense.APACHE_2);
        MaterialAboutCard aboutLibraryLicenseCard = ConvenienceBuilder.createLicenseCard(context,
                context.getResources().getDrawable(R.drawable.ic_book_black_24dp), "material-about-library", "2016-2018", "daniel-stoneuk",
                OpenSourceLicense.APACHE_2);

        return new MaterialAboutList.Builder()
                .addCard(aboutCard.build())
                .addCard(developerCard.build())
                .addCard(PicassoLicenseCard)
                .addCard(okHttpLicenseCard)
                .addCard(aboutLibraryLicenseCard)
                .build();
    }

    @Nullable
    @Override
    protected CharSequence getActivityTitle() {
        return "关于";
    }
}
