import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HelloServiceTest {
    private HelloService SUT = new HelloService();
    @Test
    public void test_prepareGreeting_null_returnsGreetingWithFallback(){
        //given
        String name=null;
        //when
        String result = SUT.prepareGreeting(name);
        assertEquals("Hello "+HelloService.FALLBACK_NAME+"!",result);
    }
    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName(){
        //given
        String name="test";
        //when
        String result = SUT.prepareGreeting(name);
        assertEquals("Hello "+name+"!",result);
    }
}
