package mylab.order.di.xml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
// Spring 컨테이너가 사용할 설정 파일 위치 지정
@ContextConfiguration(locations = "classpath:mylab-order-di.xml")
public class OrderSpringTest {

    // Spring 컨테이너로부터 ShoppingCart 빈을 주입받음
    @Autowired
    private ShoppingCart shoppingCart;

    // Spring 컨테이너로부터 OrderService 빈을 주입받음
    @Autowired
    private OrderService orderService;

    @Test
    @DisplayName("ShoppingCart 빈 테스트")
    void testShoppingCart() {
        // 1. shoppingCart 객체가 Null이 아닌지 검증
        assertNotNull(shoppingCart, "ShoppingCart 빈이 정상적으로 주입되어야 합니다.");

        // 2. shoppingCart에 포함된 Product 리스트 사이즈 검증
        assertEquals(2, shoppingCart.getProducts().size(), "Product 리스트에는 2개의 상품이 있어야 합니다.");

        // 3. Product 정보 검증
        Product product1 = shoppingCart.getProducts().get(0);
        Product product2 = shoppingCart.getProducts().get(1);

        assertEquals("노트북", product1.getName(), "첫 번째 상품의 이름은 '노트북'이어야 합니다.");
        assertEquals("스마트폰", product2.getName(), "두 번째 상품의 이름은 '스마트폰'이어야 합니다.");

        System.out.println("ShoppingCart 테스트 통과: " + shoppingCart.toString());
    }

    @Test
    @DisplayName("OrderService 빈 및 주문 총액 계산 테스트")
    void testOrderService() {
        // 1. orderService 객체가 Null이 아닌지 검증
        assertNotNull(orderService, "OrderService 빈이 정상적으로 주입되어야 합니다.");
        assertNotNull(orderService.getShoppingCart(), "OrderService에 ShoppingCart가 주입되어야 합니다.");

        // 2. 주문 총액 계산 기능 검증
        double expectedTotalPrice = 150000 + 800000; // 950000.0
        double actualTotalPrice = orderService.calculateOrderTotal();

        assertEquals(expectedTotalPrice, actualTotalPrice, "주문 총액이 정확하게 계산되어야 합니다.");

        System.out.println("OrderService 테스트 통과: 주문 총액 = " + actualTotalPrice);
    }
}