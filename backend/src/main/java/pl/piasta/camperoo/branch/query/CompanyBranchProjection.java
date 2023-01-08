package pl.piasta.camperoo.branch.query;

import java.math.BigDecimal;

public interface CompanyBranchProjection {
    Long getId();

    String getName();

    String getAddress();

    BigDecimal getLatitude();

    BigDecimal getLongitude();

    String getEmail();

    String getPhoneNumber();
}
