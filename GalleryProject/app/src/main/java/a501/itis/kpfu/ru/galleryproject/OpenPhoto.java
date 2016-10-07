package a501.itis.kpfu.ru.galleryproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class OpenPhoto extends AppCompatActivity {

    ImageView photo;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_photo);
        Intent intent = getIntent();
        photo = (ImageView) findViewById(R.id.photo);
        photo.setImageResource(intent.getIntExtra("photo_id", 0));
        btn = (Button) findViewById(R.id.btnbck);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
