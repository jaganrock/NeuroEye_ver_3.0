package ragul.srushty.com.neuroeye.data;

/**
 * Created by karthi2 on 9/15/2016.
 */
public class CarProducer {

    private final String name;
    private final int logoRes;

    public CarProducer(final int logoRes, final String name) {
        this.logoRes = logoRes;
        this.name = name;
    }

    public int getLogo() {
        return logoRes;
    }

    public String getName() {
        return name;
    }
}
