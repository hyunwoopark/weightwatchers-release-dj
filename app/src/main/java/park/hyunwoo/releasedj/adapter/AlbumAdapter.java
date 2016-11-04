package park.hyunwoo.releasedj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import park.hyunwoo.releasedj.R;
import park.hyunwoo.releasedj.api.model.Album;

import static com.bumptech.glide.load.engine.DiskCacheStrategy.ALL;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private final OnItemClickListener listener;
    private List<Album> albums;

    public AlbumAdapter(List<Album> albums, OnItemClickListener listener) {
        this.albums = albums;
        this.listener = listener;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_list_item_albums, viewGroup, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder viewHolder, int i) {
        Context context = viewHolder.image.getContext();
        Album album = albums.get(i);

        Glide.with(context)
                .load(album.getImages().get(0).getUrl())
                .diskCacheStrategy(ALL)
                .error(R.mipmap.ic_launcher)
                .into(viewHolder.image);

        viewHolder.name.setText(album.getName());
        viewHolder.itemView.setOnClickListener(v -> listener.onItemClick(album.getId()));
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public boolean addAll(final List<Album> albums) {
        final boolean added = this.albums.addAll(albums);
        notifyItemRangeInserted(this.albums.size() - albums.size(), albums.size() + 1);
        return added;
    }

    public interface OnItemClickListener {
        void onItemClick(String albumId);
    }

    static class AlbumViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.album_image)
        ImageView image;
        @BindView(R.id.album_name)
        TextView name;

        AlbumViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}