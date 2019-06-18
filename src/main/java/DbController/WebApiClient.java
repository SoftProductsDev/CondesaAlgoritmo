package DbController;

import com.google.gson.Gson;
import condeso.Condeso;
import horario.Plantillas;
import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;
import tiendas.Tiendas;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class WebApiClient implements CrudOperations {

    public final String url = "https://localhost:44389/api/";

    public void MakeHttpClient(Condeso condeso) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        URI uri = URI.create(url + "Users");
        Gson gson = new Gson();
        String condesoJson = gson.toJson(condeso);
        var body = HttpRequest.BodyPublisher.fromString(condesoJson);
        HttpRequest postRequest = HttpRequest.newBuilder(uri).POST(body).build();
        HttpResponse<String> response = HttpClient.newBuilder()
                .build()
                .send(postRequest, HttpResponse.BodyHandler.asString());
    }


    @Override
    public String SaveCondeso(Condeso condeso) {
        return null;
    }

    @Override
    public String UpdateCondeso(Condeso condeso) {
        return null;
    }

    @Override
    public String DeleteCondeso(Condeso condeso) {
        return null;
    }

    @Override
    public List<Condeso> GetAllCondesos() {
        return null;
    }

    @Override
    public void UpdateMultipleCondesos(List<Condeso> condesos) {

    }

    @Override
    public String SaveTienda(Tiendas tienda) {
        return null;
    }

    @Override
    public String UpdateTienda(Tiendas tienda) {
        return null;
    }

    @Override
    public String DeleteTienda(Tiendas tienda) {
        return null;
    }

    @Override
    public List<Tiendas> GetAllTiendas() {
        return null;
    }

    @Override
    public void UpdateMultipleTiendas(List<Tiendas> tiendas) {

    }

    @Override
    public String UpdatePlantilla(Plantillas plantilla) {
        return null;
    }
}
