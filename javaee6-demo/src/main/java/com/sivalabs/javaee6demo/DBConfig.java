package com.sivalabs.javaee6demo;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Startup;

@DataSourceDefinition(
        className = "com.mysql.jdbc.Driver",
        name = "java:global/JavaEE6DS",
        serverName="localhost",
        portNumber=3306,
        user = "root",
        password = "admin",
        databaseName = "test"
)
@Startup
public class DBConfig {

}
