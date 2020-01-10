package ir.maktab32.java.homeworks.hw10articles.config.hibernate.repositories;

import ir.maktab32.java.homeworks.hw10articles.entities.TestEntity;
import ir.maktab32.java.homeworks.hw10articles.repositories.TestEntityRepository;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class CrudRepositoryTest {

    @Test
    @DisplayName("Find All Test")
    public void findAllTest(){

        //create some entities
        TestEntity entity1 = new TestEntity(null,"Test Entity 1");
        TestEntity entity2 = new TestEntity(null,"Test Entity 2");


        //save entities
        TestEntity savedEntity1 = TestEntityRepository.getInstance().save(entity1);
        TestEntity savedEntity2 = TestEntityRepository.getInstance().save(entity2);

        //find all
        List<TestEntity> allEntities = new ArrayList<>(TestEntityRepository.getInstance().findAll());

        //test
        Assertions.assertTrue(
                allEntities.size() ==2
                && allEntities.contains(entity1)
                        && allEntities.contains(entity2)
        );

        //display all
        System.out.println("\n\u29bf All Entities List:");
        TestEntityRepository.getInstance().findAll().forEach(System.out::println);
    }


    @Test
    @DisplayName("Find By Id Test")
    public void findByIdTest(){
        //create some entities
        TestEntity entity1 = new TestEntity(null,"Test Entity 1");
        TestEntity entity2 = new TestEntity(null,"Test Entity 2");
        TestEntity entity3 = new TestEntity(null,"Test Entity 3");


        //save entities
        TestEntity savedEntity1 = TestEntityRepository.getInstance().save(entity1);
        TestEntity savedEntity2 = TestEntityRepository.getInstance().save(entity2);
        TestEntity savedEntity3 = TestEntityRepository.getInstance().save(entity3);

        //test
        Assertions.assertTrue(
                TestEntityRepository.getInstance().findById(2L).getTitle().equals("Test Entity 2")
        );


        //display founded Entity
        System.out.println("\n\u29bf Founded Entity By Id: 2");
        System.out.println(TestEntityRepository.getInstance().findById(2L));

    }


    @Test
    @DisplayName("Save Test")
    public void saveTest(){
        //save an entity
        TestEntity entity1 = TestEntityRepository.getInstance().save(new TestEntity(null,"Test Entity"));

        //save another entity to test
        TestEntity entity2 =  TestEntityRepository.getInstance().save(new TestEntity(null, "Save Test"));

        //test
        Assertions.assertTrue(entity2.getId() == 2l);

        //display all saved entities
        System.out.println("\n\u29bf All Entities List:");
        TestEntityRepository.getInstance().findAll().forEach(System.out::println);
    }

    @Test
    @DisplayName("Update Test")
    public void updateTest(){
        //save an entity
        TestEntity savedEntity = TestEntityRepository.getInstance().save(new TestEntity(null,"Test"));

        //edit saved entity and Update
        savedEntity.setTitle("Update Test");
        TestEntityRepository.getInstance().update(savedEntity);

        //test
        Assertions.assertTrue(TestEntityRepository.getInstance().findById(1L).getTitle().equals("Update Test"));

        //display all saved entities
        System.out.println("\n\u29bf All Entities List:");
        TestEntityRepository.getInstance().findAll().forEach(System.out::println);
    }


    @Test
    @DisplayName("Is Existing Test")
    public void isExistingTest(){
        //save an entity
        TestEntity savedEntity1 = TestEntityRepository.getInstance().save(new TestEntity(null,"Test"));

        //test
        Assertions.assertTrue(
                TestEntityRepository.getInstance().isExisting(1L)
                        && !TestEntityRepository.getInstance().isExisting(2L)
        );

        //display all
        System.out.println("\n\u29bf All Entities List:");
        TestEntityRepository.getInstance().findAll().forEach(System.out::println);
    }

    @Test
    @DisplayName("Remove Test")
    public void removeTest(){
        //save some entities
        TestEntity savedEntity1 = TestEntityRepository.getInstance().save(new TestEntity(null,"Test 1"));
        TestEntity savedEntity2 = TestEntityRepository.getInstance().save(new TestEntity(null,"Test 2"));

        //display all entities
        System.out.println("\n\u29bf All Entities List Before Remove:");
        TestEntityRepository.getInstance().findAll().forEach(System.out::println);

        //remove first Entity
        TestEntityRepository.getInstance().remove(TestEntityRepository.getInstance().findById(1L));

        //display all entities
        System.out.println("\n\u29bf All Entities List After Remove:");
        TestEntityRepository.getInstance().findAll().forEach(System.out::println);

        //test
        Assertions.assertTrue(!TestEntityRepository.getInstance().isExisting(1L));
    }

    @Test
    @DisplayName("Remove By Id Test")
    public void removeByIdTest(){

        //save some entities
        TestEntity savedEntity1 = TestEntityRepository.getInstance().save(new TestEntity(null,"Test 1"));
        TestEntity savedEntity2 = TestEntityRepository.getInstance().save(new TestEntity(null,"Test 2"));

        //display all entities
        System.out.println("\n\u29bf All Entities List Before Remove:");
        TestEntityRepository.getInstance().findAll().forEach(System.out::println);

        //remove first entity
        TestEntityRepository.getInstance().removeById(savedEntity1.getId());

        //display all entities
        System.out.println("\n\u29bf All Entities List After Removing Id: " + savedEntity1.getId());
        TestEntityRepository.getInstance().findAll().forEach(System.out::println);

        //test
        Assertions.assertTrue(!TestEntityRepository.getInstance().isExisting(savedEntity1.getId()));
    }



    @Test
    @DisplayName("Find All By Predicate Test")
    public void findAllByPredicateTest(){
        //create some entities
        TestEntity entity1 = new TestEntity(null,"Java");
        TestEntity entity2 = new TestEntity(null,"JavaScript");
        TestEntity entity3 = new TestEntity(null,"Android");


        //save entities
        TestEntity savedEntity1 = TestEntityRepository.getInstance().save(entity1);
        TestEntity savedEntity2 = TestEntityRepository.getInstance().save(entity2);
        TestEntity savedEntity3 = TestEntityRepository.getInstance().save(entity3);

        //declare predicate
        Predicate<TestEntity> predicate = s -> s.getTitle().contains("Java");

        //find list of all entities
        List<TestEntity> allEntities = new ArrayList<>(TestEntityRepository.getInstance().findAll());

        //test
        List<TestEntity> result = TestEntityRepository.getInstance().findAll(predicate);
        Assertions.assertTrue(result.size() == 2 && !result.contains(savedEntity3));

        //display
        System.out.println("\n\u29bf All Entities List:");
        TestEntityRepository.getInstance().findAll().forEach(System.out::println);
        System.out.println("\n\u29bf After Filtering: ");
        result.forEach(System.out::println);
    }

    @Test
    @DisplayName("Find All By Function Test")
    public void findAllByFunctionTest(){
        //create some entities
        TestEntity entity1 = new TestEntity(null,"Java");
        TestEntity entity2 = new TestEntity(null,"JavaScript");
        TestEntity entity3 = new TestEntity(null,"Android");


        //save entities
        TestEntity savedEntity1 = TestEntityRepository.getInstance().save(entity1);
        TestEntity savedEntity2 = TestEntityRepository.getInstance().save(entity2);
        TestEntity savedEntity3 = TestEntityRepository.getInstance().save(entity3);

        //declare function
        Function<TestEntity, String> function = testEntity -> testEntity.getTitle();

        //find list of all entities
        List<TestEntity> allEntities = new ArrayList<>(TestEntityRepository.getInstance().findAll());

        //test
        List<String> result = TestEntityRepository.getInstance().findAll(function);
        Assertions.assertTrue(result.size() == 3 && result.get(1).equals("JavaScript"));

        //display
        System.out.println("\n\u29bf List Before Mapping:");
        TestEntityRepository.getInstance().findAll().forEach(System.out::println);
        System.out.println("\n\u29bf List After Mapping:");
        result.forEach(System.out::println);
    }


}
