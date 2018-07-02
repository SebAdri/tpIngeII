package agenda.agenda.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static agenda.agenda.Configuraciones.URL_BASE_SERVICE;


public class ApiBuilder {
    public static Api retrofit;
    public static Api build() {
        final GsonBuilder gson = new GsonBuilder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
         OkHttpClient.Builder client = new OkHttpClient.Builder();
         client.addInterceptor(logging);
         if (retrofit==null){
              retrofit = new Retrofit.Builder()
                     .baseUrl(URL_BASE_SERVICE)
                     .addConverterFactory(GsonConverterFactory.create())
                     .client(client.build())
                     .build().create(Api.class);

         };

        return retrofit;
    }

}
