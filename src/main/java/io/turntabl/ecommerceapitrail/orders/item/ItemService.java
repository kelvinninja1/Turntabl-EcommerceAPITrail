package io.turntabl.ecommerceapitrail.orders.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public List<Item> getOrderItems(Long orderID) {
        return itemRepository.findAllByOrder(orderID);
    }

    public ResponseEntity<Item> addItems(Item item) {
        if (item.getOrder() != null && item.getProduct() != null && item.getPrice() != null && item.getQuantity() != null && item.getQuantity() > 0) {
            Item newItem = itemRepository.save(item);
            return new ResponseEntity<Item>(newItem, HttpStatus.CREATED);
        } else {
            throw new BadRequestException("Item details are empty, bad or Un-formatted");
        }
    }

    public Item getItem(Long orderID) {
        return itemRepository.findByOrder(orderID).orElseThrow(() -> new IllegalStateException("Item with Order ID:" + orderID + " does not exist"));
    }

    public void deleteItem(Long orderID) {
        boolean exists = itemRepository.existsByOrder(orderID);
        if (!exists) {
            throw new IllegalStateException("Item with Order ID:" + orderID + " does not exist");
        }
        itemRepository.deleteByOrder(orderID);
    }

    @Transactional
    public void updateItem(Long itemID, Map<String, Object> change) {
        Item item = itemRepository.findById(itemID).orElseThrow(() -> new IllegalStateException("Item with Order ID:" + itemID + " does not exist"));

        Integer quantity = Integer.parseInt(change.get("quantity").toString());
        if (quantity > 0 && !Objects.equals(quantity, item.getQuantity())) {
            item.setQuantity(quantity);
            item.setDateModified(LocalDate.now());
        }
    }

    public List<Long> getOrderIDsByProduct(Long productID) {
        return itemRepository.findAllIDsByProduct(productID);
    }

    public List<Long> getProductIDsByOrderIDs(List<Long> orderIDs) {
        return itemRepository.findAllDistinctProductByOrderIDs(orderIDs);
    }

    public List<Integer> getProductCountsByProductIDs(List<Long> productIDs) {
        return itemRepository.CountProductByProductIDs(productIDs);
    }

    public Integer getCountByProduct(Long productID) {
        return itemRepository.countAllByProduct(productID);
    }
}
