package com.example.user.starwars;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.user.starwars.mvp.contract.DetailsContract;
import com.example.user.starwars.mvp.presenter.DetailsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.View {

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


    DetailsContract.Presenter presenter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        presenter = new DetailsPresenter(getIntent(), this);
        presenter.getData();
    }

    @Override
    public void onDataLoaded(Person item) {
        name.setText(item.getName());
        birth.setText(item.getBirthYear());
        gender.setText(item.getGender());
        height.setText(item.getHeight());
        mass.setText(item.getMass());
        eyescolor.setText(item.getEyeColor());
        hairscolor.setText(item.getHairColor());
    }
}
