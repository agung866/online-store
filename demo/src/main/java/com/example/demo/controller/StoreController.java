package com.example.demo.controller;

import com.example.demo.exception.CommonException;
import com.example.demo.model.DataProductRq;
import com.example.demo.model.DataProductRs;
import com.example.demo.model.OrderRq;
import com.example.demo.model.response.ResponseInfo;
import com.example.demo.repository.PurchaseRepository;
import com.example.demo.usecase.ProductUsecase;
import com.example.demo.usecase.PurchaseUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
@Validated
public class StoreController {

    @Autowired
    ProductUsecase productUsecase;
    @Autowired
    PurchaseUsecase purchaseUsecase;

//    @Operation(summary = "Get List Product")
    @GetMapping(value = "/getListProduct", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getListProduct(@RequestHeader(value = "id", required = true) int id
                                            ) throws CommonException {
        /* invoke use case */
        ResponseInfo responseInfo = productUsecase.getDataProduct(id);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }

//    @PostMapping(value = "/addNewProduct", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> insertNewProduct(@RequestHeader(value = "id", required = false) int id,
//                                              @RequestBody @Valid DataProductRs dataProduct
//    ) throws CommonException {
//        /* invoke use case */
//        ResponseInfo responseInfo = productUsecase.insertNewProduct(dataProduct);
//        return new ResponseEntity<>(responseInfo.getBody(),
//                responseInfo.getHttpHeaders(),
//                responseInfo.getHttpStatus());
//    }

    @DeleteMapping(value = "/DeleteProduct", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteProduct(@RequestHeader(value = "id", required = true) int id
    ) throws CommonException {
        /* invoke use case */
        ResponseInfo responseInfo = productUsecase.deleteProduct(id);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }
    @PostMapping(value = "/updateHargaProduct", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatehargaProduct(@RequestHeader(value = "id", required = true) int id,
                                           @RequestHeader(value = "amount", required = true) int amount
    ) throws CommonException {
        /* invoke use case */
        ResponseInfo responseInfo = productUsecase.updateHargaProduct(id,amount);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }

    @PostMapping(value = "/updateStokProduct", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStokProduct(@RequestHeader(value = "id", required = true) int id,
                                           @RequestHeader(value = "stok", required = true) int stok,
                                           @RequestHeader(value = "tambahstok", defaultValue = "true") boolean tambah
    ) throws CommonException {
        /* invoke use case */
        ResponseInfo responseInfo = productUsecase.updateStokProduct(id,stok,tambah);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }

    @PostMapping(value = "/insertNewProduct", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> InsertNewProduct(@RequestBody DataProductRq dataProduct
    ) throws CommonException {
        /* invoke use case */
        ResponseInfo responseInfo = productUsecase.insertNewProduct(dataProduct);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }

}
