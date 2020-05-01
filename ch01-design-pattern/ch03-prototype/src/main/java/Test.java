import model.Station;
import model.WorkOrder;

public class Test {

    public static void main(String[] args) {

        Station sta = new Station();
        sta.setCode("S3001");
        sta.setName("深汕浮标站");

        WorkOrder wr = new WorkOrder();
        wr.setId("F20200301");
        wr.setAddUser("ghf");
        wr.setStation(sta);

        WorkOrder copyWr = wr.deepClone();
        System.out.println("拷贝的站点：" + copyWr);

        System.out.println("2个工单是否相等: " + (wr == copyWr));
        System.out.println("2个工单的站点是否相等：" + (wr.getStation() == copyWr.getStation()));
    }
}
