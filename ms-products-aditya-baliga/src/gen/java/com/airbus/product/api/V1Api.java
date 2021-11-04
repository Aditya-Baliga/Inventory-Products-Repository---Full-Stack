/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.0).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.airbus.product.api;

import com.airbus.product.model.Error;
import com.airbus.product.model.Product;
import java.util.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-05T01:16:36.928543700+05:30[Asia/Calcutta]")

@Api(value = "v1", description = "the v1 API")
public interface V1Api {

    Logger log = LoggerFactory.getLogger(V1Api.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    @ApiOperation(value = "create a new product", nickname = "createProduct", notes = "creates a new product with given details", response = Product.class, tags={ "Product", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "product created successfully.", response = Product.class),
        @ApiResponse(code = 400, message = "Malformed request (example :Required parameters are not present in the request body).", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal server error", response = Error.class) })
    @RequestMapping(value = "/v1/products/",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Product> createProduct(@ApiParam(value = "details of the product" ,required=true )  @Valid @RequestBody Product body) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default V1Api interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "delete product with given ID", nickname = "deleteProduct", notes = "", tags={ "Product", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "product deleted successfully"),
        @ApiResponse(code = 400, message = "Malformed request (example :Required parameters are not present in the request body).", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal server error", response = Error.class) })
    @RequestMapping(value = "/v1/products/{productId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteProduct(@ApiParam(value = "Unique ID of the product",required=true) @PathVariable("productId") UUID productId) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default V1Api interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "get all the products from the inventory", nickname = "getAllProducts", notes = "request to send all products", response = Product.class, responseContainer = "List", tags={ "Product", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "requested products sent successfully", response = Product.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Malformed request (example :Required parameters are not present in the request body).", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal server error", response = Error.class) })
    @RequestMapping(value = "/v1/products/",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<Product>> getAllProducts(@Min(1)@ApiParam(value = "number of products in the response will be less than or equal to value provided for count", allowableValues = "") @Valid @RequestParam(value = "count", required = false) Integer count) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default V1Api interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "get all the products from the inventory with category - categoryName", nickname = "getAllProductsByCategory", notes = "request to send all products of given categoryName", response = Product.class, responseContainer = "List", tags={ "Product", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "requested products sent successfully", response = Product.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Malformed request (example :Required parameters are not present in the request body).", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal server error", response = Error.class) })
    @RequestMapping(value = "/v1/products/category/{categoryName}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<Product>> getAllProductsByCategory(@ApiParam(value = "category name",required=true) @PathVariable("categoryName") String categoryName,@Min(1)@ApiParam(value = "number of products in the response will be less than or equal to value provided for count", allowableValues = "") @Valid @RequestParam(value = "count", required = false) Integer count) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default V1Api interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "get detail of a product with given ID", nickname = "getProduct", notes = "", response = Product.class, tags={ "Product", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "requested product sent successfully", response = Product.class),
        @ApiResponse(code = 400, message = "Malformed request (example :Required parameters are not present in the request body).", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal server error", response = Error.class) })
    @RequestMapping(value = "/v1/products/{productId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<Product> getProduct(@ApiParam(value = "Unique ID of the product",required=true) @PathVariable("productId") UUID productId) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default V1Api interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "update product with given ID", nickname = "updateProduct", notes = "", response = Product.class, tags={ "Product", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "requested product sent successfully", response = Product.class),
        @ApiResponse(code = 400, message = "Malformed request (example :Required parameters are not present in the request body).", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal server error", response = Error.class) })
    @RequestMapping(value = "/v1/products/{productId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<Product> updateProduct(@ApiParam(value = "details of the product" ,required=true )  @Valid @RequestBody Product body,@ApiParam(value = "Unique ID of the product",required=true) @PathVariable("productId") UUID productId) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default V1Api interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
