package qt.qr_backend.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import qt.qr_backend.DTO.OrderDTO;
import qt.qr_backend.domain.Order;
import qt.qr_backend.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderDTO saveOrder(OrderDTO orderDTO){
        Order order = orderRepository.save(orderDTO.toOrder());
        return OrderDTO.fromOrdertoOrderDTO(order);
    }

    public OrderDTO updateOrder(OrderDTO orderDTO){
        orderRepository.save(OrderDTO.fromOrderDTOtoOrder(orderDTO));
        Order order = orderRepository.findNoProxyOrderById(orderDTO.getId());
        return OrderDTO.fromOrdertoOrderDTO(order);
    }

    public void deleteOrder(String id){
        orderRepository.deleteById(id);
    }
    public OrderDTO findOrderById(String id){
        Optional<Order> findedOrder = orderRepository.findById(id);
        Order order = new Order();
        if(findedOrder.isPresent()){
            order.setId(findedOrder.get().getId());
            order.setStore(findedOrder.get().getStore());
            order.setOrderDate(findedOrder.get().getOrderDate());
            order.setStatus(findedOrder.get().getStatus());
        }
        return OrderDTO.fromOrdertoOrderDTO(order);
    }
    public List<OrderDTO> findOrderListByStoreId(String id){
        List<Order> findedOrderByStoreId = orderRepository.findByStore_Id(id);
        return OrderDTO.listFromOrdertoOrderDTO(findedOrderByStoreId);
    }
    public List<OrderDTO> findAllOrder(){
        List<Order> findAllOrder = orderRepository.findAll();
        return OrderDTO.listFromOrdertoOrderDTO(findAllOrder);
    }

}
