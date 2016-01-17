package in.lovelacetech.pln.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.raizlabs.android.dbflow.list.FlowCursorList;

import in.lovelacetech.pln.R;
import in.lovelacetech.pln.vo.Example;

/**
 * Created by tioammar on 13/01/16.
 */
public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.SampleViewHolder> {

    public interface Listener {
        void onClickListener(String title);
    }

    private Listener mListener;
    private FlowCursorList<Example> mCursor;
    private Context mContext;

    public ExampleAdapter(Context context, Listener listener) {
        mContext = context;
        mListener = listener;
    }

    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (parent instanceof RecyclerView) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
            return new SampleViewHolder(view);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {
        Example example = mCursor.getItem(position);
        String text = "ID: " + example.id;
        holder.mTitle.setText(text);
    }

    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    public class SampleViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private final TextView mTitle;

        public SampleViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.example_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = getAdapterPosition();
            Example example = mCursor.getItem(id);
            mListener.onClickListener(example.text);
        }
    }

    public void swapCursor(FlowCursorList<Example> examples) {
        mCursor = examples;
        notifyDataSetChanged();
    }
}
