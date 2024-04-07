package qt.qr_backend.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import qt.qr_backend.DTO.OrderDTO;
import qt.qr_backend.DTO.OrderMenuDTO;
import qt.qr_backend.DTO.OrderMenuOptionDTO;
import qt.qr_backend.DTO.OrderMenuRequest;
import qt.qr_backend.domain.Order;
import qt.qr_backend.domain.OrderMenu;
import qt.qr_backend.repository.OrderMenuOptionRepository;
import qt.qr_backend.repository.OrderMenuRepository;
import qt.qr_backend.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderMenuService {

    private final OrderMenuRepository orderMenuRepository;
    private final OrderRepository orderRepository;
    private final OrderMenuOptionRepository orderMenuOptionRepository;

    public OrderMenuDTO saveOrderMenu(OrderMenuDTO orderMenuDTO){
        OrderMenu orderMenu = orderMenuRepository.save(orderMenuDTO.toOrderMenu());
        return OrderMenuDTO.fromOrderMenutoOrderMenuDTO(orderMenu);
    }
    public OrderMenuDTO updateOrderMenu(OrderMenuDTO orderMenuDTO){
        orderMenuRepository.save(OrderMenuDTO.fromOrderMenuDTOtoOrderMenu(orderMenuDTO));
        OrderMenu orderMenu = orderMenuRepository.findNoProxyOrderMenuById(orderMenuDTO.getId());
        return OrderMenuDTO.fromOrderMenutoOrderMenuDTO(orderMenu);
    }

    public List<OrderMenuDTO> saveAllOrderMenu(List<OrderMenuRequest> orderMenuRequestList, OrderDTO orderDTO){
        Order savedOrder = orderRepository.save(OrderDTO.fromOrderDTOtoOrder(orderDTO));
        List<OrderMenuDTO> orderMenuDTOList = orderMenuRequestList.stream().map(l -> new OrderMenuDTO(orderDTO,l.getMenuDTO(), l.getOrderMenuPrice())).toList();
        List<OrderMenuDTO> orderMenuDTOS = OrderMenuDTO.listFromOrderMenutoOrderMenuDTO(orderMenuRepository.saveAll(orderMenuDTOList.stream().map(OrderMenuDTO::toOrderMenu).toList()));
        List<OrderMenuOptionDTO> orderMenuOptionDTOList = new ArrayList<>();

        for (int i=0;i<orderMenuDTOS.size();i++){
            List<OrderMenuOptionDTO> list = orderMenuRequestList.get(i).getOrderMenuOptionDTOList();
            for (OrderMenuOptionDTO orderMenuOptionDTO : list) {
                orderMenuOptionDTOList.add(new OrderMenuOptionDTO(orderMenuDTOS.get(i), orderMenuOptionDTO.getMenuOptionDTO()));
            }
        }
        orderMenuOptionRepository.saveAll(orderMenuOptionDTOList.stream().map(OrderMenuOptionDTO::toOrderMenuOption).toList());
        return orderMenuDTOS;
    }

    public void deleteOrderMenu(String id){
        orderMenuRepository.deleteById(id);
    }
    public OrderMenuDTO findOrderMenuById(String id){
        Optional<OrderMenu> findedOrderMenu = orderMenuRepository.findById(id);
        OrderMenu orderMenu = new OrderMenu();
        if(findedOrderMenu.isPresent()){
            orderMenu.setId(findedOrderMenu.get().getId());
            orderMenu.setOrder(findedOrderMenu.get().getOrder());
            orderMenu.setMenu(findedOrderMenu.get().getMenu());
            orderMenu.setOrderMenuPrice(findedOrderMenu.get().getOrderMenuPrice());
        }
        return OrderMenuDTO.fromOrderMenutoOrderMenuDTO(orderMenu);
    }
    public List<OrderMenuDTO> findOrderMenuListByOrderId(String id){
        List<OrderMenu> findedOrderMenuByOrderId = orderMenuRepository.findByOrder_Id(id);
        return OrderMenuDTO.listFromOrderMenutoOrderMenuDTO(findedOrderMenuByOrderId);
    }
    public List<OrderMenuDTO> findOrderMenuListByMenuId(String id){
        List<OrderMenu> findedOrderMenuByMenuId = orderMenuRepository.findByMenu_Id(id);
        return OrderMenuDTO.listFromOrderMenutoOrderMenuDTO(findedOrderMenuByMenuId);
    }
    public List<OrderMenuDTO> findAllOrderMenu(){
        List<OrderMenu> findAllOrderMenu = orderMenuRepository.findAll();
        return OrderMenuDTO.listFromOrderMenutoOrderMenuDTO(findAllOrderMenu);
    }

}
