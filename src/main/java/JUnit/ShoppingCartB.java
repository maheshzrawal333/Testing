package JUnit;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartB {
    private final List<String> items = new ArrayList<>();

    public void addItem(String item) {
        items.add(item);
    }

    public int getItemCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }
}
