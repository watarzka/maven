import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {
    private static final String WELCOME = "Hello";
    private static final String FALLBACK_ID_WELCOME = "Hola";
    @Test
    public void test_prepareGreeting_nullName_returnsGreetingWithFallbackName(){
        //given
        LangRepository mockRepository = alwawysReturningHelloRepository();
        HelloService SUT = new HelloService(mockRepository);
        String name=null;
        //when
        String result = SUT.prepareGreeting(name,"-1");
        assertEquals(WELCOME+" "+HelloService.FALLBACK_NAME+"!",result);
    }

    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName(){
        //given
        LangRepository mockRepository = alwawysReturningHelloRepository();
        HelloService SUT = new HelloService(mockRepository);
        String name="test";
        //when
        String result = SUT.prepareGreeting(name,"-1");
        assertEquals("Hello "+name+"!",result);
    }
    @Test
    public void test_prepareGreeting_nullLang_returnsGreetingWithFallbackIdLang(){
        //given
        LangRepository mockRepository = fallbackLangIdRepository();
        HelloService SUT = new HelloService(mockRepository);
        String name="test";
        //when
        String result = SUT.prepareGreeting(null,null);
        assertEquals(FALLBACK_ID_WELCOME+" "+HelloService.FALLBACK_NAME+"!",result);
    }
    @Test
    public void test_prepareGreeting_textLang_returnsGreetingWithFallbackIdLang(){
        //given
        LangRepository mockRepository = fallbackLangIdRepository();
        HelloService SUT = new HelloService(mockRepository);
        String name="test";
        //when
        String result = SUT.prepareGreeting(null,"abc");
        assertEquals(FALLBACK_ID_WELCOME+" "+HelloService.FALLBACK_NAME+"!",result);
    }
    @Test
    public void test_prepareGreeting_nonExisting_returnsGreetingWithFallbackLang()
    {
        LangRepository mockRepository = new LangRepository(){
            @Override
            Optional<Lang> findById(Integer id) {
                return Optional.empty();
            }
        };
        HelloService SUT = new HelloService(mockRepository);

        //when
        String result = SUT.prepareGreeting(null,"-1");
        //then
        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg()+" "+HelloService.FALLBACK_NAME+"!",result);
    }
    private LangRepository fallbackLangIdRepository() {
        return new LangRepository(){
            @Override
            Optional<Lang> findById(Integer id) {
               if(id.equals(HelloService.FALLBACK_LANG.getId())) {
                   return Optional.of(new Lang(null, FALLBACK_ID_WELCOME, null));
               }
               return Optional.empty();
            }
        };
    }

    private LangRepository alwawysReturningHelloRepository() {
        return new LangRepository(){
            @Override
            Optional<Lang> findById(Integer id) {
                return Optional.of(new Lang(null,WELCOME, null ));
            }
        };
    }
}
