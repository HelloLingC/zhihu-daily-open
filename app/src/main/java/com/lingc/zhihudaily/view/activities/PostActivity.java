package com.lingc.zhihudaily.view.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lingc.zhihudaily.R;
import com.lingc.zhihudaily.bean.Post;
import com.lingc.zhihudaily.contract.NewPostContract;
import com.lingc.zhihudaily.presenter.PostPresenter;
import com.squareup.picasso.Picasso;

public class PostActivity extends AppCompatActivity implements NewPostContract.IPostView {

    private PostPresenter postPresenter;

    private CollapsingToolbarLayout collapsingToolbarLayout;

    private ImageView imageView;

    private WebView webView;

    private FloatingActionButton floatingActionButton;

    private TextView postTitleText;
    private TextView postImgSourceText;
    private TextView postSectionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Toolbar toolbar = findViewById(R.id.post_toolbar);
        setSupportActionBar(toolbar);
        collapsingToolbarLayout = findViewById(R.id.post_collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Loading");
        imageView = findViewById(R.id.post_actionbar_image);
        webView = findViewById(R.id.post_context_webview);
        floatingActionButton = findViewById(R.id.post_share_fab);
        postTitleText = findViewById(R.id.post_infotitle_text);
        postImgSourceText = findViewById(R.id.post_infoimg_text);
        postSectionText = findViewById(R.id.post_section_text);

        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setJavaScriptEnabled(true);

        Intent intent = getIntent();
        int postId = intent.getIntExtra("postId", 0);
        postPresenter = new PostPresenter(this);
        postPresenter.getPost(postId);
    }

    @Override
    public void onResult(final Post post) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                collapsingToolbarLayout.setTitle(post.getTitle());
                postTitleText.setText("文章标题：" + post.getTitle());
                postImgSourceText.setText("图片来源：" + post.getImageSource());
                Picasso.with(PostActivity.this).load(post.getImageUrl()).into(imageView);

                webView.loadDataWithBaseURL(null, post.getBody(), "text/html", "UTF-8", null);
                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(post.getShareUrl()));
                        startActivity(intent);
                    }
                });

                if (post.getSectionMessage().getId() == 0) {
                    return;
                }
                postSectionText.setVisibility(View.VISIBLE);
                postSectionText.setText("栏目：" + post.getSectionMessage().getName());
                postSectionText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PostActivity.this, SectionActivity.class);
                        intent.putExtra("id", post.getSectionMessage().getId());
                        intent.putExtra("name", post.getSectionMessage().getName());
                        startActivity(intent);
                    }
                });

            }
        });

    }
}
