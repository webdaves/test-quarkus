import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.stream.StreamSupport;
@ApplicationScoped
@Blocking
public class FruitRepository implements BaseRepository {

    @Inject
    PgPool client;

    @Override
    public Uni<Fruit> create() {
        return null;
    }

    @Override
    public Multi<Fruit> findAll() {
        return this.client
                .query("SELECT * FROM fruit")
                .execute()
                .onItem()
                .transformToMulti(rs ->
                        Multi.createFrom()
                                .items(() ->
                                        StreamSupport.stream(rs.spliterator(), false)
                                ))
                .map(Fruit::from);
    }

    @Override
    public Boolean delete() {
        return null;
    }

    @Override
    public Uni<Fruit> update() {
        return null;
    }

    @Override
    public Uni<Fruit> findById(Long id) {
        return null;
    }
}
