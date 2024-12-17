package id.ac.binus.project.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import id.ac.binus.project.R;

public class ValidatedFormFragment extends Fragment {

    private EditText titleInput, priceInput, descriptionInput, imageUrlInput, categoryInput;
    private TextView descriptionCounter;
    private Button addButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_validated_form, container, false);

        // Initialize Views
        titleInput = view.findViewById(R.id.input_title);
        priceInput = view.findViewById(R.id.input_price);
        descriptionInput = view.findViewById(R.id.input_description);
        descriptionCounter = view.findViewById(R.id.description_counter);
        imageUrlInput = view.findViewById(R.id.input_image_url);
        categoryInput = view.findViewById(R.id.input_category);
        addButton = view.findViewById(R.id.btn_add);

        // Real-time character counter for description
        descriptionInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int remaining = 50 - s.length();
                descriptionCounter.setText("Sisa karakter: " + remaining);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Add Button Listener
        addButton.setOnClickListener(v -> {
            if (validateInputs()) {
                Toast.makeText(getActivity(), "Validasi Berhasil, Data Siap Dikirim!", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    private boolean validateInputs() {
        String title = titleInput.getText().toString();
        String price = priceInput.getText().toString();
        String description = descriptionInput.getText().toString();
        String imageUrl = imageUrlInput.getText().toString();
        String category = categoryInput.getText().toString();

        if (!title.matches("^[a-zA-Z0-9 ]{1,16}$")) {
            titleInput.setError("Judul hanya alfanumerik, max 16 karakter");
            return false;
        }

        if (!price.matches("^Rp \\d+(\\.\\d{3})*$")) {
            priceInput.setError("Format harga salah, contoh: Rp 10.000");
            return false;
        }

        if (!description.matches("^[a-zA-Z0-9 ]{1,50}$")) {
            descriptionInput.setError("Deskripsi max 50 karakter, alfanumerik");
            return false;
        }

        if (!Patterns.WEB_URL.matcher(imageUrl).matches() || !imageUrl.startsWith("https://")) {
            imageUrlInput.setError("URL harus valid dan mulai dengan https://");
            return false;
        }

        if (!category.matches("^[a-zA-Z0-9 ]{1,20}$")) {
            categoryInput.setError("Kategori hanya alfanumerik, max 20 karakter");
            return false;
        }

        return true;
    }
}
