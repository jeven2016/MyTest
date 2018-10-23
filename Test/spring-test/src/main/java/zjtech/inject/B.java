package zjtech.inject;

import org.springframework.stereotype.Service;

@Service("B")
public class B<E, T> extends A<E, T> implements IB {
}
