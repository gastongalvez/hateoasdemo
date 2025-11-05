package com.example.hateoasdemo.controller;

import com.example.hateoasdemo.model.OrderModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    // Obtener una orden específica
    @GetMapping("/{id}")
    public ResponseEntity<OrderModel> getOrder(@PathVariable Long id) {
        OrderModel order = new OrderModel(id, getStatusById(id), 150.99);

        // Enlaces base
        order.add(linkTo(methodOn(OrderController.class).getOrder(id)).withSelfRel());
        order.add(linkTo(methodOn(OrderController.class).getAllOrders()).withRel("list_all"));

        // Enlaces según el estado
        switch (order.getStatus()) {
            case "Pending":
                order.add(linkTo(methodOn(OrderController.class).payOrder(id)).withRel("pay"));
                order.add(linkTo(methodOn(OrderController.class).cancelOrder(id)).withRel("cancel"));
                break;
            case "Paid":
                order.add(linkTo(methodOn(OrderController.class).trackShipment(id)).withRel("track_shipment"));
                order.add(linkTo(methodOn(OrderController.class).refundOrder(id)).withRel("refund"));
                break;
            case "Cancelled":
                // sin acciones adicionales
                break;
        }

        return ResponseEntity.ok(order);
    }

    // Obtener todas las órdenes
    @GetMapping
    public List<OrderModel> getAllOrders() {
        List<OrderModel> orders = Arrays.asList(
                new OrderModel(1L, "Pending", 100.0),
                new OrderModel(2L, "Paid", 200.0),
                new OrderModel(3L, "Cancelled", 150.0)
        );

        // Agregar enlace a cada orden
        orders.forEach(order -> {
            order.add(linkTo(methodOn(OrderController.class).getOrder(order.getId())).withSelfRel());
        });

        return orders;
    }

    // Simular pago
    @PostMapping("/{id}/pay")
    public ResponseEntity<String> payOrder(@PathVariable Long id) {
        return ResponseEntity.ok("Orden " + id + " pagada correctamente.");
    }

    // Simular cancelación
    @DeleteMapping("/{id}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable Long id) {
        return ResponseEntity.ok("Orden " + id + " cancelada correctamente.");
    }

    // Simular seguimiento de envío
    @GetMapping("/{id}/track")
    public ResponseEntity<String> trackShipment(@PathVariable Long id) {
        return ResponseEntity.ok("Seguimiento iniciado para la orden " + id);
    }

    // Simular reembolso
    @PostMapping("/{id}/refund")
    public ResponseEntity<String> refundOrder(@PathVariable Long id) {
        return ResponseEntity.ok("Reembolso procesado para la orden " + id);
    }

    // Método auxiliar para estados de ejemplo
    private String getStatusById(Long id) {
        if (id == 1L) return "Pending";
        if (id == 2L) return "Paid";
        return "Cancelled";
    }
}
