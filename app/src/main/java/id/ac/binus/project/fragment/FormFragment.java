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

public class FormFragment extends Fragment {

    private EditText titleInput, priceInput, descriptionInput, imageUrlInput, categoryInput;
    private Button addButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form, container, false);

        // Inisialisasi View
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
                sendDataToApi(title, price, description, imageUrl, category);
            } else {
                Toast.makeText(getActivity(), "Semua field harus diisi!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void sendDataToApi(String title, String price, String description, String imageUrl, String category) {
        // Implementasi sederhana untuk API call
        Log.d("API_CALL", "Data dikirim ke API -> Title: " + title + ", Price: " + price
                + ", Description: " + description + ", Image URL: " + imageUrl + ", Category: " + category);
        Toast.makeText(getActivity(), "Data berhasil dikirim!", Toast.LENGTH_SHORT).show();
    }
}

