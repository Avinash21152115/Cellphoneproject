package dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CellPhoneUsageByMonth {

    public String employeeId;

    public String date;

    public String totalMinutes;

    public String totalData;
}
