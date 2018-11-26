# Amino Mybatis Type Handlers

### Usage

For properties file, add this line to `application.properties`
```properties
mybatis.type-handlers-package=com.narvii.server.block.dao.typeHandler
```

For yaml file, add this line to `application.yaml`
```yaml
mybatis:
  type-handlers-package: com.narvii.server.block.dao.typeHandler
```

To use it in your Maven build add:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

And the dependency:

```xml
<dependency>
    <groupId>com.github.AminoApps</groupId>
    <artifactId>mybatis-type-handlers</artifactId>
    <version>${version}</version>
</dependency>
```
