import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

public interface BaseRepository {
    public Uni<Fruit> create();
    public Multi<Fruit> findAll();
    public Boolean delete();
    public Uni<Fruit> update();
    public Uni<Fruit> findById(Long id);
}
