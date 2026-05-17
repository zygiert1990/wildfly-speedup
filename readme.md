## Install WildFly

```bash
curl -L -O https://github.com/wildfly/wildfly/releases/download/39.0.1.Final/wildfly-39.0.1.Final.tar.gz
tar -xzf wildfly-39.0.1.Final.tar.gz
```

## Run MySQL in Docker

```bash
docker run --name mysql-wildfly   -e MYSQL_ROOT_PASSWORD=password   -e MYSQL_DATABASE=AppDB   -e MYSQL_USER=appuser   -e MYSQL_PASSWORD=apppassword   -p 3306:3306 -d mysql:8.0
```

## Get MySQL Connector

```bash
curl -L -O https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.3.0/mysql-connector-j-8.3.0.jar
```

## Run server

```bash
/root/wildfly-39.0.1.Final/bin/standalone.sh
```

## Configure WildFly Datasource

```bash
/root/wildfly-39.0.1.Final/bin/jboss-cli.sh --connect <<EOF
> module add --name=com.mysql --resources=/root/mysql-connector-j-8.3.0.jar --dependencies=javax.api,javax.transaction.api
> /subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-class-name=com.mysql.cj.jdbc.Driver)
> data-source add --name=AppDS --jndi-name=java:jboss/datasources/AppDS --driver-name=mysql --connection-url=jdbc:mysql://localhost:3306/AppDB --user-name=appuser --password=apppassword --check-valid-connection-sql="SELECT 1" --background-validation=true
> :reload
> EOF
```

## Test DB

```bash
/root/wildfly-39.0.1.Final/bin/jboss-cli.sh --connect --command="/subsystem=datasources/data-source=AppDS:test-connection-in-pool"
```

## Copy WAR file

```bash
cp wildfly-speedup/target/wildfly-speedup-1.0-SNAPSHOT.war /root/wildfly-39.0.1.Final/standalone/deployments/
```

## Test

1. Fetch All Users
```bash
curl http://localhost:8080/api/users
```
2. Create User
```bash
curl -X POST http://localhost:8080/api/users \
     -H "Content-Type: application/json" \
     -d '{"name": "John Doe"}'
```
3. Fetch User by ID
```bash
curl http://localhost:8080/api/users/<UUID>
```

## Override JAVA_OPTS

```bash
JAVA_OPTS="$JAVA_OPTS -XX:AOTCache=cache.aot" /root/wildfly-39.0.1.Final/bin/standalone.sh
```