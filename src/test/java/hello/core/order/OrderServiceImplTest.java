package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    @Test
    void createOrder() {
        MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
        memoryMemberRepository.save(new Member(1L, "name", Grade.VIP));
        FixDiscountPolicy fixDiscountPolicy = new FixDiscountPolicy();
        OrderServiceImpl orderService = new OrderServiceImpl(memoryMemberRepository, fixDiscountPolicy);
        Order order = orderService.createOrder(1L, "itemA",
                10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}