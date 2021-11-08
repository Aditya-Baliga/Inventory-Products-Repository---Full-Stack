package com.airbus.product.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Generic error object
 */
@ApiModel(description = "Generic error object")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-08T17:04:08.675430400+05:30[Asia/Calcutta]")

public class Error   {
  @JsonProperty("message")
  private String message = null;

  @JsonProperty("origin")
  private String origin = null;

  @JsonProperty("rootCause")
  private String rootCause = null;

  @JsonProperty("errorCode")
  private String errorCode = null;

  public Error message(String message) {
    this.message = message;
    return this;
  }

  /**
   * contains the particular error message of the origin
   * @return message
  **/
  @ApiModelProperty(required = true, value = "contains the particular error message of the origin")
  @NotNull


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Error origin(String origin) {
    this.origin = origin;
    return this;
  }

  /**
   * denotes the origin of the error
   * @return origin
  **/
  @ApiModelProperty(required = true, value = "denotes the origin of the error")
  @NotNull


  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public Error rootCause(String rootCause) {
    this.rootCause = rootCause;
    return this;
  }

  /**
   * provides the root cause of the error
   * @return rootCause
  **/
  @ApiModelProperty(value = "provides the root cause of the error")


  public String getRootCause() {
    return rootCause;
  }

  public void setRootCause(String rootCause) {
    this.rootCause = rootCause;
  }

  public Error errorCode(String errorCode) {
    this.errorCode = errorCode;
    return this;
  }

  /**
   * http status code for return value. There are common codes for all methods - \\'403\\', denoting forbidden access, \\'500\\' for internal server issue
   * @return errorCode
  **/
  @ApiModelProperty(required = true, value = "http status code for return value. There are common codes for all methods - \\'403\\', denoting forbidden access, \\'500\\' for internal server issue")
  @NotNull


  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error error = (Error) o;
    return Objects.equals(this.message, error.message) &&
        Objects.equals(this.origin, error.origin) &&
        Objects.equals(this.rootCause, error.rootCause) &&
        Objects.equals(this.errorCode, error.errorCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message, origin, rootCause, errorCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    origin: ").append(toIndentedString(origin)).append("\n");
    sb.append("    rootCause: ").append(toIndentedString(rootCause)).append("\n");
    sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

