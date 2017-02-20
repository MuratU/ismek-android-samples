package dnkilic.basketball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    int countA = 0;
    int countB = 0;

    String players = "TEAM B\n" +
            "States\tWilbekin\n" +
            "Arslan, Ender\t\n" +
            "Yağmur, Mehmet\t\n" +
            "Türen, Metin\t\n" +
            "Batuk, Birkan\t\n" +
            "Erden, Semih\t\n" +
            "Wanamaker, Brad\t\n" +
            "Clyburn, Will\t\n" +
            "Moerman, Adrien\t\n" +
            "Aldemir, Furkan\t\n" +
            "Savaş, Oğuz\t\n" +
            "Anderson, James\t\n" +
            "Slaughter, Marcus\n" +
            "Dairis\t\n" +
            "Harangody, Luke\t\n" +
            "\n" +
            "TEAM A\n" +
            "Uğurlu, Berk\t\n" +
            "Dixon, Bobby \n" +
            "Sloukas, Kostas\t\n" +
            "Mahmutoğlu, Melih \n" +
            "Bogdanović, Bogdan \n" +
            "Minchev, Yordan \n" +
            "Arna, Egehan\t\n" +
            "Nunnally, James\t\n" +
            "Kalinić, Nikola\t\n" +
            "Datome, Luigi\t\n" +
            "Hersek, Barış\t\n" +
            "Antić, Pero\n" +
            "Veselý, Jan\t\n" +
            "tates\tUdoh, Ekpe\t\n" +
            "Duran, Ahmet Can\t\n" +
            "Düverioğlu, Ahmet ";

    @BindView(R.id.tvCountA) TextView tvCountA;
    @BindView(R.id.tvCountB) TextView tvCountB;
    @BindView(R.id.checkBox) CheckBox cbCheat;
    @BindView(R.id.textView4) TextView tvPlayers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tvPlayers.setText(players);
    }

    public void updateA()
    {
        tvCountA.setText("" + countA);
    }

    public void updateB()
    {
        if(cbCheat.isChecked())
        {
            countB = countB - 1;
        }

        tvCountB.setText("" + countB);
    }

    @OnClick(R.id.btnThreeA) void btnThreeA() {
        countA = countA + 3;
        updateA();
    }
    @OnClick(R.id.btnThreeB) void btnThreeB() {
        countB = countB + 3;
        updateB();
    }
    @OnClick(R.id.btnTwoA) void btnTwoA() {
        countA = countA + 2;
        updateA();
    }
    @OnClick(R.id.btnTwoB) void btnTwoB() {
        countB = countB + 2;
        updateB();
    }
    @OnClick(R.id.btnOneA) void btnOneA() {
        countA = countA + 1;
        updateA();
    }
    @OnClick(R.id.btnOneB) void btnOneB() {
        countB = countB + 1;
        updateB();
    }
    @OnClick(R.id.btnReset) void btnReset() {

        if(cbCheat.isChecked())
        {
            countB = 1;
        }
        else
        {
            countB = 0;
        }

        countA = 0;
        updateB();
        updateA();
    }
}
