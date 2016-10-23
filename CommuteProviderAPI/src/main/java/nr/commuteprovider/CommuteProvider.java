package nr.commuteprovider;

public interface CommuteProvider {

    String getCommuteTime(String startLocation, String endLocation, String key);

}
