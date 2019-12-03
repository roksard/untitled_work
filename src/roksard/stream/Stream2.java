package roksard.stream;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

class DeliveryPojo {
    BigDecimal quantity;
    Integer farmSpec;

    public Integer getContractSpecFarmId() {
        return farmSpec;
    }

    public DeliveryPojo(BigDecimal quantity, Integer farmSpec) {
        this.quantity = quantity;
        this.farmSpec = farmSpec;
    }

    public BigDecimal getStagePlanQuantity() {
        return quantity;
    }
    public BigDecimal getQuantity() {
        return quantity;
    }
}

class StagePojo {
    List<DeliveryPojo> stageDeliveries;
    public StagePojo(List<DeliveryPojo> stageDeliveries) {
        this.stageDeliveries = stageDeliveries;
    }
    public List<DeliveryPojo> getStageDeliveries() {
        return stageDeliveries;
    }
}

public class Stream2 {
    public static void main(String[] args) {
        List<DeliveryPojo> deliveries1 = new LinkedList<>();
        deliveries1.add(new DeliveryPojo(BigDecimal.valueOf(1), 11));
        deliveries1.add(new DeliveryPojo(BigDecimal.valueOf(2), 0));

        List<DeliveryPojo> deliveries2 = new LinkedList<>();
        deliveries2.add(new DeliveryPojo(BigDecimal.valueOf(3), 11));
        deliveries2.add(new DeliveryPojo(BigDecimal.valueOf(4), 0));
        deliveries2.add(new DeliveryPojo(BigDecimal.valueOf(5), 0));

        List<DeliveryPojo> deliveries3 = new LinkedList<>();
        deliveries3.add(new DeliveryPojo(BigDecimal.valueOf(6), 0));
        deliveries3.add(new DeliveryPojo(BigDecimal.valueOf(7), 0));

        List<StagePojo> getActualStages = new LinkedList<>();
        getActualStages.add(new StagePojo(deliveries1));
        getActualStages.add(new StagePojo(deliveries2));
        getActualStages.add(new StagePojo(deliveries3));
        ////
        Integer farmId = 0;
        BigDecimal quantityInStages =
                getActualStages.stream()
                        .flatMap(stage -> stage.getStageDeliveries().stream())
                        .filter(delivery -> delivery.getContractSpecFarmId().equals(farmId))
                        .map(DeliveryPojo::getStagePlanQuantity)
                        .reduce(BigDecimal::add)
                        .orElse(BigDecimal.ZERO);
        System.out.println(quantityInStages);


//                getActualStages.stream()
//                        .forEach(stage -> stage.getStageDeliveries().forEach(delivery -> System.out.println(delivery.getStagePlanQuantity())));
    }

}
