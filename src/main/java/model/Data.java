package model;

import java.io.Serializable;

public class Data<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    Integer opt;
    T object;
    int change;
    Long id;
    Boolean result;

    public Integer getOpt() {
        return opt;
    }

    public T getObject() {
        return object;
    }

    public int getChange() {
        return change;
    }

    public Boolean getResult() {
        return result;
    }

    public void setOpt(Integer opt) {
        this.opt = opt;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
