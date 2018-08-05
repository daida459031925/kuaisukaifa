package bean.Rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component//可以实现两个一样访问对应监听的hello   这时候就会出现一个线程抢占的问题
////Receiver2  : 92    Receiver1  : 93     Receiver1  : 95
//// Receiver2  : 94    Receiver2  : 97    Receiver1  : 96
//// Receiver2  : 98    Receiver1  : 99
////问题二    这个东西塞入队列后他不会主动消失    也就意味着和map一样   使用过后并且成功后必须要吧对应消息
////从队列中删除 一样没有删除掉的证明没有被消费过。然后出现意外情况后实行数据持久化。
////从而保证数据的最终一致性
//@RabbitListener(queues = "hello")//这里相当于是key，或者是某个队列
public class HelloRid1 {
    @RabbitHandler  //这个地方是接收到的数据。监听对应队列的
    public void process(String hello) {
        System.out.println("Receiver2  : " + hello);
    }
}
