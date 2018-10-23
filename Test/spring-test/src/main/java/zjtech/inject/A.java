package zjtech.inject;


public class A<E,T> implements IA<E,T>{

    @Override
    public String say() {
        return "say";
    }
}
