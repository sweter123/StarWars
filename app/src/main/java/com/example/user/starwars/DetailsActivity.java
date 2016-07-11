package com.example.user.starwars;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.birth)
    TextView birth;
    @BindView(R.id.gender)
    TextView gender;
    @BindView(R.id.height)
    TextView height;
    @BindView(R.id.mass)
    TextView mass;
    @BindView(R.id.eyescolor)
    TextView eyescolor;
    @BindView(R.id.hairscolor)
    TextView hairscolor;

    Person person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        person = (Person) getIntent().getExtras().getSerializable("Person");
        name.setText(person.getName());
        birth.setText(person.getBirthYear());
        gender.setText(person.getGender());
        height.setText(person.getHeight());
        mass.setText(person.getMass());
        eyescolor.setText(person.getEyeColor());
        hairscolor.setText(person.getHairColor());
    }
}
