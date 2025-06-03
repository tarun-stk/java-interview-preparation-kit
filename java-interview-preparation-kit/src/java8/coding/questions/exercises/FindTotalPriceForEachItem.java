package java8.coding.questions.exercises;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Item {
    String name;
    Integer quantity;
    long price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Item(String name, Integer quantity, long price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}

class ItemResult {
    String name;
    Integer totalQuantity;
    Long totalPrice;

    public ItemResult(String name, Integer totalQuantity, Long totalPrice) {
        this.name = name;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }

    public ItemResult(Integer totalQuantity, Long totalPrice) {
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "ItemResult{" +
                "name='" + name + '\'' +
                ", totalQuantity=" + totalQuantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

public class FindTotalPriceForEachItem {
    public static void main(String[] args) {
        getItemNameAlongWithItsPrice();
    }

    private static void getItemNameAlongWithItsPrice() {
        List<Item> items = Arrays.asList(new Item("apple", 5, 5),
                new Item("apple", 20, 20),
                new Item("apple", 1, 1),
                new Item("banana", 10, 10),
                new Item("banana", 20, 20),
                new Item("orange", 60, 60),
                new Item("orange", 20, 20));

        Map<String, ItemResult> result = items.stream().collect(Collectors.groupingBy(Item::getName, Collectors.collectingAndThen(Collectors.toList(),
                list -> {
                    String name = list.getFirst().name;
                    int totalQuantity = list.stream().map(Item::getQuantity).reduce((a, b) -> a + b).get();
                    long totalPrice = list.stream().map(item -> item.getPrice() * item.getQuantity()).reduce((a, b) -> a + b).get();
                    return new ItemResult(name, totalQuantity, totalPrice);
                })));
        result.entrySet().stream().map(item -> item.getValue()).forEach(System.out::println);
    }
}