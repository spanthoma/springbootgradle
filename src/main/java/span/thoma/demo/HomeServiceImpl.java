package span.thoma.demo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017-08-24.
 */

@Service
public class HomeServiceImpl implements HomeService{

    @Cacheable(value = "BLOG:HomeService:Home")
    @Override
    public void getHome() {
        System.out.println("WelCome Home");
    }

    @Cacheable(value = "BLOG:HomeService:Test")
    @Override
    public void test() {
        System.out.println("Test");
    }

    @Override
    public void test3() {
        test();
    }
}
