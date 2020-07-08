package roksard.collections.Map;


import java.util.*;

class Pojo {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pojo pojo = (Pojo) o;
        return id == pojo.id &&
                signed == pojo.signed &&
                Objects.equals(code, pojo.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, signed, code);
    }

    int id;
    int signed;

    public Pojo(String code, int id, int signed) {
        this.id = id;
        this.signed = signed;
        this.code = code;
    }

    String code;


    public int getId() {
        return id;
    }

    public int getSigned() {
        return signed;
    }

    @Override
    public String toString() {
        return "[" + code + "]Pojo" + id + ":" + signed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSigned(int signed) {
        this.signed = signed;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

public class MapPutReplacesElement {
    static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MapPutReplacesElement.class);

    static void setOnlyUniqueObjects(List<Pojo> list) {
        HashMap<String, Pojo> pojoByCode = new HashMap<>();

        for (Pojo pojo : list) {
            Pojo oldVersionOkei = pojoByCode.get(pojo.getCode());

            if (oldVersionOkei != null) {

                oldVersionOkei.setCode(pojo.getCode());
                oldVersionOkei.setId(pojo.getId());
                oldVersionOkei.setSigned(pojo.getSigned());

                pojoByCode.remove(pojo.getCode());
                pojoByCode.put(oldVersionOkei.getCode(), oldVersionOkei);
            } else {
                pojoByCode.putIfAbsent(pojo.getCode(), pojo);
            }
        }
        list.clear();

        pojoByCode.forEach((code, dOkei) -> {
            list.add(dOkei);
        });
    }

    static void setOnlyUniqueObjectsV2(List<Pojo> list) {
        HashMap<String, Pojo> pojoByCode = new HashMap<>();
        list.forEach(pojo -> pojoByCode.put(pojo.getCode(), pojo));
        list.clear();

        pojoByCode.forEach((code, dOkei) -> {
            list.add(dOkei);
        });
    }

    public static List<Pojo> getListPojo() {
        List<Pojo> list = new ArrayList<>();
        Random rand  = new Random();
        int count = rand.nextInt(5)+3;
        List<String> codes = Arrays.asList("a", "b", "c");
        for(int i = 0; i < count; i++) {
            list.add(new Pojo(
                    codes.get(i % codes.size()),
                    i,
                    rand.nextInt(100)
            ));
        }
        list.sort(Comparator.comparingInt(Pojo::getSigned));
        return list;
    }

    public static void testListsGeneration() {
        logger.info("test random lists: (should be sorted by 'signed')");
        for (int i = 0; i < 10; i++) {
            logger.info("list {} = {}", i, getListPojo());
        }
        logger.info("finished lists generation test");
    }

    public static void main(String[] args) {
        testListsGeneration();
        for (int i = 0; i < 10; i++) {
            List<Pojo> listA = getListPojo();
            List<Pojo> listB = new ArrayList<>(listA);
            logger.info("generated listA = \n{}", listA);
            logger.info("generated listB = \n{}", listB);
            if (listA == listB) {
                throw new RuntimeException("lists are the same object!");
            }
            if (!listA.equals(listB)) {
                throw  new RuntimeException("lists are not equal!");
            }
            setOnlyUniqueObjects(listA);
            setOnlyUniqueObjectsV2(listB);
            if (listA.equals(listB)) {
                logger.info("result OK!: listA = \n{}, \nlistB = \n{}", listA, listB);
            } else {
                logger.error("result BAD: listA = \n{}, \nlistB = \n{}", listA, listB);
                throw new RuntimeException("different result!");
            }
        }
    }
}
