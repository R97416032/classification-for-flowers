package com.example.androidtest.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.androidtest.R;
import java.util.List;

public class ResultAdapt extends ArrayAdapter<Result> {
    private int resourceId;

    public ResultAdapt(Context context, int textViewResourceId,
                       List<Result> objects) {
        super(context,  textViewResourceId, objects);
        resourceId=textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Result result=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView resultImage=(ImageView)view.findViewById(R.id.result_image);
        TextView resultName=(TextView)view.findViewById(R.id.result_name);
        TextView resultScore=(TextView)view.findViewById(R.id.result_socre);
        resultImage.setImageResource(result.getImageId());
        resultName.setText(result.getName());
        resultScore.setText(result.getScore());
        return view;
    }
}
