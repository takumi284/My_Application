package jp.ac.meijou.android.s221205139;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Optional;

import jp.ac.meijou.android.s221205139.databinding.ActivitySubBinding;

public class SubActivity extends AppCompatActivity {

    private ActivitySubBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        String text = getIntent()

        Optional.ofNullable(getIntent().getStringExtra("text"))
                .ifPresent(text -> binding.textViewR.setText(text));

        binding.buttonReturn.setOnClickListener(view -> {
            var intent = new Intent(this, MainActivity3.class);
            startActivity(intent);
        });

        binding.buttonOk.setOnClickListener(view -> {
            var intent = new Intent();
            intent.putExtra("ret", "meijo");
            setResult(RESULT_OK, intent);
            finish();
        });

        binding.buttonCancel.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}