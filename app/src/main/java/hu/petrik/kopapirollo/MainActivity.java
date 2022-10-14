package hu.petrik.kopapirollo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button ko;
    private Button papir;
    private Button ollo;
    private TextView gepEredmeny;
    private TextView emberEredmeny;
    private ImageView rpsImgHuman;
    private ImageView rpsImgAI;
    private Random rnd;
    private int emberEredmenySzamlalo;
    private int gepEredmenySzamlalo;
    private AlertDialog.Builder vege;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        ko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpsImgHuman.setImageResource(R.drawable.rock);
                Jatek("ko");
            }
        });


        papir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpsImgHuman.setImageResource(R.drawable.paper);
                Jatek("papir");
            }
        });


        ollo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rpsImgHuman.setImageResource(R.drawable.scissors);
                Jatek("ollo");
            }
        });

    }


    private String AIertekadas(int number) {
        switch (number) {
            case 1:
                rpsImgAI.setImageResource(R.drawable.rock);
                return "ko";
            case 2:
                rpsImgAI.setImageResource(R.drawable.paper);
                return "papir";
            case 3:
                rpsImgAI.setImageResource(R.drawable.scissors);
                return "ollo";
            default:
                return "";
        }
    }

    private void Jatek(String ember) {
        String ai = AIertekadas(rnd.nextInt(3) + 1);
        if (ai.equals(ember)) {
            Toast.makeText(this, "A kör döntetlen", Toast.LENGTH_SHORT).show();
        } else if (ai.equals("ko") && ember.equals("papir")) {
            Toast.makeText(this, "A kört te nyerted", Toast.LENGTH_SHORT).show();
            emberEredmenySzamlalo++;
        } else if (ai.equals("ko") && ember.equals("ollo")) {
            Toast.makeText(this, "A kört a gép nyerte", Toast.LENGTH_SHORT).show();
            gepEredmenySzamlalo++;
        } else if (ai.equals("papir") && ember.equals("ko")) {
            Toast.makeText(this, "A kört a gép nyerte", Toast.LENGTH_SHORT).show();
            gepEredmenySzamlalo++;
        } else if (ai.equals("papir") && ember.equals("ollo")) {
            Toast.makeText(this, "A kört te nyerted", Toast.LENGTH_SHORT).show();
            emberEredmenySzamlalo++;
        } else if (ai.equals("ollo") && ember.equals("ko")) {
            Toast.makeText(this, "A kört te nyerted", Toast.LENGTH_SHORT).show();
            emberEredmenySzamlalo++;
        } else if (ai.equals("ollo") && ember.equals("papir")) {
            Toast.makeText(this, "A kört a gép nyerte", Toast.LENGTH_SHORT).show();
            gepEredmenySzamlalo++;
        }
        String h = "Ember: "+emberEredmenySzamlalo+" ";
        String a = "Computer: "+gepEredmenySzamlalo+" ";
        emberEredmeny.setText(h);
        gepEredmeny.setText(a);
        gyozelem(gepEredmenySzamlalo, emberEredmenySzamlalo);
    }

    public void gyozelem(int gep, int human) {
        if (human == 3) {
            vege.setTitle("Győzelem");
            vege.show();
        }else if (gep == 3) {
            vege.setTitle("Vereség");
            vege.show();
        }
    }


    private void ujJatek() {
        rpsImgHuman.setImageResource(R.drawable.rock);
        rpsImgAI.setImageResource(R.drawable.rock);
        emberEredmenySzamlalo =0;
        gepEredmenySzamlalo = 0;
        String h = "Ember: "+emberEredmenySzamlalo+" ";
        String a = "Computer: "+gepEredmenySzamlalo+" ";
        emberEredmeny.setText(h);
        gepEredmeny.setText(a);
    }


    private void init() {
        ko = findViewById(R.id.ko);
        papir = findViewById(R.id.papir);
        ollo = findViewById(R.id.ollo);
        gepEredmeny = findViewById(R.id.gepEredmeny);
        emberEredmeny = findViewById(R.id.emberEredmeny);
        rpsImgAI = findViewById(R.id.rpsImgAI);
        rpsImgHuman = findViewById(R.id.rpsImgHuman);
        rnd = new Random();
        ujJatek();
        emberEredmenySzamlalo = 0;
        gepEredmenySzamlalo = 0;
        vege = new AlertDialog.Builder(this);
        vege.setCancelable(false);
        vege.setMessage("Szeretne új játékot játszani?");
        vege.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        vege.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ujJatek();
            }
        });
    }
}