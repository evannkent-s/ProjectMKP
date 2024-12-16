package id.ac.binus.project.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import id.ac.binus.project.R;

public class FormFragment extends Fragment {

    private EditText titleInput, priceInput, descriptionInput, imageUrlInput, categoryInput;
    private Button addButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form, container, false);

        // Inisialisasi Views
        titleInput = view.findViewById(R.id.input_title);
        priceInput = view.findViewById(R.id.input_price);
        descriptionInput = view.findViewById(R.id.input_description);
        imageUrlInput = view.findViewById(R.id.input_image_url);
        categoryInput = view.findViewById(R.id.input_category);
        addButton = view.findViewById(R.id.btn_add);

        // Tombol Tambahkan
        addButton.setOnClickListener(v -> {
            String title = titleInput.getText().toString();
            String price = priceInput.getText().toString();
            String description = descriptionInput.getText().toString();
            String imageUrl = imageUrlInput.getText().toString();
            String category = categoryInput.getText().toString();

            if (!title.isEmpty() && !price.isEmpty() && !description.isEmpty() && !imageUrl.isEmpty() && !category.isEmpty()) {
                // Kirim data ke API
                new SendDataToApiTask().execute(title, price, description, imageUrl, category);
            } else {
                Toast.makeText(getActivity(), "Semua field harus diisi!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    // AsyncTask untuk melakukan POST request di background thread
    private class SendDataToApiTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String title = params[0];
            String price = params[1];
            String description = params[2];
            String imageUrl = params[3];
            String category = params[4];

            try {
                // URL API Fake Store
                URL url = new URL("https://fakestoreapi.com/products");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // Buat JSON untuk data produk
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("title", title);
                jsonParam.put("price", Double.parseDouble(price));
                jsonParam.put("description", description);
                jsonParam.put("image", imageUrl);
                jsonParam.put("category", category);

                // Kirim data ke server
                OutputStream os = connection.getOutputStream();
                os.write(jsonParam.toString().getBytes());
                os.flush();
                os.close();

                int responseCode = connection.getResponseCode();
                Log.d("API_RESPONSE", "Response Code: " + responseCode);

                if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                    return "Produk berhasil ditambahkan!";
                } else {
                    return "Gagal menambahkan produk. Kode: " + responseCode;
                }

            } catch (Exception e) {
                Log.e("API_ERROR", "Error: " + e.getMessage());
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // Tampilkan hasil ke user
            Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
            Log.d("API_RESULT", result);
        }
    }
}
