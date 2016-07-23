package com.example.kunalsingh.game;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    long timespan = 0;
    int tom = 1;
    int jerry = 0;
    int jerrytmp = 0;
    int counter = 0;
    int k = 0;
    int j=0;
    private static final String TAG = "MainActivity";
    SensorManager manager;
    GridView gridView;
    boolean status = false;
    ArrayList<Integer> arrayList = new ArrayList<>(9);
    ArrayListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList.add(0, R.drawable.runingjerry);
        arrayList.add(1, R.drawable.runningtom);
        for (int i = 2; i < 9; i++)
            arrayList.add(R.drawable.white);
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
        gridView = (GridView) findViewById(R.id.grid_view);
        adapter = new ArrayListAdapter(arrayList);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public class ArrayListAdapter extends BaseAdapter {

        ArrayList<Integer> arrayList2;

        public ArrayListAdapter(ArrayList<Integer> arrayList2) {
            this.arrayList2 = arrayList2;
        }

        @Override
        public int getCount() {
            return arrayList2.size();
        }

        @Override
        public Integer getItem(int i) {
            return arrayList2.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView img;
            LayoutInflater li = getLayoutInflater();
            view = li.inflate(R.layout.image, null);
            img = (ImageView) view.findViewById(R.id.img);
            img.setImageResource(arrayList2.get(i));
            return view;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        /*if(timespan == 0)
            timespan = sensorEvent.timestamp;

        if(sensorEvent.timestamp > timespan + 1000*1000*1000) {
            Log.d(TAG, "x : " + sensorEvent.values[0]);
            Log.d(TAG, "y :" + sensorEvent.values[1]);
            Log.d(TAG, "z : " + sensorEvent.values[2]);
            timespan = sensorEvent.timestamp;
        }*/

    //    Toast.makeText(MainActivity.this, "see : "+tom, Toast.LENGTH_SHORT).show();
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];
        if (timespan == 0)
            timespan = sensorEvent.timestamp;

        if (sensorEvent.timestamp > (timespan + 9*1000 * 1000 * 1000)) {
            //    Log.d(TAG,"check  "+x);
            if ((x >= 5 && x <= 10)) {
                k++;
                Log.d(TAG,"tims : "+" "+k);
                causeChange(1);
            } else if ((x <= -5 && x >= -10)) {
                //   Log.d(TAG, "coming");
                k++;
                Log.d(TAG,"tims : "+" "+k);
                causeChange(2);
            } else if ( ((y >= 5) && (y <= 10))) {
                k++;
                Log.d(TAG,"tims : "+" "+k);
                causeChange(3);
            } else if ( ((y <= -5) && (y >= -10))) {
                k++;
                Log.d(TAG,"tims : "+" "+k);
                causeChange(4);
            }
        timespan = sensorEvent.timestamp;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void causeChange(int cat) {

        switch (cat) {
            case 1:
                if (tom % 3 == 0) {
                    // Toast.makeText(MainActivity.this, "Wrong Move Try Up or Bottom", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(TAG,"check"+" "+"1");
                    arrayList.set(tom--,R.drawable.white);
                    if (tom == jerry) {
                        Log.d(TAG,"Tom : "+tom+" "+"Jerry : "+jerry);
                        counter++;
                        counter = counter % 9;
                        jerry = posjerry(tom, counter);
                        Log.d(TAG,"check"+" "+"2");
                        arrayList.set(jerry,R.drawable.runingjerry);
                    }
                //    arrayList.add(tom, R.drawable.runningtom);
                    Log.d(TAG,"check"+" "+"3");
                    arrayList.set(tom,R.drawable.runningtom);
                    adapter.notifyDataSetChanged();
                    Log.d(TAG, "called 1 "+tom+" "+counter);
                }
                break;

            case 2:
                if ((tom + 1) % 3 == 0) {
                    // Toast.makeText(MainActivity.this, "Wrong Move Try Up or Bottom", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(TAG,"check"+" "+"4");
                    arrayList.set(tom++,R.drawable.white);
                    if (tom == jerry) {
                        Log.d(TAG,"Tom : "+tom+" "+"Jerry : "+jerry);
                        counter++;
                        counter = counter % 9;
                        jerry = posjerry(tom, counter);
                        Log.d(TAG,"check"+" "+"5");
                        arrayList.set(jerry,R.drawable.runingjerry);
                    }
                   // arrayList.add(tom, R.drawable.runningtom);
                    Log.d(TAG,"check"+" "+"6");
                    arrayList.set(tom,R.drawable.runningtom);
                    adapter.notifyDataSetChanged();
                    Log.d(TAG, "called 2 "+tom+" "+counter);
                }
                break;
            case  3: if((tom+3)>=9){
            }
                else{
                Log.d(TAG,"check"+" "+"7");
                arrayList.set(tom,R.drawable.white);
                tom = tom+3;
                if (tom == jerry) {
                    Log.d(TAG,"Tom : "+tom+" "+"Jerry : "+jerry);
                    counter++;
                    counter = counter % 9;
                    jerry = posjerry(tom, counter);
                    Log.d(TAG,"check"+" "+"8");
                    arrayList.set(jerry,R.drawable.runingjerry);
                }
             //   arrayList.add(tom, R.drawable.runningtom);
                Log.d(TAG,"check"+" "+"9");
                arrayList.set(tom,R.drawable.runningtom);
                adapter.notifyDataSetChanged();
            Log.d(TAG,"called 3 "+tom+" "+counter);
            }
                break;
            case 4: if((tom-3)<0){
        }
                else{
                Log.d(TAG,"check"+" "+"10");
                arrayList.set(tom--,R.drawable.white);
                tom = tom-3;
                if (tom == jerry) {
                    Log.d(TAG,"Tom : "+tom+" "+"Jerry : "+jerry);
                    counter++;
                    counter = counter % 9;
                    jerry = posjerry(tom, counter);
                    Log.d(TAG,"check"+" "+"11");
                    arrayList.set(jerry,R.drawable.runingjerry);
                }
              //  arrayList.add(tom, R.drawable.runningtom);
                Log.d(TAG,"check"+" "+"12");
                arrayList.set(tom,R.drawable.runningtom);
                adapter.notifyDataSetChanged();
                Log.d(TAG,"called 4 "+tom+" "+counter);

            }
                break;
            }
    }
    public int posjerry(int tom2, int counter2){
        int a = (int) (Math.pow(tom2,2)*(counter2+j));
        int b = (int) (counter2+j)*(tom2);
        int c = (int) (counter2+j);
        int jer = a+b+c;
        if((jer%9)==tom2) {
            j=j++;
            j=j%9;
            return (jer+1)%9;
        }
            Log.d(TAG, "check position " + (jer % 9));
            return (jer % 9);

        }

}
