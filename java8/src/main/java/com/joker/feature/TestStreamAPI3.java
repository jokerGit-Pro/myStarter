package com.joker.feature;


import org.junit.Test;

import java.security.cert.CollectionCertStoreParameters;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 终止操作
 */
public class TestStreamAPI3 {
    /**
     * 查找与匹配
     * allMatch--检查是否匹配所有方法
     * anyMatch--检查是否至少匹配一个元素
     * noneMatch--是否没有匹配所有元素
     * findFirst--返回第一个元素
     * findAny--返回当前流中的任意元素
     * count--返回流中元素的总个数
     * max--返回流中最大值
     * min--返回流中最小值
     */
    List<Employee> employees =
            Arrays.asList(
                    new Employee("张三", 18, 9999.99, Employee.State.BUSY),
                    new Employee("李四", 58, 5555.55,Employee.State.FREE),
                    new Employee("王五", 26, 3333.33,Employee.State.VOCATION),
                    new Employee("赵六", 36, 6666.66, Employee.State.BUSY),
                    new Employee("田七", 12, 8888.88, Employee.State.FREE)
            );

    @Test
    public void test1(){
        boolean match = employees.stream()
                .allMatch(e -> e.getState().equals(Employee.State.BUSY));
        System.out.println("是不是匹配所有:"+match);

        boolean b = employees.stream()
                .anyMatch(e -> e.getState().equals(Employee.State.FREE));
    System.out.println("是否匹配一个:"+b);

        boolean b3 = employees.stream().noneMatch(e -> e.getState().equals(Employee.State.VOCATION));
        System.out.println("是不是没有匹配的"+b3);

        Optional<Employee> first = employees.stream().sorted((e1, e2) ->
            -Double.compare(e1.getSalary(), e2.getSalary())
        ).findFirst();
        if(first.isPresent()){
            System.out.println("谁的薪资高:"+first.get());
        }

        Optional<Employee> optional = employees.parallelStream()
                .filter((e) -> e.getState().equals(Employee.State.FREE))
                .findFirst();
    System.out.println("找到的处于空闲状态的:"+optional.get());
    }

    @Test
    public void test2(){
        long count = employees.stream().count();
        System.out.println("流中有元素:"+count);

        Optional<Employee> max = employees.stream().max((e1, e2) ->
                Double.compare(e1.getSalary(), e2.getSalary())
        );
    System.out.println("薪资最高的是:"+max.get());


        Optional<Double> min = employees.stream().map(Employee::getSalary)
                .min(Double::compareTo);

        System.out.println("工资最少的薪资是:"+min.get());
    }

    /**
     * 归约
     * reduce:将流中元素反复结合,得到一个值
     */
    @Test
    public void test3(){
        List<Integer> list=Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println("和为:"+sum);

        Optional<Double> reduce = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(""+reduce.get());
    }

    /**
     * 收集
     * collect--流转换为其他形式,接收一个Collector接口的实现
     * 用于给Stream中元素汇总的方法
     */
    @Test
    public void test4(){
        List<String> collect = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        collect.forEach(System.out::println);


        HashSet<String> strings = employees.stream().map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        strings.forEach(System.out::println);
    }

    @Test
    public void test5(){
        Long collect = employees.stream()
                .collect(Collectors.counting());
        System.out.println("总数:"+collect);

        Double collect1 = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));

        System.out.println("平均薪资"+collect1);

        Double collect2 = employees.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println("工资总和:"+collect2);

        //最大值
        Optional<Employee> collect3 = employees.stream().collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println("薪资最高的员工是"+collect3.get());

        //最小值
        Optional<Double> collect4 = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compareTo));
        System.out.println("最小薪资是:"+collect4.get());

    }

    //分组
    @Test
    public void test6(){
        Map<Employee.State, List<Employee>> collect = employees.stream()
                .collect(Collectors.groupingBy(Employee::getState));
        Iterator it=collect.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry next = (Map.Entry) it.next();
            Employee.State key = (Employee.State) next.getKey();
            List<Employee> value = (List<Employee>) next.getValue();
            System.out.println("key="+key+",value="+value);
        }
    }

    //多级分组
    @Test
    public void test7(){
        Map<Employee.State, Map<String, List<Employee>>> collect = employees.stream().collect(Collectors.groupingBy(Employee::getState, Collectors.groupingBy(e -> {
            if (((Employee) e).getAge() <= 35) {
                return "青年";
            } else if (((Employee) e).getAge() < 50) {
                return "中年";
            } else {
                return "老年";
            }
        })));
        Iterator it=collect.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry next = (Map.Entry) it.next();
            Employee.State state=(Employee.State)next.getKey();
            Map<String, List<Employee>> listMap=(Map<String, List<Employee>>)next.getValue();
            Iterator uter=listMap.entrySet().iterator();
            System.out.println("key="+state+",value="+listMap);
            while (uter.hasNext()){
                Map.Entry nexts=(Map.Entry)uter.next();
                String emList=(String)nexts.getKey();
                List<Employee> value = (List<Employee>) nexts.getValue();

            }


        }
    }

    //分片(分区)
    @Test
    public void test8(){
        Map<Boolean, List<Employee>> collect = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 7000));
        Iterator it=collect.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry sala=(Map.Entry)it.next();
            boolean key=(boolean)sala.getKey();
            List<Employee> value = (List<Employee>) sala.getValue();
            System.out.println("key="+key+",value="+value);
        }
    }

    @Test
    public void test9(){
        DoubleSummaryStatistics collect = employees.stream().collect(Collectors.summarizingDouble(Employee::getSalary));

        System.out.println(collect.getMax());
        System.out.println(collect.getAverage());
        System.out.println(collect.getCount());
        System.out.println(collect.getSum());


    }

    @Test
    public void test10(){
        String collect = employees.stream().map(Employee::getName)
                .collect(Collectors.joining(",", "====", "=++++"));
        System.out.println(collect);
    }

}
