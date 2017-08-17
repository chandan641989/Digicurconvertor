package com.kartnap.chandan.digicurconvertor;

import com.kartnap.chandan.digicurconvertor.Remote.CoinServices;
import com.kartnap.chandan.digicurconvertor.Remote.RetrofitClient;

/**
 * Created by Chandan on 8/17/2017.
 */

public class Common {
    public static final String API_URL = "https://min-api.cryptocompare.com";
    public static final String BASE_URL = "https://cryptocompare.com";
    public static final String ETC_IMAGE = new StringBuilder(BASE_URL).append("media/20275/etc2.png").toString();
    public static final String DASH_IMAGE = new StringBuilder(BASE_URL).append("/media/20026/dash.png").toString();
    public static final String BTC_IMAGE = new StringBuilder(BASE_URL).append("/media/19633/btc.png").toString();
    public static final String ETH_IMAGE = new StringBuilder(BASE_URL).append("/media/352247/maid.png").toString();
    public static final String XEM_IMAGE = new StringBuilder(BASE_URL).append("/media/352247/maid.png").toString();
    public static final String AUR_IMAGE = new StringBuilder(BASE_URL).append("/media/352247/maid.png").toString();
    public static final String LTC_IMAGE = new StringBuilder(BASE_URL).append("/media/352247/maid.png").toString();
    public static final String XMR_IMAGE = new StringBuilder(BASE_URL).append("/media/352247/maid.png").toString();
    public static final String MAID_IMAGE = new StringBuilder(BASE_URL).append("/media/352247/maid.png").toString();
    public static final String XRP_IMAGE = new StringBuilder(BASE_URL).append("/media/19972/ripple.png\n").toString();

    public static CoinServices getCoinService(){
        return RetrofitClient.getClient(API_URL).create(CoinServices.class);

    }

}
