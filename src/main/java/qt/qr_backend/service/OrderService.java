package qt.qr_backend.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import qt.qr_backend.DTO.OrderDTO;
import qt.qr_backend.DTO.OrderMenuDTO;
import qt.qr_backend.domain.exOrder.Order;
import qt.qr_backend.domain.exOrder.OrderMenu;
import qt.qr_backend.repository.OrderMenuRepository;
import qt.qr_backend.repository.OrderRepository;
import qt.qr_backend.repository.StoreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMenuRepository orderMenuRepository;

    public OrderDTO saveOrder(OrderDTO orderDTO){
        Order order = orderRepository.save(orderDTO.toOrder());
        return OrderDTO.fromOrdertoOrderDTO(order);
    }

    public OrderDTO updateOrder(OrderDTO orderDTO){
        Optional<Order> targetOrder = orderRepository.findById(orderDTO.getId());
        if (targetOrder.isPresent()){
            if (orderDTO.getPrice()!=targetOrder.get().getPrice()){
                targetOrder.get().setPrice(orderDTO.getPrice());
            }
            if (orderDTO.getStatus()!=targetOrder.get().getStatus()){
                targetOrder.get().setStatus(orderDTO.getStatus());
            }
        }
        Order savedOrder = orderRepository.save(targetOrder.get());
        return OrderDTO.fromOrdertoOrderDTO(savedOrder);
    }
    public OrderDTO changeOrderStatus(String orderId,String status){
        Optional<Order> targetOrder = orderRepository.findById(orderId);
        if (targetOrder.isPresent()){
            if (targetOrder.get().getStatus()!=status){
                targetOrder.get().setStatus(status);
            }
        }
        Order savedOrder = orderRepository.save(targetOrder.get());
        return OrderDTO.fromOrdertoOrderDTO(savedOrder);
    }

    public void deleteOrder(String id){
        orderRepository.deleteById(id);
    }
    public OrderDTO findOrderById(String id){
        Optional<Order> findedOrder = orderRepository.findById(id);
        Order order = new Order();
        if(findedOrder.isPresent()){
            order.setId(findedOrder.get().getId());
            order.setStoreId(findedOrder.get().getStoreId());
            order.setOrderDate(findedOrder.get().getOrderDate());
            order.setStatus(findedOrder.get().getStatus());
        }
        return OrderDTO.fromOrdertoOrderDTO(order);
    }
    public List<OrderDTO> findOrderListByStoreId(String id){
        List<Order> findedOrderByStoreId = orderRepository.findByStoreId(id);
        return OrderDTO.listFromOrdertoOrderDTO(findedOrderByStoreId);
    }
    public List<OrderDTO> findAllOrder(){
        List<Order> findAllOrder = orderRepository.findAll();
        return OrderDTO.listFromOrdertoOrderDTO(findAllOrder);
    }

    public List<OrderMenuDTO> saveOrderAndOrderMenu(OrderDTO orderDTO, List<OrderMenuDTO> menus) {
        log.info(orderDTO.toString(), menus);
        Order savedOrder = orderRepository.save(orderDTO.toOrder());
        log.info(savedOrder.toString());
        List<OrderMenu> list = menus.stream().map(l -> new OrderMenu(l.getName(), l.getOptions(), l.getAmount(), savedOrder)).toList();
        log.info(list.toString());
        List<OrderMenu> savedOrderMenus = orderMenuRepository.saveAll(list);
        return OrderMenuDTO.listFromOrderMenutoOrderMenuDTO(savedOrderMenus);

    }
}
