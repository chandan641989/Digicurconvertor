package com.kartnap.chandan.digicurconvertor;

import android.app.ProgressDialog;
import android.hardware.usb.UsbDevice;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.kartnap.chandan.digicurconvertor.Model.Coin;
import com.kartnap.chandan.digicurconvertor.Remote.CoinServices;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    CoinServices mServices;
    RadioGroup radioGroup;
    RadioButton coin2coin,coin2money,money2coin;
    ImageView coinImage;
    Button btnconvert;
    CardView cardView;
    TextView toTextView,fromTextView;
    MaterialSpinner fromSpinner,toSpinner;
    String[] money = {"USD","EUR","GBP"};
    String[] coin = {"BTC","ETH","ETC","XRP","LTC","XMR","DASH","MAID","AUR","XEM"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mServices = Common.getCoinService();
        fromSpinner = (MaterialSpinner)findViewById(R.id.fromSpinner);
        toSpinner = (MaterialSpinner)findViewById(R.id.toSpinner);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        btnconvert = (Button)findViewById(R.id.btnConvert);
        coinImage = (ImageView)findViewById(R.id.coinImage);
        coin2coin =(RadioButton)findViewById(R.id.coin2coin);
        coin2money =(RadioButton)findViewById(R.id.coin2money);
        toTextView = (TextView)findViewById(R.id.totextView);
        //toTextView.setVisibility(View.GONE);
        cardView = (CardView)findViewById(R.id.cardView);
        cardView.setVisibility(View.GONE);



        money2coin =(RadioButton)findViewById(R.id.money2coin);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int i) {
                cardView.setVisibility(View.GONE);
                if (i == R.id.coin2coin)
                    setCoin2CoinSource();
                else if (i == R.id.money2coin)
                    setMoney2CoinSource();
                else if (i == R.id.coin2money)
                    setCoin2MoneySource();
            }
        });
        btnconvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculateValue();
            }
        });


     loadCoinList();


    }



    private void loadCoinList() {
        if (coin2coin.isChecked())
            setCoin2CoinSource();
        else if(coin2coin.isChecked())
            setCoin2MoneySource();
        else if (money2coin.isChecked())
            setMoney2CoinSource();

    }

    private void setCoin2CoinSource() {
        fromSpinner.setItems(coin);
        toSpinner.setItems(coin);
    }
    private void setMoney2CoinSource(){
        fromSpinner.setItems(money);
        toSpinner.setItems(coin);

    }
    private void setCoin2MoneySource(){
        fromSpinner.setItems(coin);
        toSpinner.setItems(money);

    }
    private void calculateValue(){
        final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Please Wait...");
        dialog.show();
        String fromCoin = fromSpinner.getItems().get(fromSpinner.getSelectedIndex()).toString();
        final String coinName = toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString();
        mServices.calculateValue(fromCoin,coinName).enqueue(new Callback<Coin>() {
            @Override
            public void onResponse(Call<Coin> call, Response<Coin> response) {
                dialog.dismiss();

                if (coinName.equals("BTC"))
                    showData(response.body().getBTC());
                if (coinName.equals("ETC"))
                    showData(response.body().getETC());
                if (coinName.equals("ETJ"))
                    showData(response.body().getETH());
                if (coinName.equals("XRP"))
                    showData(response.body().getXRP());
                if (coinName.equals("XEM"))
                    showData(response.body().getXEM());
                if (coinName.equals("XMR"))
                    showData(response.body().getXMR());
                if (coinName.equals("MAID"))
                    showData(response.body().getMAID());
                if (coinName.equals("LTC"))
                    showData(response.body().getLTC());
                if (coinName.equals("DASH"))
                    showData(response.body().getDASH());
                if (coinName.equals("AUR"))
                    showData(response.body().getAUR());
                if (coinName.equals("USD"))
                    showData(response.body().getUSD());
                if (coinName.equals("EUR"))
                    showData(response.body().getEUR());
                if (coinName.equals("GBP"))
                    showData(response.body().getGBP());


            }

            @Override
            public void onFailure(Call<Coin> call, Throwable t) {
                Log.d("ERROR",t.getMessage());
                dialog.dismiss();

            }
        });

    }

    private void showData(String value) {
        cardView.setVisibility(View.VISIBLE);


        if (toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("BTC")){
            Picasso.with(this)
                    .load(Common.BTC_IMAGE)
                    .into(coinImage);
            toTextView.setText(value);
        }
        else if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("ETC")){
            Picasso.with(this)
                    .load(Common.ETC_IMAGE)
                    .into(coinImage);
            toTextView.setText(value);
        }
        else if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("AUR")){
            Picasso.with(this)
                    .load(Common.AUR_IMAGE)
                    .into(coinImage);
            toTextView.setText(value);
        }
        else if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("DASH")){
            Picasso.with(this)
                    .load(Common.DASH_IMAGE)
                    .into(coinImage);
            toTextView.setText(value);
        }
        else if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("MAID")){
            Picasso.with(this)
                    .load(Common.MAID_IMAGE)
                    .into(coinImage);
            toTextView.setText(value);
        }
        else if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("XEM")){
            Picasso.with(this)
                    .load(Common.XEM_IMAGE)
                    .into(coinImage);
            toTextView.setText(value);
        }
        else if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("XMR")){
            Picasso.with(this)
                    .load(Common.XMR_IMAGE)
                    .into(coinImage);
            toTextView.setText(value);
        }
        else if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("XRP")){
            Picasso.with(this)
                    .load(Common.XRP_IMAGE)
                    .into(coinImage);
            toTextView.setText(value);
        }
        else if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("LTC")){
            Picasso.with(this)
                    .load(Common.LTC_IMAGE)
                    .into(coinImage);
            toTextView.setText(value);
        }
        else if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("ETH")){
            Picasso.with(this)
                    .load(Common.ETH_IMAGE)
                    .into(coinImage);
            toTextView.setText(value);
        }
        else if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("USD")){

            toTextView.setText("$"+value);
        }
        else if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("GBP")){

            toTextView.setText("£"+value);
        }
        else if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("EUR")){

            toTextView.setText("€"+value);
        }
    }
}
