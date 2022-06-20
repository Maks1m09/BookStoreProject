package by.tms.bookshop.service;

import by.tms.bookshop.entity.Shop;
import by.tms.bookshop.repository.ShopRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShopService {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    ShopRepository shopRepository;

    public boolean saveShop(Shop shop) {
        if (shop == null) {
            logger.debug("The store would not be saved (shop = null)");
        }
        logger.debug("The store would have been successfully saved");
        shopRepository.save(shop);
        return true;
    }

    public Iterable<Shop> findAllInfo() {
        logger.debug("Display information about all known stores");
        return shopRepository.findAll();
    }
}
