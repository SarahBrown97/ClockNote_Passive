package com.example.passive_clocknote;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


    public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
        private ArrayList<ExampleItem> mExampleList;
        private OnItemClickListener mListener;
        public interface OnItemClickListener {
            void onGeneralClick(int position);
            void onDeleteClick(int position);
            void onVibrateClick(int position);
        }
        public void setOnItemClickListener(OnItemClickListener listener) {
            mListener = listener;
        }

        public static class ExampleViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextViewLine1;
            public TextView mTextViewLine2;
            public TextView mTextViewLine3;
            public TextView mTextViewLine4;
            public Button delete,general,vibrate;
            public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
                super(itemView);
                mTextViewLine1 = itemView.findViewById(R.id.textview_line1);
                mTextViewLine2 = itemView.findViewById(R.id.textview_line_2);
                mTextViewLine3 = itemView.findViewById(R.id.textview_line_3);
                mTextViewLine4 = itemView.findViewById(R.id.textview_line_4);
                delete=(Button) itemView.findViewById(R.id.delete);
                general=(Button) itemView.findViewById(R.id.general);
                vibrate=(Button) itemView.findViewById(R.id.vibrate);

                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                listener.onDeleteClick(position);

                            }
                        }
                    }
                });
                general.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                listener.onGeneralClick(position);
                            }
                        }
                    }
                });
                vibrate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                listener.onVibrateClick(position);
                            }
                        }
                    }
                });
            }
        }

        public ExampleAdapter(ArrayList<ExampleItem> exampleList) {
            mExampleList = exampleList;
        }

        @Override
        public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
            ExampleViewHolder evh = new ExampleViewHolder(v,mListener);
            return evh;
        }

        @Override
        public void onBindViewHolder(ExampleViewHolder holder, int position) {
            ExampleItem currentItem = mExampleList.get(position);

            holder.mTextViewLine1.setText(currentItem.getLine1());
            holder.mTextViewLine2.setText(currentItem.getLine2());
            holder.mTextViewLine3.setText(currentItem.getLine3());
            holder.mTextViewLine4.setText(currentItem.getLine4());
        }

        @Override
        public int getItemCount() {
            return mExampleList.size();
        }
    }

