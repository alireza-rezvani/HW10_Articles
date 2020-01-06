package ir.maktab32.java.homeworks.hw10articles.config.hibernate.repositories;

import ir.maktab32.java.homeworks.hw10articles.entities.TestEntity;
import ir.maktab32.java.homeworks.hw10articles.repositories.TestEntityRepository;
import ir.maktab32.java.homeworks.hw10articles.repositories.db1.TagRepository;
import org.junit.jupiter.api.*;

//todo check all parts of the class
public class CrudRepositoryTest {

    @BeforeEach
    public void setupTestDatabase(){
        TestEntity defaultEntity = new TestEntity(1l, "Default Title");
        TestEntity defaultEntity2 = new TestEntity(null, "Default Title 2");
        new TestEntityRepository().save(defaultEntity);
        new TestEntityRepository().save(defaultEntity2);
    }

    @Test
    @DisplayName("Save Test")
    public void saveTest(){
        TestEntity testEntity = new TestEntity(null,"Test Entity");
        Assertions.assertTrue(new TestEntityRepository().save(testEntity).getId() == 3);
    }

    @Test
    @DisplayName("Update Test")
    public void updateTest(){
        TestEntity defaultEntity2 = new TestEntity(null, "Default Title 2");
        new TestEntityRepository().save(defaultEntity2);
        TestEntity updatedTestEntity = new TestEntity(1L,"Updated Test Entity");
        new TestEntityRepository().update(updatedTestEntity);
        Assertions.assertTrue(new TestEntityRepository().findById(1L).getTitle().equals("Updated Test Entity"));
        TestEntityRepository.getInstance().findAll().forEach(System.out::println);
    }

    @Test
    @DisplayName("Remove Test")
    public void removeTest(){

        TestEntityRepository.getInstance().remove(TestEntityRepository.getInstance().findById(1L));
        Assertions.assertTrue(!TestEntityRepository.getInstance().isExisting(1L));
    }

    @Test
    @DisplayName("Remove By Id Test")
    public void removeByIdTest(){
        Long generatedId = TestEntityRepository.getInstance().save(new TestEntity(null,"Remove Test")).getId();
        System.out.println("before delete");
        TestEntityRepository.getInstance().findAll().forEach(System.out::println);

        TestEntityRepository.getInstance().removeById(generatedId);

        System.out.println("after delete by id " + generatedId);
        TestEntityRepository.getInstance().findAll().forEach(System.out::println);

        Assertions.assertTrue(!TestEntityRepository.getInstance().isExisting(generatedId));
    }

    @Test
    @DisplayName("Find All Test")
    public void findAllTest(){}

    @Test
    @DisplayName("Find All Test")
    public void findAllByPredicateTest(){}

    @Test
    @DisplayName("Find All Test")
    public void findAllByFunctionTest(){}

    @Test
    @DisplayName("Find By Id Test")
    public void findByIdTest(){}

    @Test
    @DisplayName("Is Existing Test")
    public void isExistingTest(){}
}
