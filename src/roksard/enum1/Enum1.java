package roksard.enum1;

import java.util.HashMap;

enum LotStatus {
    CREATED(1, "Создан"),
    CANCELLED(2, "Отменен");

    private Integer id;
    private String name;
    private static HashMap<Integer,LotStatus> byId = new HashMap<>();

    static {
        for (LotStatus value : LotStatus.values()) {
            byId.put(value.getId(), value);
        }
    }

    LotStatus(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static LotStatus getById(Integer id) {
        return byId.get(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class Enum1 {
    public static void main(String[] args) {
        LotStatus status = LotStatus.getById(1);
        if (status == LotStatus.CREATED) {
            System.out.println("==created");
        }
    }
}
