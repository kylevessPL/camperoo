package pl.piasta.camperoo.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyBranchDto {
    private String name;

    private String address;

    private String email;

    private String phoneNumber;
}
