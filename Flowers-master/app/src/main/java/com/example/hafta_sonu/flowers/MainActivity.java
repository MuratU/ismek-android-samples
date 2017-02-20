package com.example.hafta_sonu.flowers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openAnotherActivity(Flower flower)
    {
        Intent intent = new Intent(MainActivity.this, FlowerActivity.class);
        intent.putExtra("NAME", flower.name);
        intent.putExtra("IMG_LOCATION", flower.imgLocation);
        intent.putExtra("DESCRIPTION", flower.description);
        intent.putExtra("URL", flower.url);
        startActivity(intent);
    }

    public void openViolet(View vc)
    {
        String nameOfViolet = "Menekşe";
        int drawableOfViolet = R.drawable.menekse;
        String descriptionOfViolet = "Menekşe, menekşegiller (Violaceae) familyasına bağlı Viola cinsini oluşturan çoğunlukla saksılarda yetiştirilen bitki türlerinin ortak adı. 400 ile 500 arası türü bulunmaktadır. Dünyanın birçok yerinde yetişebilmekle beraber en çok kuzey yarımkürede yetişir. \n" +
                "Ayrıca Hawai ve Güneydoğu Asya'da da yetişebilir. Doğada aydınlık, fakat gölgede ve nemli bölgelerde yetişir.\n";
        String urlOfViolet = "https://tr.wikipedia.org/wiki/Dosya:2014.03.29.-20-Mannheim_Neckarau_Waldpark--Wald-Veilchen.jpg";
        Flower flwViolet = new Flower(nameOfViolet, drawableOfViolet, descriptionOfViolet, urlOfViolet);

        openAnotherActivity(flwViolet);
    }

    public void openRose(View vc)
    {
        String nameOfRose = "Gül";
        int drawableOfRose = R.drawable.gul;
        String descriptionOfRose = "Gül, gülgiller (Rosaceae) familyasının Rosa cinsinden güzel kokulu bitki türlerine verilen ad.\n" +
                "Anavatanı Anadolu, İran ve Çin'dir ama başka yerlerde de yetişir. Çok güzel ve kıymetlidir. \n" +
                "Park ve bahçelerin süslenmesinde kullanıldığı gibi odaları, balkon ve terasları süsler. \n" +
                "Birçok rengi vardır.Kesme çiçekçilikte çok talep edilen bir çiçektir." +
                "Kaliforniya yabani gülü (Rosa californica)\n" +
                "Kuşburnu (Rosa canina)\n" +
                "Okka gülü (Rosa × centifolia)\n" +
                "Çin gülü (Rosa chinensis)\n" +
                "Rosa dumalis\n" +
                "Rosa eglanteria\n" +
                "Kırmızı frenk gülü (Rosa gallica)\n" +
                "Rosa gigantea\n" +
                "Rosa glauca\n" +
                "Rosa laevigata\n" +
                "Misk gülü (Rosa moschata)\n" +
                "Rosa multiflora\n" +
                "Rosa omeiensis\n" +
                "Bataklık gülü (Rosa palustris)\n" +
                "Rosa persica\n" +
                "Rosa pimpinellifolia\n" +
                "Rosa pinetorum\n" +
                "Rosa roxburghii\n" +
                "Rosa rubiginosa\n" +
                "Japon gülü (Rosa rugosa)\n" +
                "Rosa sericea\n" +
                "Rosa stellata\n" +
                "Rosa virginiana\n" +
                "yediveren gülleri";
        String urlOfRose = "https://tr.wikipedia.org/wiki/G%C3%BCl#/media/File:Bridal_pink_-_morwell_rose_garden.jpg";
        Flower flwRose = new Flower(nameOfRose, drawableOfRose, descriptionOfRose, urlOfRose);

        openAnotherActivity(flwRose);
    }

    public void openNarcissus(View vc)
    {
        String nameOfNarcissus = "Nergis";
        int drawableOfNarcissus = R.drawable.nergis;
        String descriptionOfNarcissus = "Nergis, nergisgiller (Amaryllidaceae) familyasından Narcissus cinsinden bitki türlerinin ortak adı.\n" +
                "Bu bitkilerde sap 20-80 cm kadar yükselebilmektedir. \n" +
                "Soğanlı olan bu bitkilerde taç yaprakları beyaz veya sarının karışımları şeklindedir.\n" +
                "Anavatanı Avrupa olan bu bitkilerin en çok tür zenginliğine İspanya ve Portekiz'de rastlanmaktadır. \n" +
                "Ancak doğal olarak tüm Akdeniz kıyılarında, hatta bunun uzantısı olan Japonya'ya kadar aynı enlem dereceleri arasında görülmektedir. \n" +
                "Dünyada Avrupa, Kuzey Amerika, Kuzey Afrika ülkelerinde tarımı yapılmaktadır.";
        String urlOfNarcissus = "https://tr.wikipedia.org/wiki/Dosya:Narzisse.jpg";
        Flower flwNarcissus = new Flower(nameOfNarcissus, drawableOfNarcissus, descriptionOfNarcissus, urlOfNarcissus);

        openAnotherActivity(flwNarcissus);
    }

    public void openClove(View vc)
    {
        String nameOfClove = "Karanfil";
        int drawableOfClove = R.drawable.karanfil;
        String descriptionOfClove = "Karanfil, karanfilgiller (Caryophyllaceae) familyasının Dianthus cinsinden karşılıklı, ensiz, sivri yapraklara sahip otsu bitkilerin ortak adı.\n" +
                "Dalcıkların ucunda tek tek ya da topluca bulunan çiçekleri beyaz, pembe ya da kırmızı renklidir. \n" +
                "Her çiçek bir çanakçık oluşturan dört burgu yaprakçığıyla belirgindir. Bahçe karanfili en ünlüsüdür. Bu karanfilin katmerli, yarı katmerli, alacalı ve hoş kokulu pek çok çeşidi vardır.";
        String urlOfClove = "https://tr.wikipedia.org/wiki/Karanfil_(%C3%A7i%C3%A7ek)#/media/File:Dianthus.jpg";
        Flower flwClove = new Flower(nameOfClove, drawableOfClove, descriptionOfClove, urlOfClove);

        openAnotherActivity(flwClove);
    }
}
