package ui.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BirthDate {
    private final String day;
    private final String month;
    private final String year;
}
