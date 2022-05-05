import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;

import javax.inject.Inject;
import java.util.stream.StreamSupport;

public class FruitService {

    @Inject
    FruitRepository repository;

    public Multi<Fruit> findAll() {
        return repository.findAll();
    }
}
