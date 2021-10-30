package io.turntabl.ecommerceapitrail.orders.item;

import io.turntabl.ecommerceapitrail.common.exceptions.BadRequestException;
import io.turntabl.ecommerceapitrail.common.exceptions.NotAcceptableException;
import io.turntabl.ecommerceapitrail.common.exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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
        return itemRepository.findAllByOrderID(orderID);
    }

    public Item addItems(Item item) {
        if (item == null) {
            throw new IllegalStateException("Item details are empty");
        }
        itemRepository.save(item);
        return item;
    }

    public Item getItem(Long orderID) {
        return itemRepository.findByOrderID(orderID).orElseThrow(() -> new NotFoundException("Item with Order ID:" + orderID + " does not exist"));
    }

    public ResponseEntity<Item> deleteItems(Long orderID) {
        boolean exists = itemRepository.existsByOrderID(orderID);
        if (!exists) {
            throw new NotFoundException("Item with Order ID:" + orderID + " does not exist");
        }
        itemRepository.deleteAllByOrderID(orderID);
        return new ResponseEntity<Item> (HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Item> deleteItem(Long itemID) {
        boolean exists = itemRepository.existsById(itemID);
        if (!exists) {
            throw new NotFoundException("Item with Order ID:" + itemID + " does not exist");
        }
        itemRepository.deleteById(itemID);
        return new ResponseEntity<Item> (HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Item> updateItem(Long itemID, Item updatedItem) {
        Item item = itemRepository.findById(itemID).orElseThrow(() -> new NotFoundException("Item with Order ID:" + itemID + " does not exist"));

        Integer quantity = updatedItem.getQuantity();
        if (quantity != null && quantity > 0) {
            if (Objects.equals(quantity, item.getQuantity())) {
                throw new NotAcceptableException("No change Required, Updated details already exist");
            } else {
                BeanUtils.copyProperties(updatedItem, item);
                item.setId(itemID);
                item.setDateModified(LocalDate.now());
                itemRepository.save(item);
                return new ResponseEntity<Item>(item, HttpStatus.ACCEPTED);
            }
        }
        else {
            throw new BadRequestException("Item details are empty, bad or Un-formatted");
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
