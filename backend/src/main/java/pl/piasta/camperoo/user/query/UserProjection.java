package pl.piasta.camperoo.user.query;

public interface UserProjection extends UserBasicProjection {
    PersonProjection getPerson();

    interface PersonProjection extends PersonBasicProjection {
        String getAddressOne();

        String getAddressTwo();

        String getZipCode();

        String getCity();

        String getPhoneNumber();
    }
}
