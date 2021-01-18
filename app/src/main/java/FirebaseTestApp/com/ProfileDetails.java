package FirebaseTestApp.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileDetails extends AppCompatActivity {

    ImageView imageView;
    TextView name, email, id;
    Button signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        imageView = findViewById(R.id.imageView);
        name = findViewById(R.id.textName);
        email = findViewById(R.id.textEmail);

        signOut = findViewById(R.id.Sign_out_button);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount != null) {
            Uri personPhoto = signInAccount.getPhotoUrl();
            Glide.with(this).load(String.valueOf(personPhoto)).into(imageView);

            name.setText(signInAccount.getDisplayName());
            email.setText(signInAccount.getEmail());
            id.setText(signInAccount.getId());
        }

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });
    }
}