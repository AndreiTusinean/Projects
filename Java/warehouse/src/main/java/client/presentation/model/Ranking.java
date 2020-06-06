package client.presentation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ranking")
public class Ranking {
//id,name,orders,totalamount

    @Id
    //@GeneratedValue(generator = "increment")
    //@GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "orders")
    private int orders;
    @Column(name = "totalamount")
    private int totalamount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public int getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(int totalamount) {
        this.totalamount = totalamount;
    }

    public String toString() {
        return id + " " + name + " " + orders + " " + totalamount ;
    }

    public Ranking(String name, int orders, int totalamount) {
        this.name = name;
        this.orders = orders;
        this.totalamount = totalamount;
    }

    public Ranking() {}

}
