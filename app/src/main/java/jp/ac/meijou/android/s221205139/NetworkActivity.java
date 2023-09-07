package jp.ac.meijou.android.s221205139;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Optional;

import jp.ac.meijou.android.s221205139.databinding.ActivityNetworkBinding;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkActivity extends AppCompatActivity {

    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<Gist> gistJsonAdapter = moshi.adapter(Gist.class);
    private ActivityNetworkBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNetworkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getImage("https://placehold.jp/00ff91/000000/680x500.png?text=X");

        binding.buttonGet.setOnClickListener(view -> {
            var text = binding.editTextText2.getText().toString();
            // textパラメータをつけたURL䛾作成
            var url = Uri.parse("https://placehold.jp/00ff91/000000/680x500.png")
                    .buildUpon()
                    .appendQueryParameter("text", text)
                    .build()
                    .toString();
            getImage(url);
        });


    }

    private void getImage(String url){
        var request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e){

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                var gist = gistJsonAdapter.fromJson(response.body().source());
//
//                Optional.ofNullable(gist)
//                        .map(g -> g.files.get("OkHttp.txt"))
//                        .ifPresent(gistFile -> {
//                            runOnUiThread(() -> binding.text.setText(gistFile.content));
//                        });
                var bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                runOnUiThread(() -> binding.imageView.setImageBitmap(bitmap));
            }
        });
    }


}