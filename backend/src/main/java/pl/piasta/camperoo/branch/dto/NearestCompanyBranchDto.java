package pl.piasta.camperoo.branch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NearestCompanyBranchDto {
    private String name;

    private String address;

    private String mail;

    private String phoneNumber;

    private Integer distance;
}
