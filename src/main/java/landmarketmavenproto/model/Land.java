package landmarketmavenproto.model;

import org.springframework.data.annotation.Id;


public class Land {
    @Id
    private String id;

    private String area;
    private String assignment;
    private String price;

    public Land() {
    }

    public Land(String area, String assignment, String price) {
        this.area = area;
        this.assignment = assignment;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }
    public String getPrice(){
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
}

