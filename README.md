# 마이바티스 프로그래밍 원리와 활용

- '마이바티스 프로그래밍 원리와 활용'을 보고 GPT4를 활용해서 만든 학습자료입니다.
- '마이바티스 프로그래밍 원리와 활용'은 Oracle, MyBatis, Spring, JSP가 사용되었습니다.
- 본 Respository는 개인 학습을 위해서 PostgreSQL, MyBatis, SpringBoot, Thymeleaf을 활용하였고, 코드는 직접 작성했습니다. Spring이랑 Tomcat을 활용한 환경설정을 하기가 싫네요... 해보실 분은 책 구매해서 해보면 좋을 것 같아요.

## 목차

- [1. 마이바티스 소개](#1-마이바티스-소개)
- [2. 마이바티스 프로그래밍 시작](#2-마이바티스-프로그래밍-시작)
- [3. 데이터 관리를 위한 마이바티스 프로그래밍](#3-데이터-관리를-위한-마이바티스-프로그래밍)
- [4. 마이바티스 설정](#4-마이바티스-설정)
- [5. 마이바티스 매핑 구문 정의](#5-마이바티스-매핑-구문-정의)
- [6. 마이바티스 객체](#6-마이바티스-객체)
- [7. 마이바티스와 웹 애플리케이션](#7-마이바티스와-웹-애플리케이션)
- [8. 마이바티스와 스프링 연동 웹 애플리케이션](#8-마이바티스와-스프링-연동-웹-애플리케이션)

## 1. 마이바티스 소개

### MyBatis와 JDBC의 차이

#### 개요

- JDBC는 자바 애플리케이션을 데이터베이스와 연결하기 위한 Java API. 이는 데이터베이스와 직접적으로 상호작용하는 기본적인 방법을 제공함.

#### 장점

- **직접적인 제어**: JDBC를 사용하면 개발자는 SQL 쿼리를 직접 작성하고, 실행 시점을 정확하게 제어할 수 있음. 이는 세밀한 최적화와 디버깅에 유리함.
- **포괄적인 지원**: 거의 모든 데이터베이스 제공업체에서 JDBC 드라이버를 제공하므로, 다양한 데이터베이스에 적용할 수 있음.

#### 단점

- **코드 중복**: CRUD(Create, Read, Update, Delete) 작업을 반복해서 수행할 때, 많은 보일러플레이트 코드를 작성해야 함.
- **유지보수 어려움**: SQL 쿼리가 자바 코드 안에 직접적으로 작성되기 때문에, 코드가 복잡해지고 유지보수가 어려워질 수 있음.

### MyBatis

#### 개요

- MyBatis는 JDBC 위에 구축된 데이터 매핑 프레임워크로, 데이터베이스 레코드와 자바 객체 간의 매핑을 용이하게 함.

#### 장점

- **간결한 SQL 관리**: SQL 쿼리를 XML 파일이나 애노테이션으로 분리하여 관리할 수 있어, 코드가 더 깔끔하고 유지보수하기 쉬움.
- **객체 매핑 용이**: 자동 매핑 기능 덕분에, 데이터베이스 레코드를 자바 객체로 쉽게 변환할 수 있음. 이는 개발 속도를 향상시키고, 오류 가능성을 줄임.
- **유연성**: MyBatis는 복잡한 조인이나 트랜잭션 관리 등 복잡한 SQL 작업을 수행하는 데 필요한 유연성을 제공함.

#### 단점

- **학습 곡선**: MyBatis를 효과적으로 사용하기 위해서는 그 자체적인 구조와 문법을 학습해야 함.
- **초기 설정이 복잡함**: 프로젝트에 MyBatis를 처음 설정할 때 필요한 구성이 많음.

### MyBatis

#### 개요

- MyBatis는 자바(JAVA) 언어를 사용하는 데이터베이스 프레임워크로, JDBC로 처리하는 상당 부분의 코드와 파라미터 설정 및 결과 매핑을 대신 해줌으로써 보다 쉽고 명확하게 데이터베이스와의 상호작용을 가능하게 함. MyBatis를 사용함으로써 개발자는 데이터베이스와의 데이터 교환과 관련된 상당량의 코드 작성 부담을 줄일 수 있음.

#### 특징

- **데이터 매핑의 용이성**: MyBatis는 데이터베이스의 결과를 자바 객체로 쉽게 매핑할 수 있도록 해줌. 이는 복잡한 조인이나 복잡한 트랜잭션 처리에 있어서 코드의 가독성과 유지보수성을 크게 향상시킴.
- **SQL과 코드의 분리**: SQL 문을 자바 코드에서 분리하여 XML 파일이나 애너테이션으로 관리할 수 있음. 이로써 비즈니스 로직과 데이터베이스 로직이 분리되어, 각각 독립적으로 관리되고 변경될 수 있음.
- **유연성과 확장성**: MyBatis는 복잡한 SQL 쿼리나 다양한 데이터베이스 연동 작업을 유연하게 처리할 수 있으며, 동적 SQL을 지원하여 상황에 따라 SQL을 변경할 수 있는 기능을 제공함.
- **통합 지원**: Spring Framework와 같은 다른 자바 기반 프레임워크와의 통합이 용이함. 특히, Spring Boot와의 결합을 통해 개발 프로세스를 더욱 신속하고 효율적으로 처리할 수 있음.

#### 사용 방법

- **설정 파일**: MyBatis는 mybatis-config.xml 설정 파일에서 데이터베이스 연결 정보, 트랜잭션 관리 방법 등을 설정함.
- **매퍼 파일**: SQL 쿼리는 XML 형태의 매퍼 파일에 저장됨. 각 매퍼 파일은 데이터베이스 테이블과 매핑되는 여러 SQL 명령어들을 포함함.
- **세션 관리**: SQL 명령어 실행을 위해 MyBatis는 SqlSession을 사용함. 이 세션을 통해 쿼리를 실행하고 데이터를 조작함.

#### 예제 코드

```xml
<!-- MyBatis 매퍼 파일 -->
<mapper namespace="com.example.mapper.UserMapper">
    <select id="selectUsers" resultType="com.example.User">
        SELECT id, name, email FROM users
    </select>
</mapper>
```

```java
// MyBatis 사용 예시
try (SqlSession session = sqlSessionFactory.openSession()) {
    UserMapper mapper = session.getMapper(UserMapper.class);
    List<User> users = mapper.selectUsers();
    for (User user : users) {
        System.out.println(user.getName());
    }
}
```

### 전체적인 코드 설명

- PostgreSQL, JDBC, Thymeleaf, SpringBoot를 이용한 간단한 상점 추가 및 조회 사이트.
- JDBC를 통해 직접 데이터와 매핑을 해봄으로써 JDBC를 이해하고, 추후에 MyBatis로 단순화 되는 것을 확인하는데 목적이 있음.

## 2. 마이바티스 프로그래밍 시작

### MyBatis 구성

- **MyBatis Mapper XML**: SQL 쿼리와 그에 대응하는 메소드를 연결하는 매핑 정보를 담고 있음.
- **MyBatis Settings XML**: MyBatis 동작 설정을 위한 파일로, 데이터베이스 연결과 트랜잭션 관리 방법 등을 설정할 수 있음.
- **실행 클래스**: 실제 비즈니스 로직을 수행하는 클래스로, Mapper를 호출하여 데이터베이스 연산을 실행함.
- **실행 및 로깅 설정**: 애플리케이션의 실행 정보를 로깅하는 방법을 설정함.

### MyBatis Mapper XML 작성하기

- **파일명 명명 규칙**: 일반적으로 Mapper의 기능을 나타내는 이름을 사용함. 예를 들어, `UserMapper.xml`과 같이 특정 도메인 객체 또는 테이블명을 기준으로 명명함.
- **Parameter**: SQL 쿼리에 전달될 입력 파라미터를 지정합니다. parameterType 속성으로 전달될 객체의 타입을 지정하거나, parameterMap을 사용하여 복수의 데이터를 맵 형태로 전달할 수 있음.

```xml
    <!-- 단일 파라미터 전달 예제 -->
<mapper namespace="org.myapp.mapper.UserMapper">
    <select id="selectUserById" parameterType="int" resultType="org.myapp.model.User">
        SELECT id, username, email FROM users WHERE id = #{id}
    </select>
</mapper>

<!-- 맵 파라미터 전달 예제 -->
<mapper namespace="org.myapp.mapper.UserMapper">
    <insert id="insertUser" parameterType="map">
        INSERT INTO users (username, email, phone) VALUES (#{username}, #{email}, #{phone})
    </insert>
</mapper>
```

- **Result**: SQL 쿼리의 결과를 매핑하는 설정. resultType 속성은 쿼리 결과를 자바 클래스로 매핑하고, resultMap을 사용하여 좀 더 복잡한 결과 구조를 매핑할 수 있음.

```xml
<!-- 결과 타입 매핑 예제 -->
<mapper namespace="org.myapp.mapper.UserMapper">
    <select id="selectAllUsers" resultType="org.myapp.model.User">
        SELECT id, username, email FROM users
    </select>
</mapper>

<!-- 결과 맵 매핑 예제 -->
<mapper namespace="org.myapp.mapper.UserMapper">
    <resultMap id="userResultMap" type="org.myapp.model.User">
        <id column="id" property="userId"/>
        <result column="username" property="username"/>
        <result column="email" property="email"/>
    </resultMap>

    <select id="selectUserWithDetail" resultMap="userResultMap">
        SELECT u.id, u.username, u.email FROM users u
        WHERE u.id = #{id}
    </select>
</mapper>
```

### MyBatis Config XML 작성하기

- MyBatis의 전체적인 설정을 담당하는 중요한 파일. 이 설정 파일에서는 데이터베이스 연결 정보, 트랜잭션 관리 설정, Mapper XML 파일의 위치 등을 지정할 수 있음.

```xml
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mydatabase"/>
                <property name="username" value="user"/>
                <property name="password" value="password"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource="org/myapp/mapper/UserMapper.xml"/>
    </mappers>
</configuration>
```

### 실행 클래스

- MyBatis를 사용하여 데이터베이스 작업을 수행하는 클래스를 작성함. 이 클래스에서는 MyBatis SqlSession을 사용하여 Mapper를 호출하고, 필요한 비즈니스 로직을 수행함.

```java
public class MyApp {
    public static void main(String[] args) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.getUserById(1);
            System.out.println(user.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### 실행 및 로깅 설정

- MyBatis와 함께 로깅 프레임워크를 설정하여 실행 정보를 로그로 기록할 수 있음. 보통 Log4j 또는 SLF4J 같은 로깅 라이브러리와 통합하여 사용함. 로깅 설정은 log4j.properties 파일이나 logback.xml 파일을 통해 관리할 수 있음. 이를 통해 SQL 쿼리 실행 시점, 결과 등의 정보를 기록하여 디버깅 시 활용할 수 있음.

## 3. 데이터 관리를 위한 마이바티스 프로그래밍

## 4. 마이바티스 설정

MyBatis Configuration

## 5. 마이바티스 매핑 구문 정의

Defining MyBatis Mapping Syntax

## 6. 마이바티스 객체

MyBatis Objects

## 7. 마이바티스와 웹 애플리케이션

MyBatis and Web Applications

## 8. 마이바티스와 스프링 연동 웹 애플리케이션

MyBatis Integration with Spring Web Applications
