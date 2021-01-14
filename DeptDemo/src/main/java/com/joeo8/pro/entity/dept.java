package com.joeo8.pro.entity;

import java.util.Objects;

public class dept {
    private Integer d_id;
    private String d_name ;
    private String d_loc;


    @Override
    public String toString() {
        return "\t\t" + d_id +
                "\t\t" + d_name +
                "\t\t" + d_loc
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        dept dept = (dept) o;
        return Objects.equals(d_id, dept.d_id) && Objects.equals(d_name, dept.d_name) && Objects.equals(d_loc, dept.d_loc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(d_id, d_name, d_loc);
    }

    public Integer getD_id() {
        return d_id;
    }

    public void setD_id(Integer d_id) {
        this.d_id = d_id;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String getD_loc() {
        return d_loc;
    }

    public void setD_loc(String d_loc) {
        this.d_loc = d_loc;
    }

    public dept(Integer d_id, String d_name, String d_loc) {
        this.d_id = d_id;
        this.d_name = d_name;
        this.d_loc = d_loc;
    }

    public dept() {
    }
}
