import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    private String customerLogin;
    private ArrayList<HashMap> orderList;

    public Order(String customerLogin, ArrayList<HashMap> orderList) {
        this.customerLogin = customerLogin;
        this.orderList = orderList;
    }
}
