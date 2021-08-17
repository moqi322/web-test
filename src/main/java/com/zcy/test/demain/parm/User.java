package com.zcy.test.demain.parm;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;


@Data
@AllArgsConstructor
public class User implements Serializable {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(age, user.age) && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, id);
    }

    private  String name;
    private  Integer age;
    private Long  id;


}
