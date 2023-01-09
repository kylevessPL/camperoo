package pl.piasta.camperoo.branch.exception;

import pl.piasta.camperoo.common.exception.ConflictException;
import pl.piasta.camperoo.common.exception.ErrorCode;
import pl.piasta.camperoo.common.exception.ErrorProperty;

public class CompanyBranchesNotAvailableException extends ConflictException {
    public CompanyBranchesNotAvailableException() {
        super(
                "No company branches are available",
                ErrorCode.COMPANY_BRANCHES_NOT_AVAILABLE, ErrorProperty.COMPANY_BRANCHES_NOT_AVAILABLE
        );
    }
}
