package a501.itis.kpfu.ru.galleryproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import static a501.itis.kpfu.ru.galleryproject.R.drawable.j;
import static a501.itis.kpfu.ru.galleryproject.R.drawable.p;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerList;
    private GalleryAdapter mAdapter;
    public int[] imageNames = {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
            R.drawable.i,
            j,
            R.drawable.k,
            R.drawable.l,
            R.drawable.m,
            R.drawable.n,
            R.drawable.o,
            p,
            R.drawable.q
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerList = (RecyclerView) findViewById(R.id.recycler_list);
        mRecyclerList.setHasFixedSize(true);
        mRecyclerList.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new GalleryAdapter(getApplicationContext());
        mRecyclerList.setAdapter(mAdapter);

    }

    private class GalleryAdapter extends RecyclerView.Adapter<GalleryViewHolder> {
        private Context mContext;

        public GalleryAdapter (Context context) {
            mContext = context;
        }

        public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);
            return new GalleryViewHolder(view);
        }

        public void onBindViewHolder(GalleryViewHolder holder, final int position) {
            System.out.println(imageNames[position]);
            holder.photo.setImageResource(imageNames[position]);
            Picasso.with(mContext).load(imageNames[position]).resize(400, 400).centerCrop().into(holder.photo);
            holder.photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, OpenPhoto.class);
                    intent.putExtra("photo_id", imageNames[position]);
                    startActivity(intent);
                }
            });
        }

        public int getItemCount() {
            return imageNames.length;
        }
    }

    private class GalleryViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;

        public GalleryViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
