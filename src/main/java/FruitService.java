import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class FruitService {

    @Inject
    FruitRepository repository;

    public Multi<Fruit> findAll() {
        return repository.findAll();
    }
}
