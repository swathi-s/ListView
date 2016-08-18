package com.example.user.adopters;

import android.content.Context;
import android.content.pm.LauncherActivityInfo;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView l;

    String[] days ={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

    String[] titles;
    String[] descriptions;

    int[] images = {R.drawable.ford,R.drawable.honda,R.drawable.jeep,R.drawable.moon_lander,R.drawable.porche,R.drawable.rust_bucket,R.drawable.toyoto};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        titles = res.getStringArray(R.array.titles);

        descriptions = res.getStringArray(R.array.descriptions);

        l = (ListView) findViewById(R.id.listView);

        MyAdopter adapter = new MyAdopter(this,titles,images,descriptions);

        l.setAdapter(adapter);

        /*
        ArrayAdapter<String> adopter = new ArrayAdapter<String>(this,R.layout.single_row,R.id.textView,days);
        l.setAdapter(adopter);
        l.setOnItemClickListener(this);*/
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView temp = (TextView) view;
        Toast.makeText(this, temp.getText()+""+position,Toast.LENGTH_SHORT).show();
    }
}

class MyAdopter extends ArrayAdapter<String>
{
    Context context;
    int[] images;
    String[] titles;
    String[] desc;
    MyAdopter(Context c, String[] titles, int imgs[],String[] desc)
    {
        super(c,R.layout.image_list,R.id.textView2,titles);
        this.context = c;
        this.images = imgs;
        this.titles = titles;
        this.desc = desc;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflator.inflate(R.layout.image_list,parent,false);
        ImageView myImage = (ImageView) row.findViewById(R.id.imageView2);
        TextView myTitle = (TextView) row.findViewById(R.id.textView2);
        TextView myDesc = (TextView) row.findViewById(R.id.textView3);

        myImage.setImageResource(images[position]);
        myTitle.setText(titles[position]);
        myDesc.setText(desc[position]);

        return row;
    }
}