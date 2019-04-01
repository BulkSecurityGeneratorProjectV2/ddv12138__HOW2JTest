package tk.ddvudo.Mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.List;

public class OneToMore {
    public static void main(String... args) {
        String resource = "mybatis-config.xml";
        SqlSessionFactory sqlSessionFactory;
        SqlSession session = null;
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource));
            session = sqlSessionFactory.openSession();
            List<Category> cs = session.selectList("listCategory");
            for (Category c : cs) {
                System.out.println(c);
                List<Product> ps = c.getProducts();
                if (null == ps) continue;
                for (Product p : ps) {
                    System.out.println("\t" + p);
                }
            }
            session.commit();
        } catch (IOException e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            if (null != session) {
                session.close();
            }
        }
    }
}
