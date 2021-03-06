import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;

@Path("fruit")
@RequestScoped
@Blocking
public class FruitResource {

  @Inject
  PgPool client;

  @Inject
  FruitService service;

  @Inject
  @ConfigProperty(name = "myapp.schema.create", defaultValue = "true")
  boolean schemaCreate;

  @PostConstruct
  void config() {
    if (schemaCreate) {
      initdb();
    }
  }

  private void initdb() {
    client.query("DROP TABLE IF EXISTS fruit").execute()
        .flatMap(r -> client.query("CREATE TABLE fruit (id SERIAL PRIMARY KEY, name TEXT NOT NULL)").execute())
        .flatMap(r -> client.query("INSERT INTO fruit (name) VALUES ('Orange')").execute())
        .flatMap(r -> client.query("INSERT INTO fruit (name) VALUES ('Pear')").execute())
        .flatMap(r -> client.query("INSERT INTO fruit (name) VALUES ('Apple')").execute())
        .await().indefinitely();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Multi<Fruit> getAllfruit() {
    return service.findAll();
  }

}
