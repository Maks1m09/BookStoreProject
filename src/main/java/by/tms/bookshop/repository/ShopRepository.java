package by.tms.bookshop.repository;
import by.tms.bookshop.entity.Shop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShopRepository extends CrudRepository <Shop, Long> {
}
