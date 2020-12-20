package oi.github.samirsales.demoLDAP.api.exception_handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@ApiModel("Problem")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class SwaggerDescriptionProblem {

    @ApiModelProperty(example = "400", position = 1)
    private final Integer status;

    @ApiModelProperty(example = "2020-10-24T15:34:20.6Z", position = 5)
    private final OffsetDateTime timestamp;

    @ApiModelProperty(example = "https://localhost/invalid-data", position = 10)
    private final String type;

    @ApiModelProperty(example = "Invalid data", position = 15)
    private final String title;

    @ApiModelProperty(example = "One or more fields are invalid. Fill in correctly and try again.", position = 20)
    private final String detail;
}