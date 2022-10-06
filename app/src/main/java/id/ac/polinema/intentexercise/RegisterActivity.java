package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;
import java.io.IOException;
import de.hdodenhof.circleimageview.CircleImageView;
import id.ac.polinema.intentexercise.model.User;

public class RegisterActivity extends AppCompatActivity {
    private CircleImageView imgRegis;
    private Button imgInput;
    private Uri imageUri;
    private TextInputEditText etFullname, etNim, etPassword, etCoPass, etHompage,
            etAbout;
    private Button btnOk;
    private static final String TAG = RegisterActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1;
    private static final String USER_KEY = "user";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        imgRegis = findViewById(R.id.image_profile);
        imgInput = findViewById(R.id.imageBtn);
        etFullname = findViewById(R.id.text_fullname);
        etNim = findViewById(R.id.text_email);
        etPassword = findViewById(R.id.text_password);
        etCoPass = findViewById(R.id.text_confirm_password);
        etHompage = findViewById(R.id.text_homepage);
        etAbout = findViewById(R.id.text_about);
        btnOk = findViewById(R.id.button_ok);
        imgInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI), GALLERY_REQUEST_CODE);
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = etFullname.getText().toString();
                String nim = etNim.getText().toString();
                String password = etPassword.getText().toString();
                String conpassword = etCoPass.getText().toString();
                String homepage = etHompage.getText().toString();
                String about = etAbout.getText().toString();
                User user = new User (fullname, nim, password, conpassword, homepage,
                        about);
                Intent intent = new Intent(RegisterActivity.this,
                        ProfileActivity.class);
                intent.putExtra(USER_KEY, user);
                intent.putExtra("profil", imageUri.toString());
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent
            data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            Toast.makeText(this, "Cancel input image", Toast.LENGTH_SHORT).show();
            return;
        } else if(requestCode == GALLERY_REQUEST_CODE){
            if(data != null){
                try {
                    imageUri = data.getData();
                    Bitmap bitmap =
                            MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    imgRegis.setImageBitmap(bitmap);
                }
                catch (IOException e){
                    Toast.makeText(this, "Can't load image",
                            Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }
}
