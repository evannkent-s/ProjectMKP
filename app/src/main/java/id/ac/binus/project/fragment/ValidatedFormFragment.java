package id.ac.binus.project.fragment;

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

import id.ac.binus.project.R;

public class ValidatedFormFragment extends Fragment {

    private EditText titleInput, priceInput, descriptionInput, imageUrlInput, categoryInput;
    private Button addButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_validated_form, container, false);

        // Inisialisasi View
        titleInput = view.findViewById(R.id.input_title);
        priceInput = view.findViewById(R.id.input_price);
        descriptionInput = view.findViewById(R.id.input_description);
        imageUrlInput = view.findViewById(R.id.input_image_url);
        categoryInput = view.findViewById(R.id.input_category);
        addButton = view.findViewById(R.id.btn_add);

        // Tombol Tambahkan
        addButton.setOnClickListener(v -> {
            if (validateInputs()) {
                String title = titleInput.getText().toString();
                String price = priceInput.getText().toString();
                String description = descriptionInput.getText().toString();
                String imageUrl = imageUrlInput.getText().toString();
                String category = categoryInput.getText().toString();

                sendDataToApi(title, price, description, imageUrl, category);
            }
        });

        return view;
    }

    private boolean validateInputs() {
        // Validasi Judul
        if (!titleInput.getText().toString().matches("[a-zA-Z0-9 ]{1,16}")) {
            titleInput.setError("Judul hanya boleh alfanumerik, maksimal 16 karakter.");
            return false;
        }

        // Validasi Harga
        String priceText = priceInput.getText().toString();
        if (!priceText.matches("[0-9]+")) {
            priceInput.setError("Harga harus berupa angka.");
            return false;
        }
        int price = Integer.parseInt(priceText);
        if (price < 10000 || price > 1000000) {
            priceInput.setError("Harga harus antara Rp 10.000 - Rp 1.000.000.");
            return false;
        }

        // Validasi Deskripsi
        if (!descriptionInput.getText().toString().matches("[a-zA-Z0-9 ]{1,50}")) {
            descriptionInput.setError("Deskripsi hanya boleh alfanumerik, maksimal 50 karakter.");
            return false;
        }

        // Validasi URL Gambar
        String imageUrl = imageUrlInput.getText().toString();
        if (imageUrl.length() > 100 || !imageUrl.startsWith("https://")) {
            imageUrlInput.setError("URL harus mengandung https:// dan maksimal 100 karakter.");
            return false;
        }

        // Validasi Kategori
        if (!categoryInput.getText().toString().matches("[a-zA-Z0-9 ]{1,20}")) {
            categoryInput.setError("Kategori hanya boleh alfanumerik, maksimal 20 karakter.");
            return false;
        }

        return true;
    }

    private void sendDataToApi(String title, String price, String description, String imageUrl, String category) {
        // Implementasi API call
        Log.d("API_CALL", "Data berhasil divalidasi dan dikirim -> Title: " + title + ", Price: " + price
                + ", Description: " + description + ", Image URL: " + imageUrl + ", Category: " + category);
        Toast.makeText(getActivity(), "Data berhasil dikirim!", Toast.LENGTH_SHORT).show();
    }
}

