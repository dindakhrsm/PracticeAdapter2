package com.example.acer.practiceadapter.com.example.acer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.acer.practiceadapter.R;
import com.example.acer.practiceadapter.com.example.acer.user.Teacher;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by ACER on 13/10/2016.
 */
public class TeacherArrayAdapter extends ArrayAdapter<Teacher> {

    public TeacherArrayAdapter(Context context, ArrayList<Teacher> teacherArrayList) {
        super(context, 0, teacherArrayList);
    }

    public View getView(int position, View view, ViewGroup parent){
        Teacher teacher = getItem(position);
        if(view==null){
            view= LayoutInflater.from(getContext()).inflate(R.layout.teacher_instance,parent,false);
        }

        TextView idView = (TextView) view.findViewById(R.id.teacher_id);
        TextView nipView = (TextView) view.findViewById(R.id.teacher_nip);
        TextView nameView = (TextView) view.findViewById(R.id.teacher_name);
        TextView phoneView = (TextView) view.findViewById(R.id.teacher_email);
        TextView mailView = (TextView) view.findViewById(R.id.teacher_phone);

        //set content
        idView.setText(teacher.getId() + "");
        nipView.setText(teacher.getNip());
        nameView.setText(teacher.getName());
        mailView.setText(teacher.getMail());
        phoneView.setText(teacher.getPhone());
        return view;
    }
}
