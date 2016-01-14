package in.lovelacetech.pln.ui;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import in.lovelacetech.pln.R;
import in.lovelacetech.pln.provider.Contract;
import in.lovelacetech.pln.vo.Example;

/**
 * Created by tioammar on 13/01/16.
 */
public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.SampleViewHolder> {

    public interface Listener {
        void onClickListener(String title);
    }

    private Listener mListener;
    private Cursor mCursor;
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
        if (mCursor.moveToPosition(position)) {
            holder.mTitle.setText(mCursor.getString(Example.TEXT));
        }
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
            int position = getAdapterPosition();
            mCursor.moveToPosition(position);
            int columnId = mCursor.getColumnIndex(Contract.ExampleColumn.COLUMN_TEXT);
            mListener.onClickListener(mCursor.getString(columnId));
        }
    }

    public void swapCursor(Cursor cursor) {
        mCursor = cursor;
        notifyDataSetChanged();
    }
}
