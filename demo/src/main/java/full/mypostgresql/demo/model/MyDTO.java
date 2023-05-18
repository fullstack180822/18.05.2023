package full.mypostgresql.demo.model;

public class MyDTO <T,  K>{

    protected T t;
    protected K k;

    public MyDTO() {
    }

    public MyDTO(T t, K k) {
        this.t = t;
        this.k = k;
    }
}
