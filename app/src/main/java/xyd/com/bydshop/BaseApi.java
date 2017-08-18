package xyd.com.bydshop;



import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import xyd.com.bydshop.cookie.PersistentCookieStore;
import xyd.com.bydshop.utils.LogUtil;

/**
 * @author: zhaoxiaolei
 * @date: 2017/4/17
 * @time: 14:51
 * @description:
 */

public class BaseApi {
    private static Retrofit sRetrofit ;
    private static OkHttpClient sOkHttpClient ;
    private static BaseApi network;

    public static Retrofit getRetrofit() {
        if (network == null || sRetrofit == null || sOkHttpClient == null) {
            network = new BaseApi();
        }
        return sRetrofit;
    }

    public BaseApi() {
        initOkHttp();
    }

    private static void initOkHttp() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        File cacheFile = new File("cacheFile");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                //   request=request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                Response response = chain.proceed(request);
                Response.Builder newBuilder = response.newBuilder();
                int maxAge = 0;
                // 有网络时 设置缓存超时时间0个小时
                newBuilder.header("Cache-Control", "public, max-age=" + maxAge);
                // 无网络时，设置超时为4周
                // int maxStale = 60 * 60 * 24 * 28;
                // newBuilder.header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale);

                return newBuilder.build();
            }
        };
       // builder.cookieJar(new CookiesManager());
        builder.cache(cache).addInterceptor(interceptor);
        //设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);

        //设置重连
        builder.retryOnConnectionFailure(true);
        sOkHttpClient = builder.build();

      //  LogUtil.e("cookieJar",sOkHttpClient.cookieJar().toString());
        sRetrofit = new Retrofit.Builder()
                .baseUrl("http://helpd.firelord.xin/v1/")
                .client(sOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }

    /**
     * 自动管理Cookies
     */
    private static class CookiesManager implements CookieJar {
        private final PersistentCookieStore cookieStore = new PersistentCookieStore(MyApplication.context);

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            if (cookies != null && cookies.size() > 0) {
                for (Cookie item : cookies) {
                    cookieStore.add(url, item);
                }
            }
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookies = cookieStore.get(url);
            return cookies;
        }
    }

//    try {
//        // Create a trust manager that does not validate certificate chains
//        final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
//            @Override
//            public void checkClientTrusted(
//                    java.security.cert.X509Certificate[] chain,
//                    String authType) throws CertificateException {
//            }
//
//            @Override
//            public void checkServerTrusted(
//                    java.security.cert.X509Certificate[] chain,
//                    String authType) throws CertificateException {
//            }
//
//            @Override
//            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                return new java.security.cert.X509Certificate[0];
//            }
//        } };
//
//        // Install the all-trusting trust manager
//        //trustAllCerts
//
//        final SSLContext sslContext = SSLContext.getInstance("SSL");
//        sslContext.init(null, trustAllCerts,
//                new java.security.SecureRandom());
//        // Create an ssl socket factory with our all-trusting manager
//        final SSLSocketFactory sslSocketFactory = sslContext
//                .getSocketFactory();
//
//        OkHttpClient okHttpClient = new OkHttpClient();
//        okHttpClient = okHttpClient.newBuilder()
//                .sslSocketFactory(sslSocketFactory)
//                .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).build();
//
//        return okHttpClient;
//    } catch (Exception e) {
//        throw new RuntimeException(e);
//    }
}
