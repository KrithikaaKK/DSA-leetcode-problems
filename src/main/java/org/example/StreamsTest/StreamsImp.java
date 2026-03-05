package org.example.StreamsTest;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamsImp {

    record Employee(String name,List<String> skills){

    }
    record People(String name,int age){

    };

    record Employer(String name,String dept,Integer salary){

    }



    final static List<String> skillsReq = List.of("react","java");

    public static void employeeObj() {
        List<Employee> employees = List.of(new Employee("priya",new ArrayList<>(List.of("java","react","sql"))),
                new Employee("jana",new ArrayList<>(List.of("java","sql","sql"))) ,
                new Employee("ram",new ArrayList<>(List.of("java","react","mongo"))));

        List<People> peoples = List.of(new People("jaanu",20),
                                       new People("ram",21),
                                        new People("selvi",23));
        OptionalInt max = peoples.stream()
                .mapToInt(People::age) // map -> intstream
                .max();


        //to remove duplicates hashset is used
       List<String> names =  employees.stream().filter(employee -> new HashSet<>(employee.skills).containsAll(skillsReq))
               .map(Employee::name).toList();


       System.out.println(names);

       Function<Employee,String> skills = emp -> emp.name;

        System.out.println(employees.stream().map(skills).toList());



    }

    public static void employerObj(){
        List<Employer> employers = new ArrayList<
                >(List.of(
                new Employer("ram", "FE", 450000), new Employer("Raju", "BE", 50000),
                new Employer("Harish", "FE", 600000), new Employer("Karthi", "BE", 90000),
                new Employer("Priya", "DevOPs", 350000)
        ));
//
//        for(Employer em : employers) {
//            employers.add(new Employer("sita",null,null));
//        }


        Map<String, Employer> highestSalaryByDept =
                employers.stream()
                        .collect(Collectors.groupingBy(
                                Employer::dept,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparing(Employer::salary)),
                                        Optional::get
                                )
                        ));



        System.out.println(highestSalaryByDept);

    }



    public static void testStreams () {
        List<String> list = new ArrayList<>(List.of("jana","priya","ram","meeena","aadhi","jana","ram",""));
        // to return the name having greater length
       Optional<String> name =  list.stream().max(Comparator.comparingInt(String::length));
        System.out.println("name having greater length: "+name);






    }

    public static void streamsEx(){

        // to count the frequency of each character in a string
        String s = "krithikaa";

        /// mapToObj can only be applied to intStream,doubleStream,longStream
        /// where we can include primitive type as well
        /// it converts the primitive type to ---> stream<T>(Stream<Object>) as groupingBy can be applied to object stream
        ///  like here it maps the int to char

        ///  while map() - applies to all (primitive and Stream<T>)

        Map<Character,Long> map = s.chars() //intstream..
                .mapToObj(c -> (char)c)//map to the respective char
                .collect(Collectors.groupingBy(
                        c -> c,
                        Collectors.counting()
                ));

        String s1 = "there is a way";






        System.out.println(map);
    }

    public  static void main(String[] args) {

        employeeObj();
        employerObj();
        System.out.println("-----------------------------------------");
        List<String> names = List.of("siva","sneha","jana","priya");
        String s = names.stream()
                        .max(Comparator.comparingInt(String::length)).toString();










        names.stream().sorted(Comparator.reverseOrder()).peek(System.out::println).collect(Collectors.toList());
        List<Integer> nums = List.of(1,4,3,5,6);
        List<List<String>> nams = Arrays.asList(Arrays.asList("hii","bii"),Arrays.asList("bii","hye"));
        nams.stream().flatMap(Collection::stream).map(String::length).forEach(System.out::println);


        nums.stream().sorted(Comparator.reverseOrder()).peek(System.out::println);
        //needs a terminal to trigger the pipeline
        List<String> namess = names.stream().filter(d->d.startsWith("s")).peek(d->System.out.println("starts with s "+s)).toList();

        nums.stream().filter(n -> n>3).peek(System.out::println).forEach(d -> {});


    }
}
