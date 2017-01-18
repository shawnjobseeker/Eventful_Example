package uk.co.example.shawn.loginapp.connect;

import android.support.v4.util.LruCache;


import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.example.shawn.loginapp.model.artist.Performer;
import uk.co.example.shawn.loginapp.model.event.Event;
import uk.co.example.shawn.loginapp.model.image.Image;
import uk.co.example.shawn.loginapp.model.venue.Venue;

import static uk.co.example.shawn.loginapp.connect.Constants.BASE_URL;

/**
 * Created by Shawn Li on 26/10/2016.
 */

public class ApiClient {

    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient;

    private static LruCache<Class<?>, Observable<?>> apiObservables;

    public static Retrofit getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        okHttpClient = buildClient();
        // custom deserializer for Image (only need to retrieve max one Image)
        Type imageType = new TypeToken<List<Image>>(){}.getType();
        GsonBuilder customBuilder = new GsonBuilder().registerTypeAdapter(imageType, new JsonDeserializer<List<Image>>() {
            @Override
            public List<Image> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {


                ArrayList<Image> list = new ArrayList<Image>();
                if (json instanceof JsonArray)
                    list.add((Image)context.deserialize(((JsonArray)json).get(0), Image.class));
                else
                    list.add((Image)context.deserialize(json, Image.class));
                return list;
            }
        });
        Type eventType = new TypeToken<List<Event>>(){}.getType();
        customBuilder.registerTypeAdapter(eventType, new DeserializerOfArrayObject<Event>(Event.class));
        Type performerType = new TypeToken<List<Performer>>(){}.getType();
        customBuilder.registerTypeAdapter(performerType, new DeserializerOfArrayObject<Performer>(Performer.class));
        Type venueType = new TypeToken<List<Venue>>(){}.getType();
        customBuilder.registerTypeAdapter(venueType, new DeserializerOfArrayObject<Venue>(Venue.class));

        if (retrofit==null) {
            apiObservables = new LruCache<>(1);
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(customBuilder.create()))
                    .client(client)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }


    /**
     * Method to build and return an OkHttpClient so we can set/get
     * headers quickly and efficiently.
     * @return
     */
    private static OkHttpClient buildClient(){

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                // Do anything with response here
                //if we ant to grab a specific cookie or something..
                return response;
            }
        });

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //this is where we will add whatever we want to our request headers.
                Request request = chain.request().newBuilder().addHeader("Accept", "application/json").build();
                return chain.proceed(request);
            }
        });

        return  builder.build();
    }
    /**
     * Method to clear the entire cache of observables
     */
    public void clearCache(){
        apiObservables.evictAll();
    }

    public static LruCache<Class<?>, Observable<?>> getCache() {
        return apiObservables;
    }
    /**
     * Method to either return a cached observable or prepare a new one.
     *
     * @param unPreparedObservable
     * @param clazz
     * @param cacheObservable
     * @param useCache
     * @return Observable ready to be subscribed to
     */
    public static Observable<?> getPreparedObservable(Observable<?> unPreparedObservable, Class<?> clazz, boolean cacheObservable, boolean useCache){

        Observable<?> preparedObservable = null;

        if(useCache)//this way we don't reset anything in the cache if this is the only instance of us not wanting to use it.
            preparedObservable = apiObservables.get(clazz);

        if(preparedObservable!=null)
            return preparedObservable;



        //we are here because we have never created this observable before or we didn't want to use the cache...

        preparedObservable = unPreparedObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        if(cacheObservable){
            preparedObservable = preparedObservable.cache();
            apiObservables.put(clazz, preparedObservable);
        }


        return preparedObservable;
    }
    // custom deserializer for objects that may return Array or Object
    static class DeserializerOfArrayObject<E> implements JsonDeserializer<List<E>> {
        private Class<E> eClass;
        @Override
        public List<E> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            ArrayList<E> list = new ArrayList<E>();
            if (json instanceof JsonArray) {
                for (JsonElement e : (JsonArray)json)
                    list.add((E)context.deserialize(e, eClass));
            }
            else
                list.add((E)context.deserialize(json, eClass));
            return list;
        }
        public DeserializerOfArrayObject(Class<E> typeClass) {
            this.eClass = typeClass;
        }
    }

}
