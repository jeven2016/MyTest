package zjtech.inject;

import org.springframework.stereotype.Component;

@Component
public class ComponetBean<D,T> {

    private D name;
    private T age;

    public D getName() {
        return name;
    }

    public void setName(D name) {
        this.name = name;
    }

    public T getAge() {
        return age;
    }

    public void setAge(T age) {
        this.age = age;
    }
}
