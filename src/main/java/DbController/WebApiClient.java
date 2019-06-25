package DbController;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import condeso.HorasMes;
import horario.Dias;
import org.apache.http.HttpEntity;
import condeso.Condeso;
import horario.Plantillas;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import tiendas.Tiendas;

import java.io.IOException;
import java.lang.reflect.Type;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebApiClient implements CrudOperations {

    public final String url = "https://localhost:44389/api/";
    private final Gson gson;

    public WebApiClient()
    {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
    }

    private CloseableHttpClient CreateClient(){
        CloseableHttpClient client = null;
        try {
            client = HttpClients
                    .custom().
                            setHostnameVerifier(new AllowAllHostnameVerifier()).
                            setSslcontext(new SSLContextBuilder().loadTrustMaterial(null, (TrustStrategy) (arg0, arg1) -> true).build()).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public int SaveCondeso(Condeso condeso){
       var client = CreateClient();
        HttpPost post = new HttpPost(url + "/Condesos");
        List<HorasMes> list = MonthHoursMapToList(condeso.getHorasMes());
        condeso.setMonthHours(list);
        String JSON_STRING =  gson.toJson(condeso);
        HttpEntity stringEntity = new StringEntity(JSON_STRING, ContentType.APPLICATION_JSON);
        post.setEntity(stringEntity);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.getStatusLine().getStatusCode();
    }

    private List<HorasMes> MonthHoursMapToList(Map<LocalDate, HorasMes> horasMes) {
        List<HorasMes> list = new ArrayList<HorasMes>(horasMes.values());
        return list;
    }

    @Override
    public int UpdateCondeso(Condeso condeso) {
        var client = CreateClient();
        HttpPut post = new HttpPut(url + "/Condesos/" + condeso.getId());
        String JSON_STRING =  gson.toJson(condeso);
        HttpEntity stringEntity = new StringEntity(JSON_STRING, ContentType.APPLICATION_JSON);
        post.setEntity(stringEntity);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.getStatusLine().getStatusCode();
    }

    @Override
    public int DeleteCondeso(Condeso condeso) {
        var client = CreateClient();
        HttpDelete post = new HttpDelete(url + "/Condesos/" + condeso.getId());
        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.getStatusLine().getStatusCode();
    }

    @Override
    public List<Condeso> GetAllCondesos() {
        var client = CreateClient();
        HttpGet get = new HttpGet(url + "/Condesos");
        CloseableHttpResponse response = null;
        try {
            response = client.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Type listType = new TypeToken<List<Condeso>>(){}.getType();
            var entity = response.getEntity();
            String str = EntityUtils.toString(entity);
            List<Condeso> condesos = gson.fromJson(str, listType);
            return condesos;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int UpdateMultipleCondesos(List<Condeso> condesos) {
        var client = CreateClient();
        HttpPut post = new HttpPut(url + "/Condesos/updatemultiple");
        String JSON_STRING =  gson.toJson(condesos);
        HttpEntity stringEntity = new StringEntity(JSON_STRING, ContentType.APPLICATION_JSON);
        post.setEntity(stringEntity);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.getStatusLine().getStatusCode();
    }

    @Override
    public String SaveTienda(Tiendas tienda) {
        var client = CreateClient();
        HttpPost post = new HttpPost(url + "/Shops");
        String JSON_STRING =  gson.toJson(tienda);
        HttpEntity stringEntity = new StringEntity(JSON_STRING, ContentType.APPLICATION_JSON);
        post.setEntity(stringEntity);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    @Override
    public int UpdateTienda(Tiendas tienda) {
        var client = CreateClient();
        HttpPut post = new HttpPut(url + "/Shops/" + tienda.getId());
        String JSON_STRING =  gson.toJson(tienda);
        HttpEntity stringEntity = new StringEntity(JSON_STRING, ContentType.APPLICATION_JSON);
        post.setEntity(stringEntity);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.getStatusLine().getStatusCode();
    }

    @Override
    public int DeleteTienda(Tiendas tienda) {
        var client = CreateClient();
        HttpDelete post = new HttpDelete(url + "/Shops/" + tienda.getId());
        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.getStatusLine().getStatusCode();
    }

    @Override
    public List<Tiendas> GetAllTiendas() {
        var client = CreateClient();
        HttpGet get = new HttpGet(url + "/Shops");
        CloseableHttpResponse response = null;
        try {
            response = client.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Type listType = new TypeToken<List<Tiendas>>(){}.getType();
            var entity = response.getEntity();
            String str = EntityUtils.toString(entity);
            List<Tiendas> shops = gson.fromJson(str, listType);
            return shops;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int UpdateMultipleTiendas(List<Tiendas> tiendas) {
        var client = CreateClient();
        HttpPut post = new HttpPut(url + "/Shops/updatemultiple");
        String JSON_STRING =  gson.toJson(tiendas);
        HttpEntity stringEntity = new StringEntity(JSON_STRING, ContentType.APPLICATION_JSON);
        post.setEntity(stringEntity);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.getStatusLine().getStatusCode();
    }

    public int SaveMultipleDays(List<Dias> dias)
    {
        var client = CreateClient();
        HttpPost post = new HttpPost(url + "/Days/PostMultipleDays");
        String JSON_STRING =  gson.toJson(dias);
        HttpEntity stringEntity = new StringEntity(JSON_STRING, ContentType.APPLICATION_JSON);
        post.setEntity(stringEntity);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.getStatusLine().getStatusCode();
    }

    public int UpdateMultipleDays(List<Dias> dias)
    {
        var client = CreateClient();
        HttpPost post = new HttpPost(url + "/Days/PutMultipleDays");
        String JSON_STRING =  gson.toJson(dias);
        HttpEntity stringEntity = new StringEntity(JSON_STRING, ContentType.APPLICATION_JSON);
        post.setEntity(stringEntity);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.getStatusLine().getStatusCode();
    }

    public HashMap<LocalDate, Dias> GetDaysForShop(long shopId, LocalDate time)
    {
        var client = CreateClient();
        //TODO checar time tostring formato
        HttpGet get = new HttpGet(url + "/Days/" + shopId + "/" + time.toString());
        CloseableHttpResponse response = null;
        try {
            response = client.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Type listType = new TypeToken<HashMap<LocalDate, Dias>>(){}.getType();
            var entity = response.getEntity();
            String str = EntityUtils.toString(entity);
            HashMap<LocalDate, Dias> shops = gson.fromJson(str, listType);
            return shops;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public int UpdatePlantilla(Plantillas plantilla) {
        var client = CreateClient();
        HttpPut post = new HttpPut(url + "/Plantillas/" + plantilla.getId());
        String JSON_STRING =  gson.toJson(plantilla);
        HttpEntity stringEntity = new StringEntity(JSON_STRING, ContentType.APPLICATION_JSON);
        post.setEntity(stringEntity);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.getStatusLine().getStatusCode();
    }
}

class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
    }

    public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString()).toLocalDate();
    }
}