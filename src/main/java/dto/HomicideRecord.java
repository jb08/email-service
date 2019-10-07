package dto;


import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Builder
@ApiModel("Homicide Record Dto")
public class HomicideRecord {
    private long id;
    @JsonProperty("country_or_area")
    private String countyOrArea;
    private String year;
    private int count;
    private double rate;
    private String source;
    @JsonProperty("source_type")
    private String sourceType;

    public void validate() {
    }
}
