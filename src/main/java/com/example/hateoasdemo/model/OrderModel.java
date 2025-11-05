package com.example.hateoasdemo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderModel extends RepresentationModel<OrderModel> {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("status")
    private String status; // Pending | Paid | Cancelled

    @JsonProperty("total")
    private Double total;

    public OrderModel(Long id, String status, Double total) {
        this.id = id;
        this.status = status;
        this.total = total;
    }

    public Long getId() { 
        return id; 
    }

    public String getStatus() { 
        return status; 
    }

    public Double getTotal() { 
        return total; 
    }
}
