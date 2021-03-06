package course.java.sdm.engine.exception;

import course.java.sdm.engine.engine.Item;

import java.util.Set;

public class NotAllItemsAreBeingSoldException extends RuntimeException{
    private final String EXCEPTION_MESSAGE;

    public NotAllItemsAreBeingSoldException(Set<Item> items)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("The following items are not being sell by any store:\n");
        for (Item item : items) {
            sb.append("ID: ").append(item.getId()).append(", name: ").append(item.getName()).append("\n");
        }
        EXCEPTION_MESSAGE = sb.toString() + "Each item in the super must be sell in at least by one store.";
    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
